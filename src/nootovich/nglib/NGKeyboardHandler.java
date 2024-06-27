package nootovich.nglib;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NGKeyboardHandler {

    @Deprecated
    @SuppressWarnings("DeprecatedIsStillUsed")
    public final KeyListener listener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyPressed(KeyEvent e) { onKeyDn(convertKeyCode(e.getKeyCode()), e.getKeyChar()); }

        @Override
        public void keyReleased(KeyEvent e) { onKeyUp(convertKeyCode(e.getKeyCode()), e.getKeyChar()); }
    };

    public void onKeyDn(int key, char chr) { }

    public void onKeyUp(int key, char chr) { }

    private int convertKeyCode(int awtfulKeyCode) {
        if (!NGKeys.conversion.containsKey(awtfulKeyCode)) {
            System.out.printf("The key '%s' is not implemented%n", KeyEvent.getKeyText(awtfulKeyCode));
            System.exit(1);
        }
        return NGKeys.conversion.get(awtfulKeyCode);
    }
}
