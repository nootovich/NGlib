package nootovich.nglib;

import com.sun.tools.javac.Main;
import java.lang.reflect.InvocationTargetException;

public class NGHotReloadable extends ClassLoader {

    // TODO: un-hardcode again...
    public String sourceFile;
    public String sourcePath = ".\\src\\";
    public String classPath  = ".\\out\\production\\snake\\";

    public long lastModifiedTime = 0;

    public NGHotReloadable() {
        super(NGHotReloadable.class.getClassLoader());

        // TODO: support inner classes somehow
        if (getClass().getDeclaringClass() != null) NGUtils.error("Inner classes are not supported yet");

        Class<?> declaringClass = getClass();
        while (declaringClass.getDeclaringClass() != null) declaringClass = declaringClass.getDeclaringClass();

        String sourceFileName = declaringClass.getSimpleName() + ".java";
        sourceFile = NGFileSystem.getParent(NGFileSystem.findRecursively(sourceFileName)) + sourceFileName;
    }

    public NGHotReloadable reloadIfNeeded() {
        long curModifiedTime = NGFileSystem.getLastModifiedTime(sourceFile);
        if (curModifiedTime != lastModifiedTime) {
            if (lastModifiedTime > 0) return getNew();
            lastModifiedTime = curModifiedTime;
        }
        return null;
    }

    @Override
    public Class<?> loadClass(String name) {
        if (!name.equals(getClass().getName())) try {
            return super.loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        byte[] classData = NGFileSystem.loadBytes(classPath + getClass().getName().replace('.', '/') + ".class");
        return defineClass(getClass().getName(), classData, 0, classData.length);
    }

    // CREDIT: https://jenkov.com/tutorials/java-reflection/dynamic-class-loading-reloading.html
    public NGHotReloadable getNew() {
        try {
            if (Main.compile(new String[]{"-d", classPath, "-sourcepath", sourcePath, sourceFile}) != 0) {
                NGUtils.error("Compilation failed!");
            }
            NGUtils.info("Reloaded class: " + getClass().getName());
            return (NGHotReloadable) loadClass(getClass().getName()).getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                 | IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException e) {
            // TODO: Actually handle errors
            throw new RuntimeException(e);
        }
    }
}
