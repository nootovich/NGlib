package nootovich.nglib;

import java.awt.*;
import java.awt.image.BufferedImage;

// TODO: NGColor

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

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        g2d.setColor(color);
        g2d.drawLine(x1, y1, x2, y2);
    }

    public void drawRect(int x, int y, int w, int h, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x, y, w, h);
    }

    public void drawRect(double x, double y, double w, double h, Color color) {
        g2d.setColor(color);
        g2d.fillRect((int) (x * gW), (int) (y * gH), (int) (w * gW), (int) (h * gH));
    }

    public void drawRectBorder(int x, int y, int w, int h, Color color) {
        g2d.setColor(color);
        g2d.drawRect(x, y, w, h);
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

    // TODO: drawCircleCentered()

    public void drawText(String text, int x, int y, Color color) {
        g2d.setColor(color);
        g2d.drawString(text, x, y);
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
