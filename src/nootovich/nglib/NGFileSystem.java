package nootovich.nglib;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class NGFileSystem {

    public static String loadFile(String filepath) {
        try {
            return Files.readString(Path.of(filepath));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static byte[] loadBytes(String filepath) {
        try {
            return Files.readAllBytes(Path.of(filepath));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void saveFile(String file, String data) {
        try {
            Path path = Path.of(file);
            if (Files.notExists(path.getParent())) Files.createDirectories(path.getParent());
            Files.writeString(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getDirectoryFiles(String dir) {
        // TODO: rewrite using `File` class?
        Stack<String> list = new Stack<>();
        try (Stream<Path> paths = Files.list(Path.of(dir))) {
            paths.filter(path -> !Files.isDirectory(path)).forEach(path -> list.push(path.getFileName().toString()));
            return list.toArray(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String findRecursively(String filename) {
        try {
            String result = findRecursively(filename, "./");
            if (result == null) NGUtils.error("Not able to find: " + filename);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String findRecursively(String filename, String dir) throws IOException {
        Stack<String> list = new Stack<>();
        try (Stream<Path> paths = Files.list(Path.of(dir))) {
            paths.forEach(path -> list.push(path.toString()));
        }
        for (String entry : list) {
            Path path = Path.of(entry);
            if (path.getFileName().toString().equals(filename)) return entry;
            if (Files.isDirectory(path)) {
                String result = findRecursively(filename, entry);
                if (result != null) return result;
            }
        }
        return null;
    }

    @SuppressWarnings("DataFlowIssue")
    public static boolean delete(String path) {
        boolean success = true;
        File    file    = new File(path);
        if (file.isDirectory()) for (File f : file.listFiles()) success &= deleteRecursively(f);
        return file.delete() && success;
    }

    @SuppressWarnings("DataFlowIssue")
    private static boolean deleteRecursively(File file) {
        boolean success = true;
        if (file.isDirectory()) for (File f : file.listFiles()) success &= deleteRecursively(f);
        return file.delete() && success;
    }

    public static long getLastModifiedTime(String filepath) {
        try {
            return Files.getLastModifiedTime(Path.of(filepath)).toMillis();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getParent(String path) {
        try {
            return Path.of(path).getParent().toString() + File.separator;
        } catch (Exception e) {
            NGUtils.error("Not able to get parent directory of: " + path);
        }
        return null;
    }
}
