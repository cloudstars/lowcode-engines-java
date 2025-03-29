package io.github.cloudstars.lowcode.object.commons.config;

/**
 * 模型配置解析器
 *
 * @author clouds
 */
public interface ObjectConfigResolver {

    /**
     * 根据模型的名称解析模型配置
     *
     * @param objectName 模型的名称
     * @return 模型配置
     */
    ObjectConfig resolve(String objectName);

}
