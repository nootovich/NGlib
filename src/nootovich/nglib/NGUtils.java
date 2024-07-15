package nootovich.nglib;

public class NGUtils {

    private static final int FILENAME_LEN = 24;

    public static int clamp(int n, int min, int max) {
        if (max < min) max = min;
        if (n < min) return min;
        if (n > max) return max;
        return n;
    }

    public static void info(String message) {
        StackTraceElement src      = Thread.currentThread().getStackTrace()[2];
        String            filename = src.getFileName();
        String            lineNum  = String.valueOf(src.getLineNumber());
        String            padding  = " ".repeat(Math.max(FILENAME_LEN - filename.length() - lineNum.length(), 0));
        System.out.printf("%s:%s%s [INFO]:  %s%n", filename, lineNum, padding, message);
    }

    public static void error(String message) {
        StackTraceElement src      = Thread.currentThread().getStackTrace()[2];
        String            filename = src.getFileName();
        String            lineNum  = String.valueOf(src.getLineNumber());
        String            padding  = " ".repeat(Math.max(FILENAME_LEN - filename.length() - lineNum.length(), 0));
        System.out.printf("%s:%s%s %s[ERROR]%s: %s%n", filename, lineNum, padding, "\u001B[31m", "\u001B[0m", message);
        System.exit(1);
    }
}
