package nootovich.nglib;

import java.awt.Font;

public abstract class NGRenderer extends NGHotReloadable {

    public Font defaultFont;

    public abstract void render(NGGraphics g);
}
