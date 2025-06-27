package nootovich.nglib;

import javax.swing.*;
import java.awt.*;

public class NGWindow {

    public boolean shouldClose = false;

    public NGVec2i pos;
    public NGVec2i size;
    public Insets ins;

    public JFrame jf;
    public NGRenderer renderer;
    public NGGraphics g;

    public NGWindow(int width, int height) {
        size = new NGVec2i(width, height);
    }

    public NGWindow(int width, int height, NGRenderer renderer, NGMain main) {
        size = new NGVec2i(width, height);
        this.jf = new JFrame();

        jf.getToolkit().addAWTEventListener(main, -1);

        jf.pack();
        ins = jf.getInsets();
        jf.setSize(toWindowWidth(size.w()), toWindowHeight(size.h()));

        g = new NGGraphics(jf);
        setRenderer(renderer);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.requestFocus();
    }

    public void redraw() {
        if (shouldClose) System.exit(0);
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
        if (renderer.font != null) g.setFont(renderer.font);
    }
}
