package examples.clicker;

import java.awt.Color;
import java.awt.Font;
import nootovich.nglib.*;

import static examples.clicker.Clicker.*;

public class ClickerRenderer extends NGRenderer {

    private static final int UPGRADE_TEXT_OFFSET = -6;
    private static final int UPGRADE_COST_OFFSET = 16;

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
        { // CLICKS
            g.drawTextCentered(clicks, w / 2, h / 2, Color.BLACK, 64);
        } // CLICKS
        { // UPGRADES
            { // CLICK POWER
                g.drawRect(BUTTON1, new Color(0x5B556E));
                g.drawRectBorder(BUTTON1, Color.BLACK);
                g.drawTextCentered(clickPower, BUTTON1.x() + BUTTON1.z() / 2, BUTTON1.y() + BUTTON1.w() / 2 + UPGRADE_TEXT_OFFSET, Color.WHITE, 24);
                g.drawTextCentered(clickPowerCost, BUTTON1.x() + BUTTON1.z() / 2, BUTTON1.y() + BUTTON1.w() / 2 + UPGRADE_COST_OFFSET, Color.WHITE, 14);
            } // CLICK POWER
            { // PASSIVE INCOME
                g.drawRect(BUTTON2, new Color(0x5B556E));
                g.drawRectBorder(BUTTON2, Color.BLACK);
                g.drawTextCentered(passiveIncome, BUTTON2.x() + BUTTON2.z() / 2, BUTTON2.y() + BUTTON2.w() / 2 + UPGRADE_TEXT_OFFSET, Color.WHITE, 24);
                g.drawTextCentered(passiveIncomeCost, BUTTON2.x() + BUTTON2.z() / 2, BUTTON2.y() + BUTTON2.w() / 2 + UPGRADE_COST_OFFSET, Color.WHITE, 14);
            } // PASSIVE INCOME
        } // UPGRADES
        for (NGButton button: buttons) g.drawButton(button);
    }

    @Override
    public void reset() { }
}
