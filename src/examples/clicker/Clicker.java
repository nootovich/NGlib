package examples.clicker;

import nootovich.nglib.*;

public class Clicker extends NGMain {

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
        createWindow(400, 300, new ClickerRenderer());
        setTickRate(TICK_RATE);
        setFrameRate(60);
        start();
    }

    @Override
    public void update() {
        if (tickCount % TICK_RATE == 0) clicks += passiveIncome;
    }

    @Override
    public void onLMBPressed(NGVec2i pos) {
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
