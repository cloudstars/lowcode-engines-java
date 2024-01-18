package com.cmbchina.codefriend.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 文件工具类
 *
 * @author 80274507
 */
public final class FileUtils {

    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {}

    /**
     * 从类路径下加载JSON对象
     *
     * @param filePath
     * @return
     */
    public static String loadTextFromClasspath(String filePath) {
        ClassPathResource resource = new ClassPathResource(filePath);
        try {
            byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("文件" + filePath + "加载失败！");
        }
    }

    /**
     * 读取本地文本文件
     *
     * @param filePath
     */
    public static String loadTextFromLocalPath(String filePath) {
        File file = new File(filePath);
        StringBuilder builder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            scanner.close();

            return builder.toString();
        } catch (FileNotFoundException e) {
            logger.error("读文件{}失败，文件不存在", filePath, e);
            throw new RuntimeException("文件不存在", e);
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
