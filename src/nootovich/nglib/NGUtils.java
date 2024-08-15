package nootovich.nglib;

import java.lang.reflect.InvocationTargetException;

public class NGUtils {

    private static final int FILENAME_LEN = 32;

    public static int mod(int n, int m) {
        return ((n % m) + m) % m;
    }

    public static int clamp(int n, int min, int max) {
        if (max < min) max = min;
        if (n < min) return min;
        if (n > max) return max;
        return n;
    }

    public static <T> T recursiveCopy(T source) throws InstantiationException, IllegalAccessException {
        if (source == null) return null;
        Class<?> srcClass = source.getClass();
        if (srcClass.isPrimitive()) return source;
        if (source instanceof Object[] arr) {
            Object[] copy = new Object[arr.length];
            for (int i = 0; i < arr.length; i++) copy[i] = recursiveCopy(arr[i]);
            return (T) copy;
        }
        try {
            return (T) srcClass.getMethod("clone").invoke(source);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T info(String message) {
        StackTraceElement src      = Thread.currentThread().getStackTrace()[2];
        String            filename = src.getFileName();
        String            lineNum  = String.valueOf(src.getLineNumber());
        String            padding  = " ".repeat(Math.max(FILENAME_LEN - filename.length() - lineNum.length(), 0));
        System.out.printf("%s:%s%s [INFO]:  %s%n", filename, lineNum, padding, message);
        return null;
    }

    public static <T> T error(String message) {
        StackTraceElement src      = Thread.currentThread().getStackTrace()[2];
        String            filename = src.getFileName();
        String            lineNum  = String.valueOf(src.getLineNumber());
        String            padding  = " ".repeat(Math.max(FILENAME_LEN - filename.length() - lineNum.length(), 0));
        System.out.printf("%s:%s%s %s[ERROR]%s: %s%n", filename, lineNum, padding, "\u001B[31m", "\u001B[0m", message);
        System.exit(1);
        return null;
    }
}
