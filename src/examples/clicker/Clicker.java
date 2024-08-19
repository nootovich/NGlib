package examples.clicker;

import nootovich.nglib.*;

import static examples.clicker.Main.clicks;

public class Clicker extends NGMain {

    public static final int     CENTER_MARGIN = 50;
    public static       NGVec4i CENTER        = new NGVec4i(CENTER_MARGIN, 0, w - CENTER_MARGIN * 2, h);

    public void main() {
        setTickRate(60);
        setFrameRate(60);
        createWindow(400, 300, new ClickerRenderer());
        start();
    }

    @Override
    public void onLMBPressed(NGVec2i pos) {
        if (pos.isInside(CENTER)) clicks++;
    }

    @Override
    public void onWindowResize(int w, int h) {
        CENTER = new NGVec4i(CENTER_MARGIN, 0, w - CENTER_MARGIN * 2, h);
    }
}
