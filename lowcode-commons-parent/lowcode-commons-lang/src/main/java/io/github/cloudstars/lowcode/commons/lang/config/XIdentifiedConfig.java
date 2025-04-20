package io.github.cloudstars.lowcode.commons.lang.config;

/**
 * 特定的具有唯一性标识的配置（如模型、视图等），它有自已的类型
 *
 * @author clouds
 */
public interface XIdentifiedConfig extends XTypedConfig {

    // 编号属性名称
    String ATTR_KEY = "key";

    // 代码属性名称
    String ATTR_CODE = "code";

    // 标题属性名称
    String ATTR_NAME = "name";

    /**
     * 获取配置的编号
     *
     * @return
     */
    String getKey();

    /**
     * 获取配置的代码
     *
     * @return
     */
    String getCode();

    /**
     * 获取配置的名称
     *
     * @return
     */
    String getName();

}
