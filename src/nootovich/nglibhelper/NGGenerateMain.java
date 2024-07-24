package nootovich.nglibhelper;

import java.util.Stack;
import nootovich.nglib.NGFileSystem;

import static java.awt.event.KeyEvent.*;

public class NGGenerateMain {

    public static final Stack<Integer> keys  = new Stack<>();
    public static final Stack<String>  names = new Stack<>();

    // TODO: good enough to just get something working, but needs to change at some point
    public static void main(String[] args) {

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
        sb.append("import javax.swing.JFrame;\n\n");
        sb.append("@SuppressWarnings(\"unused\")\n");
        sb.append("public class NGMain extends NGHotReloadable implements AWTEventListener {\n\n");
        sb.append("    @Override\n");
        sb.append("    public void eventDispatched(AWTEvent event) {\n");
        sb.append("        int id = event.getID();\n");
        sb.append("        if (id == KeyEvent.KEY_PRESSED) {\n");
        sb.append("            switch (((KeyEvent) event).getKeyCode()) {\n");

        for (int i = 0; i < keys.size(); i++) {
            sb.append("                case ").append(keys.get(i)).append(" -> on").append(names.get(i)).append("Press();\n");
        }

        sb.append("            }\n");
        sb.append("        } else if (id == KeyEvent.KEY_RELEASED) {\n");
        sb.append("            switch (((KeyEvent) event).getKeyCode()) {\n");

        for (int i = 0; i < keys.size(); i++) {
            sb.append("                case ").append(keys.get(i)).append(" -> on").append(names.get(i)).append("Release();\n");
        }

        sb.append("            }\n");
        sb.append("        } else if (id == MouseEvent.MOUSE_PRESSED || id == MouseEvent.MOUSE_RELEASED) {\n");
        sb.append("            Insets  ins = ((JFrame) event.getSource()).getInsets();\n");
        sb.append("            NGVec2i pos = new NGVec2i(((MouseEvent) event).getPoint()).sub(ins.left, ins.top);\n");
        sb.append("            switch (((MouseEvent) event).getButton()) {\n");
        sb.append("                case MouseEvent.BUTTON1 -> { if (id == MouseEvent.MOUSE_PRESSED) onLMBPressed(pos); else onLMBReleased(pos); }\n");
        sb.append("                case MouseEvent.BUTTON3 -> { if (id == MouseEvent.MOUSE_PRESSED) onRMBPressed(pos); else onRMBReleased(pos); }\n");
        sb.append("                case MouseEvent.BUTTON2 -> { if (id == MouseEvent.MOUSE_PRESSED) onMMBPressed(pos); else onMMBReleased(pos); }\n");
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
        sb.append("            onWindowMoved(c.getX(), c.getY());\n");
        sb.append("        } else if (id == ComponentEvent.COMPONENT_RESIZED) {\n");
        sb.append("            Component c = ((ComponentEvent) event).getComponent();\n");
        sb.append("            if (!(c instanceof JFrame && c.isVisible())) return;\n");
        sb.append("            Insets ins = ((JFrame) c).getInsets();\n");
        sb.append("            onWindowResize(c.getWidth() - ins.left, c.getHeight() - ins.top);\n");
        sb.append("        } else if (id == WindowEvent.WINDOW_ICONIFIED) {\n");
        sb.append("            onWindowMinimize();\n");
        sb.append("        } else if (id == WindowEvent.WINDOW_DEICONIFIED) {\n");
        sb.append("            onWindowRestore();\n");
        sb.append("        }\n");
        sb.append("    }\n\n");

        for (String side: new String[]{"L", "R", "M"}) {
            sb.append("    public void on").append(side).append("MBPressed(NGVec2i pos) { }\n");
            sb.append("    public void on").append(side).append("MBReleased(NGVec2i pos) { }\n");
            sb.append("    public void while").append(side).append("MBHeld(NGVec2i pos) { }\n\n");
        }

        sb.append("    public void onMouseMoved(NGVec2i pos) { }\n");
        sb.append("    public void onMouseWheel(NGVec2i pos,int direction) { }\n\n");

        sb.append("    public void onWindowMoved(int x, int y) { }\n");
        sb.append("    public void onWindowResize(int w, int h) { }\n");
        sb.append("    public void onWindowMinimize() { }\n");
        sb.append("    public void onWindowRestore() { }\n\n");

        for (int i = 0; i < keys.size(); i++) {
            sb.append("    public void on").append(names.get(i)).append("Press() { }\n");
            sb.append("    public void on").append(names.get(i)).append("Release() { }\n");
            sb.append("    public void while").append(names.get(i)).append("Held() { }\n\n");
        }

        sb.append("\n}\n");

        NGFileSystem.saveFile("./src/nootovich/nglib/NGMain.java", sb.toString());
    }
}
