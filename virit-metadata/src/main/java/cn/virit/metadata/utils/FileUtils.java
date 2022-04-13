package cn.virit.metadata.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 文件工具类
 *
 * @author Virit
 * @since 1.0
 */
public final class FileUtils {

    public static String concatPath(String... pathes) {

        StringBuilder sb = new StringBuilder();
        for (String path : pathes) {
            sb.append(path);
        }
        return sb.toString();
    }

    public static File getFile(String dir, String path) {

        return new File(dir + "/" + getRealPath(path));
    }

    public static String getRealPath(String path) {
        if (path.startsWith("resources://")) {
            return "src/main/resources/" + path.replace("resources://", "");
        }
        return path;
    }

    public static String getFileName(String file) {
        return file.substring(0, file.lastIndexOf("."));
    }

    public static String getDirName(String path) {

        return path.substring(path.lastIndexOf("/") + 1);
    }

    public static InputStream openFileStream(String path) {
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static InputStream openFileStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createIfNotExistsDir(String dir) {

        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String getFileDir(String filePath) {
        return filePath.substring(0, filePath.lastIndexOf("/"));
    }

    public static boolean removeDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = removeDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static void appendFile(String fileName, String content) {

        try {
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write(content.getBytes(StandardCharsets.UTF_8));
            randomFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
