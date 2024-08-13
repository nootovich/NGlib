package nootovich.nglib;

import java.awt.Point;

public class NGVec2i {

    public int x = 0, y = 0;

    public NGVec2i() {
    }

    public NGVec2i(int n) {
        this.x = n;
        this.y = n;
    }

    public NGVec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public NGVec2i(float x, float y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public NGVec2i(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public static NGVec2i[] createArray(int[][] positions) {
        NGVec2i[] result = new NGVec2i[positions.length];
        for (int i = 0; i < positions.length; i++) {
            int[] pos = positions[i];
            if (pos.length != 2) NGUtils.error("Invalid amount of arguments. Expected 2 but got " + pos.length);
            result[i] = new NGVec2i(pos[0], pos[1]);
        }
        return result;
    }

    public NGVec2i add(NGVec2i other) {
        return new NGVec2i(x + other.x, y + other.y);
    }

    public NGVec2i add(int n) {
        return new NGVec2i(x + n, y + n);
    }

    public NGVec2i add(int dx, int dy) {
        return new NGVec2i(x + dx, y + dy);
    }

    public NGVec2i add(float dx, float dy) {
        return new NGVec2i(x + dx, y + dy);
    }

    public NGVec2i sub(NGVec2i other) {
        return new NGVec2i(x - other.x, y - other.y);
    }

    public NGVec2i sub(int n) {
        return new NGVec2i(x - n, y - n);
    }

    public NGVec2i sub(int dx, int dy) {
        return new NGVec2i(x - dx, y - dy);
    }

    public NGVec2i scale(float factor) {
        return new NGVec2i(x * factor, y * factor);
    }

    public NGVec2i divide(float factor) {
        return new NGVec2i(x / factor, y / factor);
    }

    public NGVec2i neg() {
        return new NGVec2i(-x, -y);
    }

    public NGVec2i negX() {
        return new NGVec2i(-x, y);
    }

    public NGVec2i negY() {
        return new NGVec2i(x, -y);
    }

    public NGVec2i lerp(NGVec2i other, float n) {
        return this.add(other.sub(this).scale(n));
    }

    public NGVec2i snap(int n) { // TODO: think of a name for parameter
        return this.divide(n).scale(n);
    }

    public NGVec2f toFloat() {
        return new NGVec2f(x, y);
    }

    public boolean equals(NGVec2i other) {
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return "(%d, %d)".formatted(x, y);
    }
}
