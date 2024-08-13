package nootovich.nglib;

public class NGVec2f {

    public float x = 0, y = 0;

    public NGVec2f() {
    }

    public NGVec2f(float n) {
        this.x = n;
        this.y = n;
    }

    public NGVec2f(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public NGVec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static NGVec2f[] createArray(float[][] positions) {
        NGVec2f[] result = new NGVec2f[positions.length];
        for (int i = 0; i < positions.length; i++) {
            float[] pos = positions[i];
            if (pos.length != 2) NGUtils.error("Invalid amount of arguments. Expected 2 but got " + pos.length);
            result[i] = new NGVec2f(pos[0], pos[1]);
        }
        return result;
    }

    public NGVec2f add(NGVec2f other) {
        return new NGVec2f(x + other.x, y + other.y);
    }

    public NGVec2f add(float n) {
        return new NGVec2f(x + n, y + n);
    }

    public NGVec2f add(float dx, float dy) {
        return new NGVec2f(x + dx, y + dy);
    }

    public NGVec2f sub(NGVec2f other) {
        return new NGVec2f(x - other.x, y - other.y);
    }

    public NGVec2f sub(float n) {
        return new NGVec2f(x - n, y - n);
    }

    public NGVec2f sub(float dx, float dy) {
        return new NGVec2f(x - dx, y - dy);
    }

    public NGVec2f scale(float factor) {
        return new NGVec2f(x * factor, y * factor);
    }

    public NGVec2f lerp(NGVec2f other, float n) {
        return this.add(other.sub(this).scale(n));
    }

    public NGVec2i toInt() {
        return new NGVec2i(x, y);
    }

    public boolean equals(NGVec2f other) {
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return "(%.3f, %.3f)".formatted(x, y);
    }
}
