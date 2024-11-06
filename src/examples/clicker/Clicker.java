package examples.clicker;

import java.util.ArrayList;
import nootovich.nglib.*;

public class Clicker extends NGMain {

    public static ArrayList<NGButton> buttons = new ArrayList<>();

    public static final int TICK_RATE = 60;

    public static final int MARGIN = 50;

    public static NGVec4i CENTER;
    public static NGVec4i BUTTON1;
    public static NGVec4i BUTTON2;

    public static int clicks = 0;

    public static int clickPower     = 1;
    public static int clickPowerCost = 20;

    public static int passiveIncome     = 0;
    public static int passiveIncomeCost = 20;

    public void main() {
        setTickRate(TICK_RATE);
        setFrameRate(60);
        createWindow(400, 300, new ClickerRenderer());

        buttons.add(
            new NGButton("./assets/cross-mark.png", 0, 0, 50, 50) {
                @Override
                public void performAction() {
                    window.shouldClose = true;
                }
            }
        );

        start();
    }

    @Override
    public void update() {
        if (tickCount % TICK_RATE == 0) clicks += passiveIncome;
    }

    @Override
    public void onLMBPress(NGVec2i pos) {

        for (NGButton button: buttons) {
            if (pos.isInside(button.area)) button.performAction();
        }

        if (pos.isInside(CENTER)) clicks += clickPower;
        else if (pos.isInside(BUTTON1) && clicks >= clickPowerCost) {
            clicks -= clickPowerCost;
            clickPower++;
            clickPowerCost = clickPower * clickPower * clickPower / 2 + 25;
        } else if (pos.isInside(BUTTON2) && clicks >= passiveIncomeCost) {
            clicks -= passiveIncomeCost;
            passiveIncome++;
            passiveIncomeCost = (passiveIncome * passiveIncome + 7) / 3 + 20;
        }
    }

    @Override
    public void whileRMBHeld(NGVec2i pos) {
        clicks += clickPower;
    }

    @Override
    public void onWindowResize(int w, int h) {
        CENTER  = new NGVec4i(MARGIN, 0, w - MARGIN * 2, h);
        BUTTON1 = new NGVec4i(CENTER.x() + CENTER.z(), 0, MARGIN, MARGIN);
        BUTTON2 = new NGVec4i(CENTER.x() + CENTER.z(), MARGIN, MARGIN, MARGIN);
    }
}
