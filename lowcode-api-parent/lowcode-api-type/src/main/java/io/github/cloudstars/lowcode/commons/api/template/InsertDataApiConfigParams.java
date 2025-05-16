package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.value.type.MapPropertyConfig;

import java.util.List;

/**
 * 单条数据插入API配置模板的参数
 *
 * @author clouds
 */
public class InsertDataApiConfigParams {

    /**
     * 插入的属性列表
     */
    private List<MapPropertyConfig> properties;

    public List<MapPropertyConfig> getProperties() {
        return properties;
    }

    public void setProperties(List<MapPropertyConfig> properties) {
        this.properties = properties;
    }

}
