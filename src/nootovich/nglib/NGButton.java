package nootovich.nglib;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class NGButton {

    public Image   img;
    public NGVec2i pos;
    public NGVec2i size;
    public NGVec4i area;
    public int     state = 0;

    public NGButton(Image img, int x, int y, int w, int h) {
        this.img  = img;
        this.pos  = new NGVec2i(x, y);
        this.size = new NGVec2i(w, h);
        this.area = new NGVec4i(x, y, w, h);
    }

    public NGButton(String imgPath, int x, int y, int w, int h) {
        try {
            if (imgPath.isEmpty()) this.img = null;
            else this.img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.pos  = new NGVec2i(x, y);
        this.size = new NGVec2i(w, h);
        this.area = new NGVec4i(x, y, w, h);
    }

    abstract public void performAction();

    public void setPos(NGVec2i pos) {
        this.pos  = pos;
        this.area = new NGVec4i(pos.x(), pos.y(), size.w(), size.h());
    }
}
