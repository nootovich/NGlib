package nootovich.nglib;

import java.awt.Color;
import java.util.ArrayList;

public class NGSprite {

    public NGVec2i       pos;
    public NGVec2i       size;
    public Color         color;
    public Color         borderColor = new Color(0, true);
    public NGSpriteShape shape;

    public ArrayList<NGAnimation> anims    = new ArrayList<>();
    public ArrayList<NGSprite>    children = new ArrayList<>();

    public enum NGSpriteShape {RECT, CIRCLE}

    public NGSprite(int x, int y, int w, int h, Color color) {
        this.pos   = new NGVec2i(x, y);
        this.size  = new NGVec2i(w, h);
        this.color = color;
        this.shape = NGSpriteShape.RECT;
    }

    public NGSprite(int x, int y, int w, int h, Color color, Color borderColor) {
        this.pos         = new NGVec2i(x, y);
        this.size        = new NGVec2i(w, h);
        this.color       = color;
        this.borderColor = borderColor;
        this.shape       = NGSpriteShape.RECT;
    }

    public NGSprite(NGVec2i pos, NGVec2i size, Color color) {
        this.pos   = pos;
        this.size  = size;
        this.color = color;
        this.shape = NGSpriteShape.RECT;
    }

    public NGSprite(NGVec2i pos, NGVec2i size, Color color, Color borderColor) {
        this.pos         = pos;
        this.size        = size;
        this.color       = color;
        this.borderColor = borderColor;
        this.shape       = NGSpriteShape.RECT;
    }

    public void update(float dt) {
        for (NGAnimation anim: anims) anim.update(dt);
        for (NGSprite child: children) child.update(dt);
    }
}
