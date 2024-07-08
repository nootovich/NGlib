package nootovich.nglib;

public class NGHotReloadable {
    public String sourceDir        = "src/";
    public String sourceFile       = "";
    public String classDir         = "out/production/snake/"; // TODO: Unhardcode
    public String classFile        = getClass().getName();
    public String className        = getClass().getSimpleName();
    public long   lastModifiedTime = 0;

    public NGHotReloadable() {
        Class<?> declaringClass = getClass();
        while (declaringClass.getDeclaringClass() != null) declaringClass = declaringClass.getDeclaringClass();
        sourceFile = declaringClass.getSimpleName();
        if (!sourceFile.equals(classFile)) NGUtils.error("Inner classes are not supported yet");
        // TODO: support inner classes somehow
    }

    public NGHotReloadable reloadIfNeeded() {
        long curModifiedTime = NGFileSystem.getLastModifiedTime(sourceDir + sourceFile + ".java");
        if (curModifiedTime != lastModifiedTime) {
            if (lastModifiedTime > 0) return new NGClassLoader(sourceDir, sourceFile, classDir, classFile, className).getNew();
            lastModifiedTime = curModifiedTime;
        }
        return null;
    }
}
