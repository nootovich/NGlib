package examples.ui_test;

import nootovich.nglib.NGMain;

public class UITest extends NGMain {

    public static long startTime = System.currentTimeMillis();

    public void main() {
        setTickRate(10);
        setFrameRate(60);
        createWindow(1280, 720, new UITestRenderer());
        start();
    }
}
