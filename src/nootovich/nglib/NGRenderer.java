package nootovich.nglib;

public class NGRenderer {
    public String sourceDir        = "src/";
    public String sourceFile;
    public String classDir         = "out/production/snake/";
    public String classFile        = getClass().getName();
    public String className        = getClass().getSimpleName();
    public long   lastModifiedTime = 0;

    public NGRenderer() {
        Class<?> declaringClass = getClass();
        while (declaringClass.getDeclaringClass() != null) declaringClass = declaringClass.getDeclaringClass();
        sourceFile = declaringClass.getSimpleName();
        if (!sourceFile.equals(classFile)) NGUtils.error("Inner classes are not supported yet");
        // TODO: support inner classes somehow somehow
    }

    public void render(NGGraphics g) {
    }

    public void reloadIfNeeded(NGWindow window) {
        long curModifiedTime = NGFileSystem.getLastModifiedTime(sourceDir + sourceFile + ".java");
        if (curModifiedTime != lastModifiedTime) {
            if (lastModifiedTime > 0) {
                window.setRenderer(new NGClassLoader(sourceDir, sourceFile, classDir, classFile, className).getNew(NGRenderer.class));
                NGUtils.info("Reloaded class: " + classFile);
            }
            lastModifiedTime = curModifiedTime;
        }
    }
}
