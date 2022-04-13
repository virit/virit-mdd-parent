package cn.virit.metadata.generator;

import cn.virit.metadata.utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class InitDataBuilderImpl implements InitDataBuilder {

    @Override
    public void build(String dir) {

        String outFile = dir + "/src/main/resources/gen/init/init.sql";
        FileUtils.createIfNotExistsDir(FileUtils.getFileDir(dir));
        File out = new File(outFile);
        int count = 0;
        try (FileWriter writer = new FileWriter(out)) {
            String sourceDir = dir + "/src/main/resources/init";
            File rootDir = new File(sourceDir);
            count = buildInDir(rootDir, writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (count == 0) {
            out.delete();
        }
    }

    private int buildInDir(File dir, FileWriter writer) {

        int count = 0;
        if (!dir.exists() || !dir.isDirectory()) {
            return count;
        }
        for (var file : dir.listFiles()) {
            if (file.isDirectory()) {
                count += buildInDir(file, writer);
                continue;
            }
            String filePath = file.getAbsolutePath();
            if (filePath.contains(".csv")) {

            } else if (filePath.contains(".sql")) {
                count++;
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line + "\n");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return count;
    }
}
