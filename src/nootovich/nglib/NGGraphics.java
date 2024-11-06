package nootovich.nglib;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

@SuppressWarnings("unused")
public class NGGraphics {

    // TODO: merge `NGGraphics` with `NGRenderer`

    private static final boolean DEBUG = false;

    private NGVec2i       pos;
    private NGVec2i       size;
    private BufferedImage buffer;
    public  Graphics2D    g2d;

    public NGGraphics(Container c) {
        Insets ins = c.getInsets();
        pos    = new NGVec2i(ins.left, ins.top);
        size   = new NGVec2i(c.getComponent(0).getWidth(), c.getComponent(0).getHeight());
        buffer = new BufferedImage(size.w(), size.h(), BufferedImage.TYPE_INT_RGB);
        System.out.println();
        updateGraphics();
    }

    public void updateGraphics() {
        g2d = buffer.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
    }

    public void resize(int w, int h) {
        size.set(w, h);
        Font temp = g2d.getFont();
        buffer = new BufferedImage(size.w(), size.h(), BufferedImage.TYPE_INT_RGB);
        updateGraphics();
        setFont(temp);
    }

    public void drawPixel(int x, int y, Color color) {
        drawRect(x, y, 1, 1, color);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        g2d.setColor(color);
        g2d.drawLine(x1, y1, x2, y2);
    }

    public void drawLine(NGVec2i pos1, NGVec2i pos2, Color color) {
        drawLine(pos1.x(), pos1.y(), pos2.x(), pos2.y(), color);
    }

