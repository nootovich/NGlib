package nootovich.nglib;

import java.util.ArrayList;

import static nootovich.nglib.NGUtils.error;

public class NGBits {

    public static final int BYTE_SIZE = 8;

    public boolean[] data    = new boolean[]{ };
    public int       pointer = 0;

    public NGBits() { }

    public NGBits(boolean[] data) {
        this.data = data;
    }

    public NGBits(byte[] data) {
        this.data = getBits(data);
    }

    public NGBits(byte data) {
        this.data = getBits(data);
    }

    public NGBits(String data) {
        this.data = getBits(data.getBytes());
    }

    public NGBits(ArrayList<NGBits> data) {
        for (NGBits bits: data) {
            push(bits.data);
        }
    }

    public static boolean[] getBits(byte n) {
        boolean[] bits = new boolean[BYTE_SIZE];
        for (int i = 0; i < bits.length; i++) {
            bits[BYTE_SIZE - i - 1] = (n & (1 << i)) > 0;
        }
        return bits;
    }

    public static boolean[] getBits(byte[] n) {
        boolean[] bits = new boolean[BYTE_SIZE * n.length];
        for (int i = 0; i < n.length; i++) {
            System.arraycopy(getBits(n[i]), 0, bits, BYTE_SIZE * i, BYTE_SIZE);
        }
        return bits;
    }

    public NGBits chop(int n) {
        if (length() < n) error("TODO: error message"); // TODO:
        boolean[] result = new boolean[n];
        System.arraycopy(data, pointer, result, 0, n);
        pointer += n;
        return new NGBits(result);
    }

    public boolean chop1() {
        if (length() < 1) error("TODO: error message"); // TODO:
        return data[pointer++];
    }

    public boolean[] pop(int n) {
        if (length() < n) error("TODO: error message"); // TODO:
        boolean[] result = new boolean[n];
        System.arraycopy(data, pointer, result, 0, n);
        boolean[] temp = data;
        data = new boolean[data.length - n];
        System.arraycopy(temp, 0, data, 0, data.length);
        return result;
    }

    public void push(boolean bit) {
        int    len  = length();
        NGBits copy = copy();
        data = new boolean[len + 1];
        System.arraycopy(copy.data, 0, data, 0, len);
        data[len] = bit;
    }

    public void push(boolean[] bits) {
        int    len  = length();
        NGBits copy = copy();
        data = new boolean[len + bits.length];
        System.arraycopy(copy.data, 0, data, 0, len);
        System.arraycopy(bits, 0, data, len, bits.length);
    }

    public void push(byte n) {
        push(getBits(n));
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
