package examples.clicker;

import nootovich.nglib.*;

import static examples.clicker.Main.clicks;

public class Clicker extends NGMain {

    public static final int MARGIN = 50;

    public static NGVec4i CENTER;
    public static NGVec4i BUTTON1;

    public static int clickPower = 1;

    public void main() {
        createWindow(400, 300, new ClickerRenderer());
        setTickRate(60);
        setFrameRate(60);
        start();
    }

    @Override
    public void onLMBPressed(NGVec2i pos) {
        if (pos.isInside(CENTER)) clicks += clickPower;
        if (pos.isInside(BUTTON1)) clickPower++;
    }

    @Override
    public void onWindowResize(int w, int h) {
        CENTER  = new NGVec4i(MARGIN, 0, w - MARGIN * 2, h);
        BUTTON1 = new NGVec4i(CENTER.x() + CENTER.z(), 0, MARGIN, MARGIN);
    }
}
