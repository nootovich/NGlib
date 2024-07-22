package nootovich.nglib;

import javax.swing.*;
import java.awt.*;

public class NGWindow {

    public final int HOT_RELOAD_CHECK_COOLDOWN = 500;

    public boolean shouldClose = false;
    public int w, h;

    private final Insets ins;

    public NGRenderer      renderer;
    public NGKeyHandler    keyHandler;
    public NGMouseHandler  mouseHandler;
    public NGWindowHandler resizeHandler;

    public final JFrame     jf = new JFrame();
    public final NGGraphics g;

    private long lastHotReloadCheckTime = 0;

    public NGWindow(int width, int height, NGRenderer renderer) {
        this.w = width;
        this.h = height;

        jf.pack();
        ins = jf.getInsets();
        jf.setSize(toWindowWidth(w), toWindowHeight(h));

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

            if (keyHandler != null) {
                NGKeyHandler reloadedKeyHandler = (NGKeyHandler) keyHandler.reloadIfNeeded();
                if (reloadedKeyHandler != null) setKeyHandler(reloadedKeyHandler);
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

    public void setKeyHandler(NGKeyHandler keyHandler) {
        this.keyHandler = keyHandler;
        jf.getToolkit().addAWTEventListener(this.keyHandler, NGEventHandler.KEY);
    }

    public void setMouseHandler(NGMouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
        jf.getToolkit().addAWTEventListener(this.mouseHandler, NGEventHandler.MOUSE);
    }

    public void setResizeHandler(NGWindowHandler resizeHandler) {
        this.resizeHandler = resizeHandler;
        jf.getToolkit().addAWTEventListener(this.resizeHandler, NGEventHandler.WINDOW);
    }
}
