package nootovich.nglib;

import java.nio.charset.StandardCharsets;

import static nootovich.nglib.NGBits.BYTE_SIZE;

public class NGConvert {

    private static final int    BASE64_CHAR_SIZE = 6;
    private static final char[] BASE64_TABLE     = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final char   BASE64_PADDING   = '=';

    public static String encodeBase64(String data) {
        return encodeBase64(data.getBytes(StandardCharsets.UTF_8));
    }

    public static String encodeBase64(byte[] data) {
        StringBuilder result = new StringBuilder();
        NGBits        bits   = new NGBits(data);
        while (bits.length() > 0) {
            NGBits slice = bits.chop(Math.min(BASE64_CHAR_SIZE, bits.length()));
            slice.rightPad(6);
            result.append(BASE64_TABLE[slice.toByte()]);
        }
        return result.toString();
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
}
