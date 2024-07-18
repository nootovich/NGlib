package nootovich.nglib;

import java.awt.*;
import java.awt.image.BufferedImage;

// TODO: NGColor

@SuppressWarnings("unused")
public class NGGraphics {

    private final int gX, gY;
    private int gW, gH;
    private BufferedImage buffer;
    private Graphics2D    g2d;

    public NGGraphics(Container c) {
        gX     = c.getInsets().left;
        gY     = c.getInsets().top;
        gW     = c.getComponent(0).getWidth();
        gH     = c.getComponent(0).getHeight();
        buffer = new BufferedImage(gW, gH, BufferedImage.TYPE_INT_RGB);
        g2d    = buffer.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void resize(int w, int h) {
        gW     = w;
        gH     = h;
        buffer = new BufferedImage(gW, gH, BufferedImage.TYPE_INT_RGB);
        g2d    = buffer.createGraphics();
    }

    public void drawPixel(int x, int y, Color color) {
        drawRect(x, y, 1, 1, color);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        g2d.setColor(color);
        g2d.drawLine(x1, y1, x2, y2);
    }

    public void drawRect(int x, int y, int w, int h, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x, y, w, h);
    }

    public void drawRect(int x, int y, int size, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x, y, size, size);
    }

    public void drawRect(NGVec2i pos, NGVec2i size, Color color) {
        g2d.setColor(color);
        g2d.fillRect(pos.x, pos.y, size.x, size.y);
    }

    public void drawRect(NGVec4i rect, Color color) {
        g2d.setColor(color);
        g2d.fillRect(rect.x, rect.y, rect.z, rect.w);
    }

    public void drawRect(NGVec2f fpos, NGVec2f fsize, Color color) {
        g2d.setColor(color);
        NGVec2i pos  = fpos.toInt();
        NGVec2i size = fsize.toInt();
        g2d.fillRect(pos.x, pos.y, size.x, size.y);
    }

    public void drawRect(NGVec2i pos, int size, Color color) {
        g2d.setColor(color);
        g2d.fillRect(pos.x, pos.y, size, size);
    }

    public void drawRect(NGVec2f fpos, int size, Color color) {
        g2d.setColor(color);
        NGVec2i pos = fpos.toInt();
        g2d.fillRect(pos.x, pos.y, size, size);
    }

    public void drawRectBorder(int x, int y, int w, int h, Color color) {
        g2d.setColor(color);
        g2d.drawRect(x, y, w, h);
    }

    public void drawRectBorder(int x, int y, int size, Color color) {
        g2d.setColor(color);
        g2d.drawRect(x, y, size, size);
    }

    public void drawRectBorder(NGVec2i pos, NGVec2i size, Color color) {
        g2d.setColor(color);
        g2d.drawRect(pos.x, pos.y, size.x, size.y);
    }

    public void drawRectBorder(NGVec4i rect, Color color) {
        g2d.setColor(color);
        g2d.drawRect(rect.x, rect.y, rect.z, rect.w);
    }

    public void drawRectBorder(NGVec2f fpos, NGVec2f fsize, Color color) {
        g2d.setColor(color);
        NGVec2i pos  = fpos.toInt();
        NGVec2i size = fsize.toInt();
        g2d.drawRect(pos.x, pos.y, size.x, size.y);
    }

    public void drawRectBorder(NGVec2i pos, int size, Color color) {
        g2d.setColor(color);
        g2d.drawRect(pos.x, pos.y, size, size);
    }

    public void drawRectBorder(NGVec2f fpos, int size, Color color) {
        g2d.setColor(color);
        NGVec2i pos = fpos.toInt();
        g2d.drawRect(pos.x, pos.y, size, size);
    }

    public void drawRoundRect(int x, int y, int w, int h, int aw, int ah, Color color) {
        g2d.setColor(color);
        g2d.fillRoundRect(x, y, w, h, aw, ah);
    }

    public void drawRoundRectBorder(int x, int y, int w, int h, int aw, int ah, Color color) {
        g2d.setColor(color);
        g2d.drawRoundRect(x, y, w, h, aw, ah);
    }

    public void drawCircle(int x, int y, int radius, Color color) {
        g2d.setColor(color);
        g2d.fillOval(x, y, radius, radius);
    }

    public void drawCircle(NGVec2i pos, int radius, Color color) {
        g2d.setColor(color);
        g2d.fillOval(pos.x, pos.y, radius, radius);
    }

    public void drawCircle(NGVec2f fpos, int radius, Color color) {
        g2d.setColor(color);
        NGVec2i pos = fpos.toInt();
        g2d.fillOval(pos.x, pos.y, radius, radius);
    }

    public void drawCircleCentered(int x, int y, int radius, Color color) {
        drawCircle(x - radius / 2, y - radius / 2, radius, color);
    }

    public void drawCircleCentered(NGVec2i pos, int radius, Color color) {
        drawCircle(pos.x - radius / 2, pos.y - radius / 2, radius, color);
    }

    public void drawCircleCentered(NGVec2f fpos, int radius, Color color) {
        NGVec2i pos = fpos.toInt();
        drawCircle(pos.x - radius / 2, pos.y - radius / 2, radius, color);
    }

    public void drawText(String text, int x, int y, Color color) {
        g2d.setColor(color);
        g2d.drawString(text, x, y);
    }

    public void drawPixelSprite(int x, int y, int w, int h, NGSprite pixelSprite) {
        // TODO: Temporary naive approach. Just enough to get things going
        if (pixelSprite.pixels.length == 0 || pixelSprite.pixels[0].length == 0) NGUtils.error("Invalid `NGPixelSprite`");
        int sw = pixelSprite.pixels.length;
        int sh = pixelSprite.pixels[0].length;
        for (int dy = 0; dy < h; dy++) {
            for (int dx = 0; dx < w; dx++) {
                int sx = dx * sw / w;
                int sy = dy * sh / h;
                drawPixel(dx, dy, pixelSprite.getPixel(sx, sy));
            }
        }
    }

    public void setStroke(Stroke stroke) {
        g2d.setStroke(stroke);
    }

    public void setClip(int x, int y, int w, int h) {
        g2d.setClip(x, y, w, h);
    }

    public void resetClip() {
        g2d.setClip(null);
    }

    public void displayOn(Container c) {
        c.getGraphics().drawImage(buffer, gX, gY, null);
    }
}
