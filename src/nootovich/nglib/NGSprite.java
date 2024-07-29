package nootovich.nglib;

import java.awt.Color;

public class NGSprite {

    public Color[][] pixels;

    public NGSprite(int w, int h) {
        pixels = new Color[h][w];
        for (int i = 0; i < h; i++) for (int j = 0; j < w; j++) pixels[i][j] = new Color(0, 0, 0, 0);
    }

    public NGSprite(Color[][] pixels) {
        this.pixels = pixels;
    }

    public void setPixel(int x, int y, Color color) {
        pixels[y][x] = color;
    }

    public NGColor getPixel(int x, int y) {
        return new NGColor(pixels[y][x]);
    }
}