    public void drawRoundedLine(int x1, int y1, int x2, int y2, Color color, int thiccness) {
        g2d.setStroke(new BasicStroke(thiccness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        drawLine(x1, y1, x2, y2, color);
        g2d.setStroke(new BasicStroke());
    }

    public void drawRoundedLine(NGVec2i pos1, NGVec2i pos2, Color color, int thiccness) {
        drawRoundedLine(pos1.x(), pos1.y(), pos2.x(), pos2.y(), color, thiccness);
    }

    public void drawRect(int x, int y, int w, int h, Color color) {
        g2d.setColor(color);
        if (w < 0) {
            x += w;
            w = -w;
        }
        if (h < 0) {
            y += h;
            h = -h;
        }
        g2d.fillRect(x, y, w, h);
    }

    public void drawRect(int x, int y, int size, Color color) {
        drawRect(x, y, size, size, color);
    }

    public void drawRect(NGVec2i pos, NGVec2i size, Color color) {
        drawRect(pos.x(), pos.y(), size.w(), size.h(), color);
    }

    public void drawRect(NGVec4i rect, Color color) {
        drawRect(rect.x(), rect.y(), rect.z(), rect.w(), color);
    }

    public void drawRect(NGVec2f fpos, NGVec2f fsize, Color color) {
        drawRect(fpos.toInt(), fsize.toInt(), color);
    }

    public void drawRect(NGVec2i pos, int size, Color color) {
        drawRect(pos.x(), pos.y(), size, color);
    }

    public void drawRect(NGVec2f fpos, int size, Color color) {
        drawRect(fpos.toInt(), size, color);
    }

    public void drawRectBorder(int x, int y, int w, int h, Color color) {
        g2d.setColor(color);
        if (w < 0) {
            x += w;
            w = -w;
        }
        if (h < 0) {
            y += h;
            h = -h;
        }
        g2d.drawRect(x, y, w, h);
    }

    public void drawRectBorder(int x, int y, int size, Color color) {
        drawRectBorder(x, y, size, size, color);
    }

    public void drawRectBorder(NGVec2i pos, NGVec2i size, Color color) {
        drawRectBorder(pos.x(), pos.y(), size.w(), size.h(), color);
    }

    public void drawRectBorder(NGVec2i pos, NGVec2i size, Color color, int thiccness) {
        g2d.setStroke(new BasicStroke(thiccness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        drawRectBorder(pos, size, color);
        g2d.setStroke(new BasicStroke());
    }

    public void drawRectBorder(NGVec4i rect, Color color) {
        drawRectBorder(rect.x(), rect.y(), rect.z(), rect.w(), color);
    }

    public void drawRectBorder(NGVec2f fpos, NGVec2f fsize, Color color) {
        drawRectBorder(fpos.toInt(), fsize.toInt(), color);
    }

    public void drawRectBorder(NGVec2i pos, int size, Color color) {
        drawRectBorder(pos.x(), pos.y(), size, color);
    }

    public void drawRectBorder(NGVec2f fpos, int size, Color color) {
        drawRectBorder(fpos.toInt(), size, color);
    }

    public void drawRectWithBorder(NGVec2i pos, NGVec2i size, Color color, Color borderColor) {
        drawRect(pos, size, color);
        drawRectBorder(pos, size, borderColor);
    }

    public void drawRoundRect(int x, int y, int w, int h, int aw, int ah, Color color) {
        g2d.setColor(color);
        g2d.fillRoundRect(x, y, w, h, aw, ah);
    }

    public void drawRoundRectBorder(int x, int y, int w, int h, int aw, int ah, Color color) {
        g2d.setColor(color);
        g2d.drawRoundRect(x, y, w, h, aw, ah);
    }

    public void drawCircle(int x, int y, int diameter, Color color) {
        g2d.setColor(color);
        g2d.fillOval(x, y, diameter, diameter);
    }

    public void drawCircle(NGVec2i pos, int diameter, Color color) {
        drawCircle(pos.x(), pos.y(), diameter, color);
    }

    public void drawCircle(NGVec2f fpos, int diameter, Color color) {
        drawCircle(fpos.toInt(), diameter, color);
    }

    public void drawCircleCentered(int x, int y, int diameter, Color color) {
        drawCircle(x - diameter / 2, y - diameter / 2, diameter, color);
    }

    public void drawCircleCentered(NGVec2i pos, int diameter, Color color) {
        drawCircleCentered(pos.x(), pos.y(), diameter, color);
    }

    public void drawCircleCentered(NGVec2f fpos, int diameter, Color color) {
        drawCircleCentered(fpos.toInt(), diameter, color);
    }

    public void drawCircleBorder(int x, int y, int diameter, Color color) {
        g2d.setColor(color);
        g2d.drawOval(x, y, diameter, diameter);
    }

    public void drawCircleBorder(int x, int y, int diameter, Color color, int thiccness) {
        g2d.setStroke(new BasicStroke(thiccness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        drawCircleBorder(x, y, diameter, color);
        g2d.setStroke(new BasicStroke());
    }

    public void drawCircleBorder(NGVec2i pos, int diameter, Color color, int thiccness) {
        drawCircleBorder(pos.x(), pos.y(), diameter, color, thiccness);
    }

    // A BIG TODO:
    public void drawGradient(NGVec2i pos, NGVec2i size, int direction, Color color1, Color color2) {
        // NOTE: direction implementation is temporary
        NGVec4i colorVec1 = new NGVec4i(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha());
        NGVec4i colorVec2 = new NGVec4i(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha());
        if (direction == 0) { // TO THE TOP
            for (int y = pos.y(); y < pos.y() + size.h(); y++) {
                NGVec4i colorVec3 = colorVec2.lerp(colorVec1, (float) (y - pos.y()) / size.h());
                Color   color     = new Color(colorVec3.x(), colorVec3.y(), colorVec3.z(), colorVec3.w());
                drawLine(pos.x(), y, pos.x() + size.w(), y, color);
            }
        } else if (direction == 1) { // TO THE RIGHT
            for (int x = pos.x(); x < pos.x() + size.w(); x++) {
                NGVec4i colorVec3 = colorVec1.lerp(colorVec2, (float) (x - pos.x()) / size.w());
                Color   color     = new Color(colorVec3.x(), colorVec3.y(), colorVec3.z(), colorVec3.w());
                drawLine(x, pos.y(), x, pos.y() + size.h(), color);
            }
        } else if (direction == 2) { // TO THE BOTTOM
            for (int y = pos.y(); y < pos.y() + size.h(); y++) {
                NGVec4i colorVec3 = colorVec1.lerp(colorVec2, (float) (y - pos.y()) / size.h());
                Color   color     = new Color(colorVec3.x(), colorVec3.y(), colorVec3.z(), colorVec3.w());
                drawLine(pos.x(), y, pos.x() + size.w(), y, color);
            }
        } else if (direction == 3) { // TO THE LEFT
            for (int x = pos.x(); x < pos.x() + size.w(); x++) {
                NGVec4i colorVec3 = colorVec2.lerp(colorVec1, (float) (x - pos.x()) / size.w());
                Color   color     = new Color(colorVec3.x(), colorVec3.y(), colorVec3.z(), colorVec3.w());
                drawLine(x, pos.y(), x, pos.y() + size.h(), color);
            }
        } else NGUtils.error("no. bad.");
    }

    public void drawGradient(NGVec2i pos, NGVec2i size, int direction, Color color) {
        drawGradient(pos, size, direction, color, new Color(0, true));
    }

    // public void drawPixelSprite(int x, int y, int w, int h, NGSprite pixelSprite) {
    //     // NOTE: Temporary naive approach. Just enough to get things going
    //     if (pixelSprite.pixels.length == 0 || pixelSprite.pixels[0].length == 0) NGUtils.error("Invalid `NGPixelSprite`");
    //     int sw = pixelSprite.pixels.length;
    //     int sh = pixelSprite.pixels[0].length;
    //     for (int dy = 0; dy < h; dy++) {
    //         for (int dx = 0; dx < w; dx++) {
    //             int sx = dx * sw / w;
    //             int sy = dy * sh / h;
    //             drawPixel(dx, dy, pixelSprite.getPixel(sx, sy));
    //         }
    //     }
    // }

    public void drawSprite(NGSprite sprite) {
        sprite.draw(this);
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

    public void setFont(Font font) {
        g2d.setFont(font);
    }

    public void drawText(String text, int x, int y, Color color) {
        g2d.setColor(color);
        g2d.drawString(text, x, y);
    }

    public void drawText(String text, int x, int y, Color color, int size) {
        g2d.setColor(color);
        g2d.setFont(g2d.getFont().deriveFont((float) size));
        g2d.drawString(text, x, y);
    }

    public void drawTextCentered(String text, int x, int y, Color color) {
        FontMetrics metrics = g2d.getFontMetrics();
        drawText(text, x - metrics.stringWidth(text) / 2, y + metrics.getDescent(), color);
    }

    public void drawTextCentered(int num, int x, int y, Color color) {
        drawTextCentered(String.valueOf(num), x, y, color);
    }

    public void drawTextCentered(String text, int x, int y, Color color, int size) {
        g2d.setFont(g2d.getFont().deriveFont((float) size));
        drawTextCentered(text, x, y, color);
    }

    public void drawTextCentered(int num, int x, int y, Color color, int size) {
        g2d.setFont(g2d.getFont().deriveFont((float) size));
        drawTextCentered(String.valueOf(num), x, y, color);
    }

    public void displayOn(Container c) {
        c.getGraphics().drawImage(buffer, pos.x(), pos.y(), null);
    }

    public void drawImage(String path, int x, int y, int w, int h) {
        try {
            Image img = ImageIO.read(new File(path));
            g2d.drawImage(img, x, y, w, h, null);
        } catch (IOException e) {
        }
    }

    public void drawImage(Image img, NGVec2i pos, NGVec2i size) {
        g2d.drawImage(img, pos.x(), pos.y(), size.w(), size.h(), null);
    }

    public void drawButton(NGButton button) {
        drawImage(button.img, button.pos, button.size);
        if (DEBUG) drawRect(button.pos, button.size, new Color(0x69FF0000, true));
    }
}
