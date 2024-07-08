package nootovich.nglib;

import javax.swing.*;
import java.awt.*;

public class NGWindow {

    public int w, h;
    public boolean shouldClose = false;

    public final  JFrame     jf = new JFrame();
    public final  NGGraphics g;
    private       NGRenderer renderer;
    private final Insets     ins;

    private long lastHotReloadCheckTime = 0;

    public NGWindow(int width, int height, NGRenderer renderer) {
        this.w = width;
        this.h = height;
        setRenderer(renderer);

        jf.pack();
        ins = jf.getInsets();
        jf.setSize(toWindowWidth(w), toWindowHeight(h));

        g = new NGGraphics(jf);

        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.requestFocus();
    }

    public void redraw() {
        long curTime = System.currentTimeMillis();
        if (curTime - lastHotReloadCheckTime > 250) {
            lastHotReloadCheckTime = curTime;
            NGRenderer reloadedRenderer = (NGRenderer) renderer.reloadIfNeeded();
            if (reloadedRenderer != null) renderer = reloadedRenderer;
        }
        renderer.render(g);
        g.displayOn(jf);
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
    }

    @SuppressWarnings("deprecation")
    public void setKeyboardHandler(NGKeyboardHandler keyboardHandler) {
        jf.addKeyListener(keyboardHandler.listener);
    }

    @SuppressWarnings("deprecation")
    public void setResizeHandler(NGResizeHandler resizeHandler) {
        resizeHandler.window = this;
        jf.addComponentListener(resizeHandler.listener);
    }
}
