package nootovich.nglib;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

@SuppressWarnings("unused")
public class NGFileSystem {

    public static String loadFile(String filepath) {
        try {
            String[]      fileLines = Files.readAllLines(new File(filepath).toPath()).toArray(new String[]{ });
            StringBuilder result    = new StringBuilder();
            for (String line: fileLines) result.append(line).append('\n');
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadFile(String filepath, Charset charset) {
        try {
            String[]      fileLines = Files.readAllLines(new File(filepath).toPath(), charset).toArray(new String[]{ });
            StringBuilder result    = new StringBuilder();
            for (String line: fileLines) result.append(line).append('\n');
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] loadBytes(String filepath) {
        try {
            return Files.readAllBytes(new File(filepath).toPath());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static BufferedImage loadBakedImage(String ImageBase64) {
        try {
            return ImageIO.read(new ByteArrayInputStream(NGConvert.decodeBase64(ImageBase64)));
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveFile(String file, String data) {
        try {
            Path path = new File(file).toPath();
            if (Files.notExists(path.getParent())) Files.createDirectories(path.getParent());
            Files.write(path, data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveFile(String file, byte[] data) {
        try {
            Path path = new File(file).toPath();
            if (Files.notExists(path.getParent())) Files.createDirectories(path.getParent());
            Files.write(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getDirectoryFiles(String dir) {
        Stack<String> list = new Stack<>();
        try (Stream<Path> paths = Files.list(new File(dir).toPath())) {
            paths.filter(path -> !Files.isDirectory(path)).forEach(path -> list.push(path.getFileName().toString()));
            return list.toArray(new String[]{ });
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
        try (Stream<Path> paths = Files.list(new File(dir).toPath())) {
            paths.filter(path -> !path.getFileName().toString().startsWith(".")).map(Path::toString).forEach(list::push);
        }
        for (String entry: list) {
            Path path = new File(entry).toPath();
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
        if (file.isDirectory()) for (File f: file.listFiles()) success &= deleteRecursively(f);
        return file.delete() && success;
    }

    @SuppressWarnings("DataFlowIssue")
    private static boolean deleteRecursively(File file) {
        boolean success = true;
        if (file.isDirectory()) for (File f: file.listFiles()) success &= deleteRecursively(f);
        return file.delete() && success;
    }

    public static long getLastModifiedTime(String filepath) {
        try {
            return Files.getLastModifiedTime(new File(filepath).toPath()).toMillis();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getParent(String path) {
        try {
            return new File(path).toPath().getParent().toString() + File.separator;
        } catch (Exception e) {
            NGUtils.error("Not able to get parent directory of: " + path);
        }
        return null;
    }
}
