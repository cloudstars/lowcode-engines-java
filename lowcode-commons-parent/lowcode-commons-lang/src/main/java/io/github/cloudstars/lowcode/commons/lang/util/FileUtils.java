package io.github.cloudstars.lowcode.commons.lang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 文件工具类
 *
 * @author clouds
 */
public final class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {}


    /**
     * 从类路径下加载JSON对象
     *
     * @param filePath 文件路径
     * @return
     */
    public static String loadTextFromClasspath(String filePath) {
        return loadTextFromClasspath(filePath, StandardCharsets.UTF_8);
    }

    /**
     * 从类路径下加载JSON对象
     *
     * @param filePath 文件路径
     * @return
     */
    public static String loadTextFromClasspath(String filePath, Charset charset) {
        ClassPathResource resource = new ClassPathResource(filePath);
        try {
            byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return new String(fileData, charset);
        } catch (IOException e) {
            throw new RuntimeException("文件" + filePath + "加载失败！");
        }
    }

}
