package examples.ui_test;

import java.awt.Color;
import java.awt.Font;
import nootovich.nglib.*;

import static examples.ui_test.UITest.*;

public class UITestRenderer extends NGRenderer {

    static {
        font = new Font(Font.MONOSPACED, Font.BOLD, 64);
    }

    @Override
    public void render(NGGraphics g) {
        g.drawRect(0, 0, w, h, Color.DARK_GRAY);
        g.drawText(String.valueOf(tickCount), 0, 50, Color.LIGHT_GRAY);
        g.drawText(String.valueOf((System.currentTimeMillis() - startTime) / 100), 0, 150, Color.LIGHT_GRAY);

        final NGVec2i buttonPos  = new NGVec2i(350, 150);
        final NGVec2i buttonSize = new NGVec2i(200, 50);
        g.drawRectWithBorder(buttonPos, buttonSize, Color.GRAY, Color.BLACK);
    }

    @Override
    public void reset() {

    }
}
