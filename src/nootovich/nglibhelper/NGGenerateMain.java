package nootovich.nglibhelper;

import java.util.*;
import nootovich.nglib.NGFileSystem;

import static java.awt.event.KeyEvent.*;

public class NGGenerateMain {

    public static boolean DEBUG = false;

    public static final Stack<Integer> keys  = new Stack<>();
    public static final Stack<String>  names = new Stack<>();

    public static final Map<Integer, String> mouseButtons;

    static {
        mouseButtons = new HashMap<>();
        mouseButtons.put(1, "LMB");
        mouseButtons.put(2, "MMB");
        mouseButtons.put(3, "RMB");
    }

    public static void main(String[] args) {

        for (String s: args) {
            if (s.equals("-d")) DEBUG = true;
            else throw new IllegalStateException("Unexpected argument: " + s);
        }

        for (int key = VK_ENTER; key < VK_BEGIN; key++) {
            String text = getKeyText(key);
            if (text.startsWith("Unknown")) continue;
            text = text.replace("On/Off", "OnOff");
            text = text.replace(" ", "");
            text = text.replace("-", "");
            text = text.replace("*", "Asterisk");
            text = text.replace("+", "Plus");
            text = text.replace("-", "Minus");
            text = text.replace(",", "Comma");
            text = text.replace(".", "Period");
            text = text.replace("/", "Slash");
            if (names.contains(text)) continue;
            keys.push(key);
            names.push(text);
        }

        StringBuilder sb = new StringBuilder();

        sb.append("package nootovich.nglib;\n\n");

        sb.append("import java.awt.*;\n");
        sb.append("import java.awt.event.*;\n");
        sb.append("import java.lang.reflect.InvocationTargetException;\n");
        sb.append("import java.util.*;\n");
        sb.append("import javax.swing.JFrame;\n\n");

        sb.append("@SuppressWarnings(\"unused\")\n");
        sb.append("public class NGMain implements AWTEventListener {\n\n");

        sb.append("    private static final int WINDOW_MINIMAL_SIZE = 100;\n\n");

        sb.append("    public static int w;\n");
        sb.append("    public static int h;\n\n");

        sb.append("    public static float TICK_DURATION  = 0.0333f; // Measured in seconds\n");
        sb.append("    public static float FRAME_DURATION = 0.0167f; // Measured in seconds\n\n");

        sb.append("    public static NGWindow window;\n\n");

        sb.append("    public static int tickCount = 0;\n\n");

        sb.append("    public static Stack<String> heldKeys = new Stack<>();\n\n");

        sb.append("    public void setTickRate(int ups) {\n");
        sb.append("        TICK_DURATION = 1.0f / ups;\n");
        sb.append("    }\n\n");

        sb.append("    public void setFrameRate(int fps) {\n");
        sb.append("        FRAME_DURATION = 1.0f / fps;\n");
        sb.append("    }\n\n");

        sb.append("    public void createWindow(int w, int h, NGRenderer renderer) {\n");
        sb.append("        this.w = w;\n");
        sb.append("        this.h = h;\n");
        sb.append("        window = new NGWindow(w, h, renderer, this);\n");
        sb.append("    }\n\n");

        sb.append("    public void start() {\n");
        if (DEBUG) {
            sb.append("        NGUtils.info(\"tickrate: \" + 1 / TICK_DURATION);\n");
            sb.append("        NGUtils.info(\"framerate: \" + 1 / FRAME_DURATION);\n");
        }
        sb.append("        new Timer().scheduleAtFixedRate(new TimerTask(){public void run(){updateAll();}},0,(long)(TICK_DURATION*1000));\n");
        sb.append("        new Timer().scheduleAtFixedRate(new TimerTask(){public void run(){window.redraw();}},0,(long)(FRAME_DURATION*1000));\n");
        // sb.append("        new Timer((int) (TICK_DURATION  * 1000), t -> updateAll()).start();\n");
        // sb.append("        new Timer((int) (FRAME_DURATION * 1000), t -> window.redraw()).start();\n");
        sb.append("    }\n\n");

        sb.append("    public void exit() {\n");
        sb.append("        window.shouldClose = true;\n");
        sb.append("    }\n\n");

        sb.append("    public void exit(float waitTime) {\n");
        sb.append("        new Timer().scheduleAtFixedRate(new TimerTask(){public void run(){window.shouldClose = true;}},0,(long)(waitTime*1000));\n");
        // sb.append("        new Timer((int) (waitTime * 1000), t -> window.shouldClose = true).start();\n");
        sb.append("    }\n\n");

        sb.append("    public void updateAll() {\n");
        sb.append("        updateHeldKeys();\n");
        sb.append("        update();\n");
        sb.append("        tickCount++;\n");
        sb.append("    }\n\n");

        sb.append("    public void update() { }\n\n");

        { // updateHeldKeys()
            sb.append("    public void updateHeldKeys() {\n");
            sb.append("        for (String heldKey: heldKeys) {\n");
            sb.append("            try {\n");
            sb.append("                if (heldKey.equals(\"LMB\") || heldKey.equals(\"RMB\") || heldKey.equals(\"MMB\"))\n");
            sb.append("                    getClass().getDeclaredMethod(\"while\" + heldKey + \"Held\", NGVec2i.class)\n");
            sb.append("                              .invoke(this, new NGVec2i(MouseInfo.getPointerInfo().getLocation()).sub(window.pos));\n");
            sb.append("                else getClass().getDeclaredMethod(\"while\" + heldKey + \"Held\").invoke(this);\n");
            sb.append("            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) { }\n");
            sb.append("        }\n");
            sb.append("    }\n\n");
        } // updateHeldKeys()

        { // eventDispatched()
            sb.append("    @Override\n");
            sb.append("    public void eventDispatched(AWTEvent event) {\n");
            sb.append("        int id = event.getID();\n");
            sb.append("        if (id == KeyEvent.KEY_PRESSED) {\n");
            sb.append("            int  keyCode = ((KeyEvent) event).getKeyCode();\n");
            sb.append("            char keyChar = ((KeyEvent) event).getKeyChar();\n");
            sb.append("            onAnyKeyPress(keyCode, keyChar);\n");
            sb.append("            switch (keyCode) {\n");

            for (int i = 0; i < keys.size(); i++) {
                sb.append("                case ").append(keys.get(i)).append(": {\n");
                sb.append("                    heldKeys.push(\"").append(names.get(i)).append("\");\n");
                sb.append("                    on").append(names.get(i)).append("Press();\n");
                sb.append("                    break;\n");
                sb.append("                }\n");
            }

            sb.append("            }\n");
            sb.append("            afterAnyKeyPress(keyCode, keyChar);\n");
            sb.append("        } else if (id == KeyEvent.KEY_RELEASED) {\n");
            sb.append("            int keyCode  = ((KeyEvent) event).getKeyCode();\n");
            sb.append("            char keyChar = ((KeyEvent) event).getKeyChar();\n");
            sb.append("            onAnyKeyRelease(keyCode, keyChar);\n");
            sb.append("            switch (keyCode) {\n");

            for (int i = 0; i < keys.size(); i++) {
                sb.append("                case ").append(keys.get(i)).append(": {\n");
                sb.append("                    heldKeys.remove(\"").append(names.get(i)).append("\");\n");
                sb.append("                    on").append(names.get(i)).append("Release();\n");
                sb.append("                    break;\n");
                sb.append("                }\n");
            }

            sb.append("            }\n");
            sb.append("            afterAnyKeyRelease(keyCode, keyChar);\n");
            sb.append("        } else if (id == MouseEvent.MOUSE_PRESSED || id == MouseEvent.MOUSE_RELEASED) {\n");
            sb.append("            Insets  ins = ((JFrame) event.getSource()).getInsets();\n");
            sb.append("            NGVec2i pos = new NGVec2i(((MouseEvent) event).getPoint()).sub(ins.left, ins.top);\n");
            sb.append("            switch (((MouseEvent) event).getButton()) {\n");
            for (int mouseButton = 1; mouseButton <= 3; mouseButton++) {
                sb.append("                case MouseEvent.BUTTON").append(mouseButton).append(": {\n");
                sb.append("                    if (id == MouseEvent.MOUSE_PRESSED) {\n");
                sb.append("                        heldKeys.push(\"").append(mouseButtons.get(mouseButton)).append("\");\n");
                sb.append("                        on").append(mouseButtons.get(mouseButton)).append("Press(pos);\n");
                sb.append("                    } else {\n");
                sb.append("                        heldKeys.remove(\"").append(mouseButtons.get(mouseButton)).append("\");\n");
                sb.append("                        on").append(mouseButtons.get(mouseButton)).append("Release(pos);\n");
                sb.append("                    }\n");
                sb.append("                    break;\n");
                sb.append("                }\n");
            }
            sb.append("            }\n");
            sb.append("        } else if (id == MouseEvent.MOUSE_MOVED || id == MouseEvent.MOUSE_DRAGGED) {\n");
            sb.append("            Insets  ins = ((JFrame) event.getSource()).getInsets();\n");
            sb.append("            NGVec2i pos = new NGVec2i(((MouseEvent) event).getPoint()).sub(ins.left, ins.top);\n");
            sb.append("            onMouseMoved(pos);\n");
            sb.append("        } else if (id == MouseEvent.MOUSE_WHEEL) {\n");
            sb.append("            Insets  ins = ((JFrame) event.getSource()).getInsets();\n");
            sb.append("            NGVec2i pos = new NGVec2i(((MouseEvent) event).getPoint()).sub(ins.left, ins.top);\n");
            sb.append("            onMouseWheel(pos, ((MouseWheelEvent) event).getWheelRotation());\n");
            sb.append("        } else if (id == ComponentEvent.COMPONENT_MOVED) {\n");
            sb.append("            Component c = ((ComponentEvent) event).getComponent();\n");
            sb.append("            if (!(c instanceof JFrame && c.isVisible())) return;\n");
            sb.append("            // NOTE: window.pos points to the top-left corner of the drawable area.\n");
            sb.append("            //  In other words - excludes title bar.\n");
            sb.append("            window.pos = new NGVec2i(c.getLocation()).add(window.ins.left, window.ins.top);\n");
            sb.append("            onWindowMoved(c.getX(), c.getY());\n");
            sb.append("        } else if (id == ComponentEvent.COMPONENT_RESIZED) {\n");
            sb.append("            Component c = ((ComponentEvent) event).getComponent();\n");
            sb.append("            if (!(c instanceof JFrame && c.isVisible())) return;\n");
            sb.append("            Insets ins = ((JFrame) c).getInsets();\n");
            sb.append("            w = Math.max(WINDOW_MINIMAL_SIZE, c.getWidth() - ins.left - ins.right);\n");
            sb.append("            h = Math.max(WINDOW_MINIMAL_SIZE, c.getHeight() - ins.top - ins.bottom);\n");
            sb.append("            window.g.resize(w, h);\n");
            sb.append("            onWindowResize(w, h);\n");
            sb.append("        } else if (id == WindowEvent.WINDOW_ICONIFIED) {\n");
            sb.append("            onWindowMinimize();\n");
            sb.append("        } else if (id == WindowEvent.WINDOW_DEICONIFIED) {\n");
            sb.append("            onWindowRestore();\n");
            sb.append("        }\n");
            sb.append("    }\n\n");
        } // eventDispatched()

        sb.append("    public void onMouseMoved(NGVec2i pos) { }\n");
        sb.append("    public void onMouseWheel(NGVec2i pos, int direction) { }\n\n");

        sb.append("    public void onWindowMoved(int x, int y) { }\n");
        sb.append("    public void onWindowResize(int w, int h) { }\n");
        sb.append("    public void onWindowMinimize() { }\n");
        sb.append("    public void onWindowRestore() { }\n\n");

        { // on<Key>Press(), on<Key>Release(), while<Key>Held()
            for (int mouseButton = 1; mouseButton <= 3; mouseButton++) {
                String mouseButtonName = mouseButtons.get(mouseButton);
                sb.append("    public void on").append(mouseButtonName).append("Press(NGVec2i pos) { }\n");
                sb.append("    public void on").append(mouseButtonName).append("Release(NGVec2i pos) { }\n");
                sb.append("    public void while").append(mouseButtonName).append("Held(NGVec2i pos) { }\n\n");
            }

            sb.append("    public void onAnyKeyPress(int keyCode, char keyChar) { }\n");
            sb.append("    public void afterAnyKeyPress(int keyCode, char keyChar) { }\n\n");

            // TODO: sb.append("    public void whileAnyKeyHeld() { }\n\n");

            sb.append("    public void onAnyKeyRelease(int keyCode, char keyChar) { }\n");
            sb.append("    public void afterAnyKeyRelease(int keyCode, char keyChar) { }\n\n");

            for (int i = 0; i < keys.size(); i++) {
                String name = names.get(i);
                sb.append("    public void on").append(name).append("Press() {").append(name.equals("Escape") ? "exit();" : " ").append("}\n");
                sb.append("    public void on").append(name).append("Release() { }\n");
                sb.append("    public void while").append(name).append("Held() { }\n\n");
            }
        } // on<Key>Press(), on<Key>Release(), while<Key>Held()

        sb.append("\n}\n");

        NGFileSystem.saveFile("./src/nootovich/nglib/NGMain.java", sb.toString());
    }
}
