package nootovich.nglib;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class NGKeyHandler extends NGEventHandler {

    private static final Map<Integer, Integer> conversion = new HashMap<>();

    @Override
    public void eventDispatched(AWTEvent e) {
        switch (e.getID()) {
            case KeyEvent.KEY_TYPED    /*400*/ -> { }
            case KeyEvent.KEY_PRESSED  /*401*/ -> onKeyDn(convertKeyCode(((KeyEvent) e).getKeyCode()), ((KeyEvent) e).getKeyChar());
            case KeyEvent.KEY_RELEASED /*402*/ -> onKeyUp(convertKeyCode(((KeyEvent) e).getKeyCode()), ((KeyEvent) e).getKeyChar());
            default -> NGUtils.error("Unexpected event!\n{%d} : %s".formatted(e.getID(), e));
        }
    }

    private int convertKeyCode(int awtfulKeyCode) {
        if (!conversion.containsKey(awtfulKeyCode))
            NGUtils.error("The key '%s' is not implemented%n".formatted(KeyEvent.getKeyText(awtfulKeyCode)));
        return conversion.get(awtfulKeyCode);
    }

    public void onKeyDn(int key, char chr) { }

    public void onKeyUp(int key, char chr) { }

    static { // NOTE: some of those are REALLY redundant...
        conversion.put(KeyEvent.VK_ENTER, NGKeys.ENTER);
        conversion.put(KeyEvent.VK_UP, NGKeys.ARROW_UP);
        conversion.put(KeyEvent.VK_LEFT, NGKeys.ARROW_LEFT);
        conversion.put(KeyEvent.VK_DOWN, NGKeys.ARROW_DOWN);
        conversion.put(KeyEvent.VK_RIGHT, NGKeys.ARROW_RIGHT);
        conversion.put(KeyEvent.VK_SPACE, NGKeys.SPACEBAR);
        conversion.put(KeyEvent.VK_A, NGKeys.A);
        conversion.put(KeyEvent.VK_B, NGKeys.B);
        conversion.put(KeyEvent.VK_C, NGKeys.C);
        conversion.put(KeyEvent.VK_D, NGKeys.D);
        conversion.put(KeyEvent.VK_E, NGKeys.E);
        conversion.put(KeyEvent.VK_F, NGKeys.F);
        conversion.put(KeyEvent.VK_G, NGKeys.G);
        conversion.put(KeyEvent.VK_H, NGKeys.H);
        conversion.put(KeyEvent.VK_I, NGKeys.I);
        conversion.put(KeyEvent.VK_J, NGKeys.J);
        conversion.put(KeyEvent.VK_K, NGKeys.K);
        conversion.put(KeyEvent.VK_L, NGKeys.L);
        conversion.put(KeyEvent.VK_M, NGKeys.M);
        conversion.put(KeyEvent.VK_N, NGKeys.N);
        conversion.put(KeyEvent.VK_O, NGKeys.O);
        conversion.put(KeyEvent.VK_P, NGKeys.P);
        conversion.put(KeyEvent.VK_Q, NGKeys.Q);
        conversion.put(KeyEvent.VK_R, NGKeys.R);
        conversion.put(KeyEvent.VK_S, NGKeys.S);
        conversion.put(KeyEvent.VK_T, NGKeys.T);
        conversion.put(KeyEvent.VK_U, NGKeys.U);
        conversion.put(KeyEvent.VK_V, NGKeys.V);
        conversion.put(KeyEvent.VK_W, NGKeys.W);
        conversion.put(KeyEvent.VK_X, NGKeys.X);
        conversion.put(KeyEvent.VK_Y, NGKeys.Y);
        conversion.put(KeyEvent.VK_Z, NGKeys.Z);
    }

}
