package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 字段配置接口
 *
 * @author clouds
 */
public interface XBpmFieldConfig extends XTypedConfig {

    // 是否必填的属性名称
    String ATTR_REQUIRED = "required";

    // 是否支持标题配置名称
    String ATTR_TITLE_SUPPORTED = "titleSupported";

    // 是否支持摘要配置名称
    String ATTR_DIGEST_SUPPORTED = "digestSupported";

    /**
     * 是否必须
     *
     * @return
     */
    boolean isRequired();

    /**
     * 获取字段的数据格式配置
     *
     * @return
     */
    XValueTypeConfig getValueType();

}
