package io.github.cloudstars.lowcode.commons.test.util;

import io.github.cloudstars.lowcode.commons.lang.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 文件工具类
 *
 * @author clouds
 */
public final class FileTestUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileTestUtils.class);

    private FileTestUtils() {}

    /**
     * 从类路径下加载Json文本
     *
     * @param filePath 文件路径
     * @return JSON文本
     */
    public static String loadFileTextFromClasspath(String filePath) {
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
     * @param filePattern 文件匹配格式
     * @return 文件名到文件内容文本的映射
     */
    public static Map<String, String> loadFileTextsFromClasspath(String filePattern) {
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
     * @param filePath 文件路径
     */
    public static String loadFileTextFromLocalPath(String filePath) {
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
     * 从类路径下加载文件，并以行格式返回
     *
     * @param filePath 文件路径
     * @return 文件行组成的文本列表
     */
    public static List<String> loadFileLinesFromClassPath(String filePath, boolean ignoreComments) {
        String content = loadFileTextFromClasspath(filePath);
        String[] lines = content.split(System.lineSeparator());
        if (!ignoreComments) {
            return Arrays.asList(lines);
        } else {
            List<String> lineList = new ArrayList<>();
            for (String line : lines) {
                if (line.startsWith("//") || StringUtils.isBlank(line)) {
                    continue;
                }

                lineList.add(line);
            }

            return lineList;
        }
    }


    /**
     * 把内容写入本地文件
     *
     * @param filePath 文件路径
     * @param content 内容
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

    /**
     * 读取流
     *
     * @param inStream 输入流
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] readBytes(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }

}
