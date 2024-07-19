package nootovich.nglib;

import java.awt.*;

public abstract class NGRenderer extends NGHotReloadable {

    public Font defaultFont;

    public abstract void render(NGGraphics g);
}
