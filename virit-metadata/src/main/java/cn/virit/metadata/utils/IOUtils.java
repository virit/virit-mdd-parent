package cn.virit.metadata.utils;

import java.io.*;

/**
 * IO工具类
 *
 * @author Virit
 * @since 1.0
 */
public final class IOUtils {

    public static InputStream getFileStream(String dir, String file) {
        try {
            return new FileInputStream(FileUtils.getFile(dir, file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static InputStream getFileStream(String file) {
        try {
            return new FileInputStream(new File(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Closeable closeable) {

        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String loadAsString(InputStream in) {

        var sb = new StringBuilder();
        String line;
        var br = new BufferedReader(new InputStreamReader(in));
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
