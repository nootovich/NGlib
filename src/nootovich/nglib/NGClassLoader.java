// CREDIT: https://jenkov.com/tutorials/java-reflection/dynamic-class-loading-reloading.html

package nootovich.nglib;

import com.sun.tools.javac.Main;

import java.lang.reflect.InvocationTargetException;

// EXAMPLE: window.renderer = new NGClassLoader(<rendererClassName>, <rendererSourceDir>, <rendererClassDir>).getNew(NGRenderer.class)
//          window.renderer = new NGClassLoader(MyRenderer.class.getName(), "src/", "out/production/my_game/").getNew(NGRenderer.class)
public class NGClassLoader extends ClassLoader {

    private final String sourceDir;
    private final String sourceFile;
    private final String classDir;
    private final String classFile;
    private final String className;

    public NGClassLoader(String sourceDir, String sourceFile, String classDir, String classFile, String className) {
        super(NGClassLoader.class.getClassLoader());
        this.sourceDir  = sourceDir;
        this.sourceFile = sourceFile;
        this.classDir   = classDir;
        this.classFile  = classFile;
        this.className  = className;
    }

    @Override
    public Class<?> loadClass(String name) {
        if (!name.equals(classFile)) try {
            return super.loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        byte[] classData = NGFileSystem.loadBytes(classDir + classFile + ".class");
        return defineClass(classFile, classData, 0, classData.length);
    }

    public <T> T getNew(Class<T> castClass) {
        try {
            Main.compile(new String[]{"-d", classDir, "-sourcepath", sourceDir, sourceDir + sourceFile + ".java"});
            return castClass.cast(loadClass(sourceFile).getDeclaredConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}