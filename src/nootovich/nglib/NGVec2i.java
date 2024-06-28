package nootovich.nglib;

public class NGVec2i {

    public int x = 0;
    public int y = 0;

    public NGVec2i() {
    }

    public NGVec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public NGVec2i(NGVec2f pos) {
        this.x = (int) pos.x;
        this.y = (int) pos.y;
    }

    public NGVec2i add(NGVec2i other) {
        return new NGVec2i(x + other.x, y + other.y);
    }

    public NGVec2i sub(NGVec2i other) {
        return new NGVec2i(x - other.x, y - other.y);
    }

    public NGVec2i scale(float factor) {
        return new NGVec2i((int) (x * factor), (int) (y * factor));
    }

    public NGVec2i lerp(NGVec2i other, float n) {
        return this.add(other.sub(this).scale(n));
    }

    @Override
    public String toString() {
        return "(%d, %d)".formatted(x, y);
    }

}
