import javax.swing.*;
import java.awt.*;

public class NGWindow {

    public int w, h;
    public boolean shouldClose = false;

    public final JFrame     jf       = new JFrame();
    public final NGGraphics g;
    public       NGRenderer renderer = null;

    NGWindow(int width, int height) {
        this.w = width;
        this.h = height;

        jf.pack();
        Insets ins = jf.getInsets();
        jf.setSize(w + ins.left + ins.right, h + ins.top + ins.bottom);

        g = new NGGraphics(jf);

        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.requestFocus();
    }

    public void redraw() {
        if (renderer != null) renderer.render(g);
        g.displayOn(jf);
    }

    @SuppressWarnings("deprecation")
    public void setKeyboardHandler(NGKeyboardHandler keyboardHandler) {
        jf.addKeyListener(keyboardHandler.listener);
    }
}
