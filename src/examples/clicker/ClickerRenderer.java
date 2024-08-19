package examples.clicker;

import java.awt.Color;
import java.awt.Font;
import nootovich.nglib.*;

import static examples.clicker.Clicker.*;
import static examples.clicker.Main.clicks;

public class ClickerRenderer extends NGRenderer {

    static {
        font = new Font(Font.MONOSPACED, Font.BOLD, 128);
    }

    @Override
    public void render(NGGraphics g) {
        { // BACKGROUND
            g.drawRect(0, 0, w, h, Color.GRAY);
            g.drawRect(CENTER, Color.DARK_GRAY);
            g.drawGradient(new NGVec2i(0, h * 0.6f), new NGVec2i(w, h * 0.4f), 0, new Color(0x10 << 24, true));
            g.drawGradient(new NGVec2i(0, 0), new NGVec2i(w, h * 0.4f), 2, new Color(0x10 << 24, true));
        } // BACKGROUND
        g.drawTextCentered(clicks, w / 2, h / 2, Color.BLACK, 128);
        g.drawRect(BUTTON1, new Color(0x5B556E));
        g.drawRectBorder(BUTTON1, Color.BLACK);
        g.drawTextCentered(clickPower, BUTTON1.x() + BUTTON1.z() / 2, BUTTON1.y() + BUTTON1.w() / 2, Color.WHITE, 42);
    }

    @Override
    public void reset() { }
}
