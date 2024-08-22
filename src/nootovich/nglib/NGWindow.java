package nootovich.nglib;

import java.awt.Container;
import java.awt.Insets;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class NGWindow {

    public static final int HOT_RELOAD_CHECK_COOLDOWN = 500;

    public boolean shouldClose = false;

    public       NGVec2i pos;
    public       NGVec2i size;
    public final Insets  ins;

    private NGMain     main;
    public  NGRenderer renderer;
    public  JFrame     jf;

    private long lastHotReloadCheckTime = 0;

    public <NGR extends Class<? extends NGRenderer>> NGWindow(int width, int height, NGR rendererClass, NGMain main) {
        size      = new NGVec2i(width, height);
        this.main = main;
        this.jf   = new JFrame();

        jf.getToolkit().addAWTEventListener(main, -1);

        jf.pack();
        ins = jf.getInsets();
        jf.setSize(toWindowWidth(size.w()), toWindowHeight(size.h()));

        try {
            setRenderer(rendererClass.getDeclaredConstructor(Container.class).newInstance(jf));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.requestFocus();
    }

    public void redraw() {
        if (shouldClose) System.exit(0);
        reloadNGClassesIfNeeded();
        renderer.render();
        renderer.displayOn(jf);
    }

    public void reloadNGClassesIfNeeded() {
        long curTime = System.currentTimeMillis();
        if (curTime - lastHotReloadCheckTime > HOT_RELOAD_CHECK_COOLDOWN) {
            lastHotReloadCheckTime = curTime;

            if (renderer != null) {
                NGRenderer reloadedRenderer = (NGRenderer) renderer.reloadIfNeeded();
                if (reloadedRenderer != null) setRenderer(reloadedRenderer);
            }

            if (main != null) {
                NGMain reloadedMain = (NGMain) main.reloadIfNeeded();
                if (reloadedMain != null) {
                    jf.getToolkit().removeAWTEventListener(this.main);
                    this.main = reloadedMain;
                    jf.getToolkit().addAWTEventListener(this.main, -1);
                }
            }
        }
    }

    public int toRealWidth(int windowW) {
        return windowW - ins.left - ins.right;
    }

    public int toRealHeight(int windowH) {
        return windowH - ins.top - ins.bottom;
    }

    public int toWindowWidth(int realW) {
        return realW + ins.left + ins.right;
    }

    public int toWindowHeight(int realH) {
        return realH + ins.top + ins.bottom;
    }

    public void setRenderer(NGRenderer renderer) {
        this.renderer = renderer;
        if (renderer.font != null) renderer.setFont(renderer.font);
    }
}
