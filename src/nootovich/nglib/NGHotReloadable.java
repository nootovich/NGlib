package nootovich.nglib;

import com.sun.tools.javac.Main;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.nio.file.Path;

// IMPORTANT NOTE: Hot reloadable classes will break after reload if they encounter a `protected` field

public class NGHotReloadable extends ClassLoader {

    public static final boolean DEBUG = false;

    public static final String CLASSPATH = Path.of(System.getProperties().get("java.class.path").toString().split(";")[0]).getParent().toString() + "\\";

    public String className;
    public String sourceFile;
    public long   lastModifiedTime = 0;

    public NGHotReloadable() {
        super(NGHotReloadable.class.getClassLoader());

        // TODO: support inner classes somehow
        if (getClass().getDeclaringClass() != null) NGUtils.error("Inner classes are not supported yet");

        Class<?> declaringClass = getClass();
        while (declaringClass.getDeclaringClass() != null) declaringClass = declaringClass.getDeclaringClass();

        className = declaringClass.getName();
        String sourceFileName = declaringClass.getSimpleName() + ".java";
        sourceFile = NGFileSystem.findRecursively(sourceFileName);
    }

    public NGHotReloadable reloadIfNeeded() {
        NGHotReloadable result          = null;
        long            curModifiedTime = NGFileSystem.getLastModifiedTime(sourceFile);
        if (curModifiedTime != lastModifiedTime) {
            if (lastModifiedTime > 0) result = getNew();
            lastModifiedTime = curModifiedTime;
        }
        return result;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!name.equals(getClass().getName())) return super.loadClass(name);
        byte[] classData = NGFileSystem.loadBytes(CLASSPATH + className.replace('.', '/') + ".class");
        return defineClass(className, classData, 0, classData.length);
    }

    // CREDIT: https://jenkov.com/tutorials/java-reflection/dynamic-class-loading-reloading.html
    public NGHotReloadable getNew() {
        try { // TODO: don't like the look of this function. I'm glad that it works though

            PrintStream   stderr = System.err;
            StringBuilder sb     = new StringBuilder();
            System.setErr(new PrintStream(new OutputStream() { public void write(int b) { sb.append((char) b); } }));
            int compilationResult = Main.compile(new String[]{"-d", CLASSPATH, sourceFile});
            System.setErr(stderr);
            if (compilationResult != 0) return NGUtils.info("Compilation failed!\n" + sb);

            NGHotReloadable reloadedInstance = (NGHotReloadable) loadClass(getClass().getName()).getDeclaredConstructor().newInstance();

            // NOTE: No idea how this actually works nor do i care.
            Field[] fields = getClass().getDeclaredFields();
            for (Field field: fields) {
                if (field.getAnnotation(NGKeepStateAfterHotReload.class) == null) continue;
                NGHotReloadable referant = Modifier.isStatic(field.getModifiers()) ? null : this;
                if (!field.canAccess(referant)) continue;
                reloadedInstance.getClass().getField(field.getName()).set(reloadedInstance, field.get(referant));

                if (DEBUG){
                    System.out.printf("NAME: %s, TYPE: %s, OLD: %s, NEW:%s\n",
                                      field.getName(),
                                      field.getType(),
                                      field.get(this),
                                      field.get(reloadedInstance)
                    );
                }
            }

            NGUtils.info("Reloaded class: " + className);
            return reloadedInstance;
        } catch (ClassNotFoundException
                 | IllegalAccessException
                 | InstantiationException
                 | InvocationTargetException
                 | NoSuchFieldException
                 | NoSuchMethodException
            e) {
            throw new RuntimeException(e); // TODO: Actually handle errors
        }
    }
}
