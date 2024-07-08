package nootovich.nglib;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class NGResizeHandler {

    @Deprecated
    @SuppressWarnings("DeprecatedIsStillUsed")
    public final ComponentListener listener = new ComponentListener() {
        @Override
        public void componentResized(ComponentEvent e) {
            if (window == null) NGUtils.error("U Can'T hAz wIndoW :(");// TODO: an error message
            onResize(window.toRealWidth(e.getComponent().getWidth()), window.toRealHeight(e.getComponent().getHeight()));
        }

        @Override
        public void componentMoved(ComponentEvent e) { }

        @Override
        public void componentShown(ComponentEvent e) { }

        @Override
        public void componentHidden(ComponentEvent e) { }
    };

    public NGWindow window;

    public void onResize(int w, int h) { }

}
