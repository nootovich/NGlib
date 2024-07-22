package nootovich.nglib;

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;

public class NGEventHandler extends NGHotReloadable implements AWTEventListener {

    private static final long AWT_COMPONENT             = 0b00000000000000000001;
    private static final long AWT_CONTAINER             = 0b00000000000000000010;
    private static final long AWT_FOCUS                 = 0b00000000000000000100;
    private static final long AWT_KEY                   = 0b00000000000000001000;
    private static final long AWT_MOUSE                 = 0b00000000000000010000;
    private static final long AWT_MOUSE_MOTION          = 0b00000000000000100000;
    private static final long AWT_WINDOW                = 0b00000000000001000000;
    private static final long AWT_ACTION                = 0b00000000000010000000;
    private static final long AWT_ADJUSTMENT            = 0b00000000000100000000;
    private static final long AWT_ITEM                  = 0b00000000001000000000;
    private static final long AWT_TEXT                  = 0b00000000010000000000;
    private static final long AWT_INPUT_METHOD          = 0b00000000100000000000;
    private static final long AWT_INPUT_METHODS_ENABLED = 0b00000001000000000000;
    private static final long AWT_PAINT                 = 0b00000010000000000000;
    private static final long AWT_INVOCATION            = 0b00000100000000000000;
    private static final long AWT_HIERARCHY             = 0b00001000000000000000;
    private static final long AWT_HIERARCHY_BOUNDS      = 0b00010000000000000000;
    private static final long AWT_MOUSE_WHEEL           = 0b00100000000000000000;
    private static final long AWT_WINDOW_STATE          = 0b01000000000000000000;
    private static final long AWT_WINDOW_FOCUS          = 0b10000000000000000000;

    public static final long KEY    = AWT_KEY;
    public static final long MOUSE  = AWT_MOUSE | AWT_MOUSE_MOTION | AWT_MOUSE_WHEEL;
    public static final long WINDOW = AWT_WINDOW_STATE | AWT_COMPONENT;

    @Override
    public void eventDispatched(AWTEvent event) { }
}
