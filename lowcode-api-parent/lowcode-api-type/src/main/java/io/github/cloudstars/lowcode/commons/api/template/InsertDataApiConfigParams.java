package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.value.type.config.ObjectValueProperty;

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
    private List<ObjectValueProperty> properties;

    public List<ObjectValueProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ObjectValueProperty> properties) {
        this.properties = properties;
    }

}
