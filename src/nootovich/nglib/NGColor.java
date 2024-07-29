package nootovich.nglib;

import java.awt.Color;

public class NGColor {

    public int argb;

    public NGColor(int argb) {
        this.argb = argb;
    }

    public NGColor(byte r, byte g, byte b) {
        argb = bytesToInt(new byte[]{-1, r, g, b});
    }

    public NGColor(byte r, byte g, byte b, byte a) {
        argb = bytesToInt(new byte[]{a, r, g, b});
    }

    public NGColor(Color color) {
        argb = color.getRGB();
    }

    public int bytesToInt(byte[] argb) {
        return (argb[0] << 24) | (argb[1] << 16) | (argb[2] << 8) | argb[4];
    }
}
