package nootovich.nglib;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static nootovich.nglib.NGBits.BYTE_SIZE;

public class NGConvert {

    private static final int    BASE64_CHAR_SIZE = 6;
    private static final String BASE64_TABLE     = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final char   BASE64_PADDING   = '=';

    public static String encodeBase64(String data) {
        return encodeBase64(data.getBytes(StandardCharsets.UTF_8));
    }

    public static String encodeBase64(byte[] data) {
        StringBuilder result = new StringBuilder();
        NGBits        bits   = new NGBits(data);
        while (bits.length() > 0) {
            NGBits slice = bits.chop(Math.min(BASE64_CHAR_SIZE, bits.length()));
            slice.rightPad(BASE64_CHAR_SIZE);
            result.append(BASE64_TABLE.charAt(slice.toByte()));
        }
        return result.toString();
    }

    public static byte[] decodeBase64(String data) {
        ArrayList<Byte> result = new ArrayList<>();
        NGBits          bits   = new NGBits();
        for (int i = 0; i < data.length(); i++) {
            NGBits slice = new NGBits((byte) BASE64_TABLE.indexOf(data.charAt(i)));
            slice.chop(2);
            bits.push(slice.copy().data);
        }
        while (bits.length() > 0) {
            NGBits slice = bits.chop(Math.min(BYTE_SIZE, bits.length()));
            if (slice.length() < BYTE_SIZE && slice.toByte() == 0) break;
            slice.rightPad(BYTE_SIZE);
            result.add(slice.toByte());
        }
        byte[] ret = new byte[result.size()];
        for (int i = 0; i < result.size(); i++) ret[i] = result.get(i);
        return ret;
    }
}
