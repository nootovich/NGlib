import java.awt.*;
import java.awt.image.BufferedImage;

// TODO: NGColor

public class NGGraphics {

    private final int gX, gY;
    private int gW, gH;
    private BufferedImage buffer;
    private Graphics2D    g2d;

    NGGraphics(Container c) {
        gX     = c.getInsets().left;
        gY     = c.getInsets().top;
        gW     = c.getComponent(0).getWidth();
        gH     = c.getComponent(0).getHeight();
        buffer = new BufferedImage(gW, gH, BufferedImage.TYPE_INT_RGB);
        g2d    = buffer.createGraphics();
    }

    public void resize(int w, int h) {
        gW     = w;
        gH     = h;
        buffer = new BufferedImage(gW, gH, BufferedImage.TYPE_INT_RGB);
        g2d    = buffer.createGraphics();
    }

    public void drawCircle(int x, int y, int radius, Color color) {
        g2d.setColor(color);
        g2d.fillOval(x, y, radius, radius);
    }

    // TODO: drawCircleCentered()

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

    public void displayOn(Container c) {
        c.getGraphics().drawImage(buffer, gX, gY, null);
    }
}
