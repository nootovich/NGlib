package nootovich.nglib;

import java.awt.AWTEvent;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JFrame;

public class NGMouseHandler extends NGEventHandler {

    @Override
    public void eventDispatched(AWTEvent e) {
        MouseEvent me  = (MouseEvent) e;
        Insets     ins = ((JFrame) me.getSource()).getInsets();
        NGVec2i    pos = new NGVec2i(me.getPoint()).sub(ins.left, ins.top);
        switch (e.getID()) {
            case MouseEvent.MOUSE_PRESSED -> {
                if (me.getButton() == MouseEvent.BUTTON1) onLMBPressed(pos);
                else if (me.getButton() == MouseEvent.BUTTON3) onRMBPressed(pos);
                else if (me.getButton() == MouseEvent.BUTTON2) onMMBPressed(pos);
            }
            case MouseEvent.MOUSE_RELEASED -> {
                if (me.getButton() == MouseEvent.BUTTON1) onLMBReleased(pos);
                else if (me.getButton() == MouseEvent.BUTTON3) onRMBReleased(pos);
                else if (me.getButton() == MouseEvent.BUTTON2) onMMBReleased(pos);
            }
            case MouseEvent.MOUSE_MOVED, MouseEvent.MOUSE_DRAGGED -> onMoved(pos);
            case MouseEvent.MOUSE_WHEEL -> onWheel(pos, ((MouseWheelEvent) e).getWheelRotation());
            case MouseEvent.MOUSE_ENTERED, MouseEvent.MOUSE_EXITED, MouseEvent.MOUSE_CLICKED -> { }
            default -> NGUtils.error("Unexpected event!\n{%d} : %s".formatted(e.getID(), e));
        }
    }

    public void onLMBPressed(NGVec2i pos) { }

    public void onRMBPressed(NGVec2i pos) { }

    public void onMMBPressed(NGVec2i pos) { }

    public void onLMBReleased(NGVec2i pos) { }

    public void onRMBReleased(NGVec2i pos) { }

    public void onMMBReleased(NGVec2i pos) { }

    public void onMoved(NGVec2i pos) { }

    public void onWheel(NGVec2i pos, int direction) { }
}
