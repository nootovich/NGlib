import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NGKeyboardHandler {

    @Deprecated
    @SuppressWarnings("DeprecatedIsStillUsed")
    public KeyListener listener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyPressed(KeyEvent e) { onKeyDn(convertKeyCode(e.getKeyCode()), e.getKeyChar()); }

        @Override
        public void keyReleased(KeyEvent e) { onKeyUp(convertKeyCode(e.getKeyCode()), e.getKeyChar()); }
    };

    public void onKeyDn(int key, char chr) { }

    public void onKeyUp(int key, char chr) { }

    private int convertKeyCode(int badKeyCode) {
        return switch(badKeyCode) {
            // TODO: put conversion into a (hashmap?) inside of NGKeys
            case KeyEvent.VK_A -> NGKeys.A;
            case KeyEvent.VK_D -> NGKeys.D;
            case KeyEvent.VK_S -> NGKeys.S;
            case KeyEvent.VK_W -> NGKeys.W;
            default -> {
                System.out.printf("The key '%s' is not implemented%n", KeyEvent.getKeyText(badKeyCode));
                System.exit(1);
                yield -1;
            }
        };
    }
}
