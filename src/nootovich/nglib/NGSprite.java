package nootovich.nglib;

import java.awt.Color;
import java.util.ArrayList;

@Deprecated// until I make code generation for it
public class NGSprite {

    public boolean visible = true;

    public NGVec2i      pos;
    public NGVec2i      size;
    public int          extra       = 0;
    public Color        color;
    public Color        borderColor = new Color(0, true);
    public NGSpriteType type        = NGSpriteType.RECT;

    public ArrayList<NGAnimation> anims = new ArrayList<>();

    public NGSprite parent     = null;
    public boolean  inheritPos = true;

    public ArrayList<NGSprite> children = new ArrayList<>();

    public enum NGSpriteType {
        LINE,
        RECT, RECT_CENTERED,
        CIRCLE, CIRCLE_CENTERED
    }

    public NGSprite(int x, int y, int w, int h, Color color) {
        this.pos   = new NGVec2i(x, y);
        this.size  = new NGVec2i(w, h);
        this.color = color;
    }

    public NGSprite(int x, int y, int w, int h, Color color, Color borderColor) {
        this.pos         = new NGVec2i(x, y);
        this.size        = new NGVec2i(w, h);
        this.color       = color;
        this.borderColor = borderColor;
    }

    public NGSprite(NGVec2i pos, int size, Color color) {
        this.pos   = pos;
        this.size  = new NGVec2i(size);
        this.color = color;
    }

    public NGSprite(NGVec2i pos, NGVec2i size, Color color) {
        this.pos   = pos;
        this.size  = size;
        this.color = color;
    }

    public NGSprite(NGVec2i pos, int size, Color color, NGSpriteType type) {
        this.pos   = pos;
        this.size  = new NGVec2i(size);
        this.color = color;
        this.type  = type;
    }

    public NGSprite(NGVec2i pos, NGVec2i size, Color color, NGSpriteType type) {
        this.pos   = pos;
        this.size  = size;
        this.color = color;
        this.type  = type;
    }

    public NGSprite(NGVec2i pos, NGVec2i size, Color color, Color borderColor) {
        this.pos         = pos;
        this.size        = size;
        this.color       = color;
        this.borderColor = borderColor;
    }

    public void update(float dt) {
        for (NGAnimation anim: anims) anim.update(dt);
        for (NGSprite child: children) child.update(dt);
    }

    public void draw(NGGraphics g) {
        if (visible) switch (type) {
            case LINE -> g.drawRoundedLine(pos.x, pos.y, pos.x + size.x, pos.y + size.y, color, extra);
            case RECT -> g.drawRectWithBorder(getPos(), size, color, borderColor);
            case CIRCLE -> g.drawCircle(getPos(), size.x, color);
            case CIRCLE_CENTERED -> g.drawCircleCentered(getPos(), size.x, color);
            default -> NGUtils.error("Not implemented");
        }
        for (NGSprite child: children) child.draw(g);
    }

    public NGVec2i getPos() {
        NGVec2i  result    = anims.isEmpty() ? pos : anims.getFirst().state.toInt();
        NGSprite curSprite = this;
        while (curSprite.inheritPos && curSprite.parent != null) {
            curSprite = curSprite.parent;
            result    = result.add(curSprite.getPos());
        }
        return result;
    }

    public void addChild(NGSprite child) {
        children.add(child);
        child.parent = this;
    }
}
