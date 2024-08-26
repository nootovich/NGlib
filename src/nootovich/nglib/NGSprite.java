package nootovich.nglib;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static nootovich.nglib.NGAnimation.NGAnimationType.POS;
import static nootovich.nglib.NGAnimation.NGAnimationType.SIZE;

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
        CIRCLE, CIRCLE_CENTERED, CIRCLE_BORDER;
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

    public NGSprite(NGVec2i pos, NGVec2i size, Color color, Color borderColor, NGSpriteType type) {
        this.pos         = pos;
        this.size        = size;
        this.color       = color;
        this.borderColor = borderColor;
        this.type        = type;
    }

    public void update(float dt) {
        for (int i = 0; i < anims.size(); i++) {
            if (!anims.get(i).active) anims.remove(i--);
            else anims.get(i).update(dt);
        }
        for (NGSprite child: children) child.update(dt);
    }

    void draw(NGRenderer r) {
        if (visible) switch (type) {
            case LINE -> r.drawRoundedLine(getPos(), getPos().add(getSize()), color, extra);
            case RECT -> r.drawRectWithBorder(getPos(), getSize(), color, borderColor);
            case CIRCLE -> r.drawCircle(getPos(), getSize().width(), color);
            case CIRCLE_CENTERED -> r.drawCircleCentered(getPos(), getSize().width(), color);
            case CIRCLE_BORDER -> r.drawCircleBorder(getPos(), getSize().width(), color, extra);
            default -> NGUtils.error("Not implemented");
        }
        for (NGSprite child: children) child.draw(r);
    }

    public NGVec2i getPos() {
        if (anims.isEmpty()) return inheritPos && parent != null ? pos.add(parent.getPos()) : pos;
        List<NGAnimation> posAnims = anims.stream().filter((anim) -> anim.type == POS).toList();
        NGVec2i           result;
        if (posAnims.isEmpty()) result = inheritPos && parent != null ? pos.add(parent.getPos()) : pos;
        else result = new NGVec2i();
        for (NGAnimation anim: anims) {
            if (anim.type == POS) result = result.add(anim.cur.toInt());
            else if (anim.type == SIZE) result = result.add(anim.end.toInt().sub(getSize()).divide(2f));
            else NGUtils.error("Not implemented");
        }
        if (result.equals(new NGVec2i())) return pos;
        return result;
    }

    public NGVec2i getSize() {
        if (anims.isEmpty()) return size;
        List<NGAnimation> sizeAnims = anims.stream().filter((anim) -> anim.type == SIZE).toList();
        if (sizeAnims.isEmpty()) return size;
        return sizeAnims.getFirst().cur.toInt();
    }

    public void addChild(NGSprite child) {
        children.add(child);
        child.parent = this;
    }
}
