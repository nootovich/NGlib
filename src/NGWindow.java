import javax.swing.*;
import java.awt.*;

public class NGWindow {

    public int w, h;
    public boolean shouldClose = false;

    public final JFrame     jf = new JFrame();
    public final NGGraphics g;
    public       NGRenderer renderer;
    public final Insets     ins;

    NGWindow(int width, int height) {
        this.w = width;
        this.h = height;

        jf.pack();
        ins = jf.getInsets();
        jf.setSize(toWindowWidth(w), toWindowHeight(h));

        g = new NGGraphics(jf);

        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.requestFocus();
    }

    public void redraw() {
        if (renderer != null) renderer.render(g);
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

    @SuppressWarnings("deprecation")
    public void setKeyboardHandler(NGKeyboardHandler keyboardHandler) {
        jf.addKeyListener(keyboardHandler.listener);
    }

    @SuppressWarnings("deprecation")
    public void setResizeHandler(NGResizeHandler resizeHandler) {
        jf.addComponentListener(resizeHandler.listener);
    }
}
