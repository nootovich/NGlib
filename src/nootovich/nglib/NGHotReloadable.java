package nootovich.nglib;

import com.sun.tools.javac.Main;

import java.lang.reflect.InvocationTargetException;

public class NGHotReloadable extends ClassLoader {

    public String sourceDir;
    public String sourceFile;
    public String classDir;
    public String className;

    public long lastModifiedTime = 0;

    public NGHotReloadable() {
        super(NGHotReloadable.class.getClassLoader());

        // TODO: support inner classes somehow
        if (getClass().getDeclaringClass() != null) NGUtils.error("Inner classes are not supported yet");

        Class<?> declaringClass = getClass();
        while (declaringClass.getDeclaringClass() != null) declaringClass = declaringClass.getDeclaringClass();

        sourceFile = declaringClass.getSimpleName();
        sourceDir  = NGFileSystem.getParent(NGFileSystem.findRecursively(sourceFile + ".java"));

        className = getClass().getSimpleName();
        classDir  = NGFileSystem.getParent(NGFileSystem.findRecursively(className + ".class"));
    }

    public NGHotReloadable reloadIfNeeded() {
        long curModifiedTime = NGFileSystem.getLastModifiedTime(sourceDir + sourceFile + ".java");
        if (curModifiedTime != lastModifiedTime) {
            if (lastModifiedTime > 0) return getNew();
            lastModifiedTime = curModifiedTime;
        }
        return null;
    }

    @Override
    public Class<?> loadClass(String name) {
        if (!name.equals(className)) try {
            return super.loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        byte[] classData = NGFileSystem.loadBytes(classDir + className + ".class");
        return defineClass(className, classData, 0, classData.length);
    }

    // CREDIT: https://jenkov.com/tutorials/java-reflection/dynamic-class-loading-reloading.html
    public NGHotReloadable getNew() {
        try {
            Main.compile(new String[]{"-d", classDir, "-sourcepath", sourceDir, sourceDir + sourceFile + ".java"});
            NGUtils.info("Reloaded class: " + className);
            return (NGHotReloadable) loadClass(sourceFile).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) { // TODO: Actually handle errors
            NGUtils.error(e.getMessage());
        }
        return null;
    }

}