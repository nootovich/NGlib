package nootovich.nglib;

import java.awt.*;
import java.awt.event.AWTEventListener;

public class NGEventHandler extends NGHotReloadable implements AWTEventListener {

    public static final long COMPONENT             = 0b00000000000000000001;
    public static final long CONTAINER             = 0b00000000000000000010;
    public static final long FOCUS                 = 0b00000000000000000100;
    public static final long KEY                   = 0b00000000000000001000;
    public static final long MOUSE                 = 0b00000000000000010000;
    public static final long MOUSE_MOTION          = 0b00000000000000100000;
    public static final long WINDOW                = 0b00000000000001000000;
    public static final long ACTION                = 0b00000000000010000000;
    public static final long ADJUSTMENT            = 0b00000000000100000000;
    public static final long ITEM                  = 0b00000000001000000000;
    public static final long TEXT                  = 0b00000000010000000000;
    public static final long INPUT_METHOD          = 0b00000000100000000000;
    public static final long INPUT_METHODS_ENABLED = 0b00000001000000000000;
    public static final long PAINT                 = 0b00000010000000000000;
    public static final long INVOCATION            = 0b00000100000000000000;
    public static final long HIERARCHY             = 0b00001000000000000000;
    public static final long HIERARCHY_BOUNDS      = 0b00010000000000000000;
    public static final long MOUSE_WHEEL           = 0b00100000000000000000;
    public static final long WINDOW_STATE          = 0b01000000000000000000;
    public static final long WINDOW_FOCUS          = 0b10000000000000000000;
    public static final long DEFAULT_IO            = KEY | MOUSE | MOUSE_MOTION | MOUSE_WHEEL;
    public static final long DEFAULT_WINDOW        = COMPONENT | CONTAINER | FOCUS | WINDOW | WINDOW_STATE | WINDOW_FOCUS;

    @Override
    public void eventDispatched(AWTEvent event) { }
}
