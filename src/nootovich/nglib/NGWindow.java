package nootovich.nglib;

import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class NGWindow {

    public final int HOT_RELOAD_CHECK_COOLDOWN = 500;

    public boolean shouldClose = false;

    public NGVec2i pos;
    public NGVec2i size;
    public final Insets ins;

    private NGMain     main;
    public  NGRenderer renderer;

    public       JFrame     jf;
    public final NGGraphics g;

    private long lastHotReloadCheckTime = 0;

    public NGWindow(int width, int height, NGRenderer renderer, NGMain main) {
        size = new NGVec2i(width, height);
        this.main = main;
        this.jf   = new JFrame();

        jf.getToolkit().addAWTEventListener(main, -1);

        jf.pack();
        ins = jf.getInsets();
        jf.setSize(toWindowWidth(size.w()), toWindowHeight(size.h()));

        g = new NGGraphics(jf);
        setRenderer(renderer);

        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.requestFocus();
    }

    public void redraw() {
        if (shouldClose) System.exit(0);
        reloadNGClassesIfNeeded();
        renderer.render(g);
        g.displayOn(jf);
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
        if (renderer.defaultFont != null) g.setFont(renderer.defaultFont);
    }
}
