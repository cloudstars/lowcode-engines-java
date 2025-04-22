package io.github.cloudstars.lowcode.commons.value.dynamic;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;

/**
 * 默认值配置
 *
 * @author clouds
 *
 */
public interface XValueConfig extends XConfig {

    // 动态值配置项名称
    String ATTR = "value";

    // 是否必填配置项名称
    String ATTR_REQUIRED = "required";

    // 初始值配置项名称
    String ATTR_INIT_VALUE = "initValue";

}
