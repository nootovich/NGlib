package examples.clicker;

import java.awt.Color;
import java.awt.Font;
import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGRenderer;

import static examples.clicker.Clicker.*;
import static examples.clicker.Main.clicks;

public class ClickerRenderer extends NGRenderer {

    static {
        font = new Font(Font.MONOSPACED, Font.BOLD, 128);
    }

    @Override
    public void render(NGGraphics g) {
        g.drawRect(0, 0, w, h, Color.GRAY);
        g.drawRect(CENTER, Color.DARK_GRAY);
        g.drawTextCentered(clicks, w / 2, h / 2, Color.BLACK);
    }

    @Override
    public void reset() { }
}
