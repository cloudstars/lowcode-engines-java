package io.github.cloudstars.lowcode.commons.config;

/**
 * 一种代表属性胡配置，如模型下的字段、API下面属性等
 *
 * @author clouds
 */
public interface XAttributeConfig extends XTypedConfig {

    /**
     * 获取属性的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取属性的标题
     *
     * @return
     */
    String getTitle();

}
