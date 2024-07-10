package nootovich.nglib;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class NGKeyboardHandler extends NGHotReloadable {

    // TODO SOMEDAY: Stop depending on AWT and handle `Events` myself (if possible)
    private static final Map<Integer, Integer> conversion = new HashMap<>();

    public NGKeyboardHandler() {
        conversion.put(KeyEvent.VK_ENTER, NGKeys.ENTER);        // 10
        conversion.put(KeyEvent.VK_UP, NGKeys.ARROW_UP);        // 28
        conversion.put(KeyEvent.VK_LEFT, NGKeys.ARROW_LEFT);    // 29
        conversion.put(KeyEvent.VK_DOWN, NGKeys.ARROW_DOWN);    // 30
        conversion.put(KeyEvent.VK_RIGHT, NGKeys.ARROW_RIGHT);  // 31
        conversion.put(KeyEvent.VK_SPACE, NGKeys.SPACEBAR);     // 32
        conversion.put(KeyEvent.VK_A, NGKeys.A);                // 65
        conversion.put(KeyEvent.VK_B, NGKeys.B);                // 66
        conversion.put(KeyEvent.VK_D, NGKeys.D);                // 68
        conversion.put(KeyEvent.VK_S, NGKeys.S);                // 83
        conversion.put(KeyEvent.VK_W, NGKeys.W);                // 87
    }

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
        if (!conversion.containsKey(awtfulKeyCode)) {
            System.out.printf("The key '%s' is not implemented%n", KeyEvent.getKeyText(awtfulKeyCode));
            System.exit(1);
        }
        return conversion.get(awtfulKeyCode);
    }
}
