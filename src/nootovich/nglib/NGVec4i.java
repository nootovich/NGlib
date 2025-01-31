package nootovich.nglib;

@SuppressWarnings("unused")
public class NGVec4i {

    private int x = 0, y = 0, z = 0, w = 0;

    public NGVec4i() {
    }

    public NGVec4i(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public NGVec4i(float x, float y, float z, float w) {
        this.x = (int) x;
        this.y = (int) y;
        this.z = (int) z;
        this.w = (int) w;
    }

    public NGVec4i(NGVec2i xy, int z, int w) {
        this.x = xy.x();
        this.y = xy.y();
        this.z = z;
        this.w = w;
    }

    public NGVec4i(NGVec2i xy, NGVec2i zw) {
        this.x = xy.x();
        this.y = xy.y();
        this.z = zw.x();
        this.w = zw.y();
    }

    public static NGVec4i[] createArray(int[][] positions) {
        NGVec4i[] result = new NGVec4i[positions.length];
        for (int i = 0; i < positions.length; i++) {
            int[] pos = positions[i];
            if (pos.length != 4) NGUtils.error("Invalid amount of arguments. Expected 4 but got " + pos.length);
            result[i] = new NGVec4i(pos[0], pos[1], pos[2], pos[3]);
        }
        return result;
    }

    public int x() { return x; }

    public int y() { return y; }

    public int z() { return z; }

    public int w() { return w; }

    public int h() { return z; }

    public NGVec4i x(int x) {
        this.x = x;
        return this;
    }

    public NGVec4i y(int y) {
        this.y = y;
        return this;
    }

    public NGVec4i z(int z) {
        this.z = z;
        return this;
    }

    public NGVec4i w(int w) {
        this.w = w;
        return this;
    }

    public NGVec4i h(int h) {
        return z(h);
    }

    public NGVec4i add(NGVec4i other) {
        return new NGVec4i(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    public NGVec4i add(int n) {
        return new NGVec4i(x + n, y + n, z + n, w + n);
    }

    public NGVec4i add(int dx, int dy, int dz, int dw) {
        return new NGVec4i(x + dx, y + dy, z + dz, w + dw);
    }

    public NGVec4i addY(int dy) {
        return new NGVec4i(x, y + dy, z, w);
    }

    public NGVec4i addXY(int dx, int dy) {
        return new NGVec4i(x + dx, y + dy, z, w);
    }

    public NGVec4i addXY(NGVec2i other) {
        return new NGVec4i(x + other.x(), y + other.y(), z, w);
    }

    public NGVec4i sub(NGVec4i other) {
        return new NGVec4i(x - other.x, y - other.y, z - other.z, w - other.w);
    }

    public NGVec4i sub(int n) {
        return new NGVec4i(x - n, y - n, z - n, w - n);
    }

    public NGVec4i subX(int dx) {
        return new NGVec4i(x - dx, y, z, w);
    }

    public NGVec4i subY(int dy) {
        return new NGVec4i(x, y - dy, z, w);
    }

    public NGVec4i subZ(int dz) {
        return new NGVec4i(x, y, z - dz, w);
    }

    public NGVec4i subW(int dw) {
        return new NGVec4i(x, y, z, w - dw);
    }

    public NGVec4i subH(int dh) {
        return subZ(dh);
    }

    public NGVec4i scale(float factor) {
        return new NGVec4i(x * factor, y * factor, z * factor, w * factor);
    }

    public NGVec4i scaleW(float factor) {
        return new NGVec4i(x, y, z, w * factor);
    }

    public NGVec4i lerp(NGVec4i other, float n) {
        return this.add(other.sub(this).scale(n));
    }

    public NGVec2i xy() {
        return new NGVec2i(x, y);
    }

//    public NGVec4f toFloat() {
//        return new NGVec4f(x, y, z, w);
//    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d, %d)", x, y, z, w);
    }
}
