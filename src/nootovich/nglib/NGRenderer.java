package nootovich.nglib;

import java.awt.Font;

public abstract class NGRenderer extends NGHotReloadable {

    public static Font font;

    public abstract void render(NGGraphics g);
    public abstract void reset();
}
