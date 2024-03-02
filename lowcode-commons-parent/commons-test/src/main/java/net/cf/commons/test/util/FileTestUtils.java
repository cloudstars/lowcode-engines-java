package net.cf.commons.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件工具类
 *
 * @author clouds
 */
public final class FileTestUtils {

    private final static Logger logger = LoggerFactory.getLogger(FileTestUtils.class);

    private FileTestUtils() {}

    /**
     * 从类路径下加载JSON对象
     *
     * @param filePath
     * @return
     */
    public static String loadTextFromClasspath(String filePath) {
        ClassPathResource resource = new ClassPathResource(filePath);
        try {
            byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return new String(fileData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("文件" + filePath + "加载失败！");
        }
    }

    /**
     * 根据路径匹配加载多个文件内容
     *
     * @param filePattern
     * @return
     */
    public static Map<String, String> loadTextsFromClasspath(String filePattern) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Map<String, String> fileTextMap = new HashMap<>();
            final Resource[] resources = resolver.getResources(filePattern);
            for (Resource resource : resources) {
                byte[] fileData = FileCopyUtils.copyToByteArray(resource.getFile());
                String fileText = new String(fileData, StandardCharsets.UTF_8);
                fileTextMap.put(resource.getFilename(), fileText);
            }

            return fileTextMap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取本地文本文件
     *
     * @param filePath
     */
    public static String loadTextFromLocalPath(String filePath) {
        File file = new File(filePath);
        try {
            byte[] fileData = FileCopyUtils.copyToByteArray(file);
            String fileText = new String(fileData, StandardCharsets.UTF_8);
            return fileText;
        } catch (IOException e) {
            logger.error("读文件{}失败", filePath, e);
            throw new RuntimeException("读文件失败", e);
        }
    }

    /**
     * 把内容写入本地文件
     *
     * @param filePath
     * @param content
     */
    public static void writeTextToLocalPath(String filePath, String content) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            logger.error("文件保存失败", e);
            throw new RuntimeException("文件保存异常", e);
        }
    }
}
