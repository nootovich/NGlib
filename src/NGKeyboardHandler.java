import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// TODO: I'm not very happy about extending KeyAdapter.
//  Need to think about a better way to handle keyboard events.
public class NGKeyboardHandler extends KeyAdapter {
    public void onKeyDown(int key, char chr) {
    }

    public void onKeyUp(int key, char chr) {
    }

    @Override
    @Deprecated
    public void keyTyped(KeyEvent e) {
    }

    @Override
    @Deprecated
    public void keyPressed(KeyEvent e) {
        onKeyDown(e.getKeyCode(), e.getKeyChar());
    }

    @Override
    @Deprecated
    public void keyReleased(KeyEvent e) {
        onKeyUp(e.getKeyCode(), e.getKeyChar());
    }
}
