package nootovich.nglib;

public class NGVec2f {

    public float x = 0;
    public float y = 0;

    public NGVec2f() {
    }

    public NGVec2f(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public NGVec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public NGVec2f add(NGVec2f other) {
        return new NGVec2f(x + other.x, y + other.y);
    }

    public NGVec2f add(float n) {
        return new NGVec2f(x + n, y + n);
    }

    public NGVec2f sub(NGVec2f other) {
        return new NGVec2f(x - other.x, y - other.y);
    }

    public NGVec2f sub(float n) {
        return new NGVec2f(x - n, y - n);
    }

    public NGVec2f scale(float factor) {
        return new NGVec2f(x * factor, y * factor);
    }

    public NGVec2f lerp(NGVec2f other, float n) {
        return this.add(other.sub(this).scale(n));
    }

    @Override
    public String toString() {
        return "(%.3f, %.3f)".formatted(x, y);
    }

}
