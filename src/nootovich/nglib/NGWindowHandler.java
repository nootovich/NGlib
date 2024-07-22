package nootovich.nglib;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class NGWindowHandler extends NGEventHandler {

    @Override
    public void eventDispatched(AWTEvent e) {
        Component c = ((ComponentEvent) e).getComponent();
        if (!c.getClass().equals(JFrame.class)) return;

        Insets ins = ((JFrame) c).getInsets();
        switch (e.getID()) {
            case ComponentEvent.COMPONENT_MOVED -> onMove(c.getX(), c.getY());
            case ComponentEvent.COMPONENT_RESIZED -> onResize(c.getWidth() - ins.left, c.getHeight() - ins.top);
            case WindowEvent.WINDOW_ICONIFIED -> onMinimize();
            case WindowEvent.WINDOW_DEICONIFIED -> onRestore();
            case WindowEvent.WINDOW_STATE_CHANGED -> { }
            default -> NGUtils.error("Unexpected event: %s".formatted(e));
        }
    }

    public void onMove(int x, int y) { }

    public void onResize(int w, int h) { }

    public void onMinimize() { }

    public void onRestore() { }
}
