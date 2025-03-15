package io.github.cloudstars.lowcode.commons.editor;

/**
 * 配置解析器
 *
 * @param <T> 配置对象的类型
 */
public interface XConfigParser<T extends XConfig> {

    /**
     * 从JSON字符串解析配置
     *
     * @param jsonString JSON字符串配置
     * @return 配置对象
     */
    T fromJsonString(String jsonString);

}
