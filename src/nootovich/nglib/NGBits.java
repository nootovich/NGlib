package nootovich.nglib;

import static nootovich.nglib.NGUtils.error;

public class NGBits {

    public static final int BYTE_SIZE = 8;

    public boolean[] data;
    public int       pointer = 0;

    public NGBits(boolean[] data) {
        this.data = data;
    }

    public NGBits(byte[] data) {
        this.data = NGConvert.getBits(data);
    }

    public NGBits chop(int n) {
        if (length() < n) error("TODO: error message"); // TODO:
        boolean[] result = new boolean[n];
        System.arraycopy(data, pointer, result, 0, n);
        pointer += n;
        return new NGBits(result);
    }

    public byte toByte() {
        if (length() > BYTE_SIZE) error("TODO: error message"); // TODO:
        byte   result = 0;
        NGBits copy   = copy();
        while (copy.length() > 0) {
            result = (byte) ((result << 1) + (copy.chop(1).data[0] ? 1 : 0));
        }
        return result;
    }

    public NGBits copy() {
        boolean[] copy = new boolean[length()];
        System.arraycopy(data, pointer, copy, 0, length());
        return new NGBits(copy);
    }

    public void leftPad(int size) {
        if (length() >= size) return;
        int    error = size - length();
        NGBits copy  = copy();
        data = new boolean[size];
        System.arraycopy(copy.data, 0, data, error, size - error);
    }

    public void rightPad(int size) {
        if (length() >= size) return;
        int    len  = length();
        NGBits copy = copy();
        data = new boolean[size];
        System.arraycopy(copy.data, 0, data, 0, len);
    }

    public int length() {
        return data.length - pointer;
    }
}
