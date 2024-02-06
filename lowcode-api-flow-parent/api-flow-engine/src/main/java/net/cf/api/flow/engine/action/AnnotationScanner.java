package net.cf.api.flow.engine.action;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.core.io.support.ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/6
 */
public class AnnotationScanner {

    public AnnotationScanner() {
    }

    /**
     * 扫描指定类型的注解
     *
     * @param annoClass 注解类
     * @param classpath 扫描的类路径，如com.cmbchina.xcode
     */
    public static <T extends Object> List<Class<T>> scan(Class<T> annoClass, String classpath) {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        List<Class<T>> classes = new ArrayList<>();
        try {
            Resource[] resources = resourcePatternResolver.getResources(CLASSPATH_ALL_URL_PREFIX
                    + ClassUtils.convertClassNameToResourcePath(classpath) + "/**" + ClassUtils.CLASS_FILE_SUFFIX);
            MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

            for (Resource resource : resources) {
                String fullClassName = null;
                if (resource.isReadable()) {
                    MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                    if (metadataReader != null) {
                        fullClassName = metadataReader.getClassMetadata().getClassName();
                    }
                }

                Class<?> clazz = Class.forName(fullClassName);
                if (!annoClass.equals(clazz) && annoClass.isAssignableFrom(clazz)) {
                    classes.add((Class<T>) clazz);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("扫描资源失败，失败原因: ", e);
        }

        return classes;
    }
}
