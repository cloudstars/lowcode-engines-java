package io.github.cloudstars.lowcode.object.commons.field;

import io.github.cloudstars.lowcode.value.type.MapPropertyConfig;

import java.util.List;

/**
 * Map字段配置接口
 *
 * @author clouds
 */
public interface XMapObjectFieldConfig extends XObjectFieldConfig {

    /**
     * 获取字段下的属性
     *
     * @return 字段下的属性
     */
    List<MapPropertyConfig> getProperties();

}
