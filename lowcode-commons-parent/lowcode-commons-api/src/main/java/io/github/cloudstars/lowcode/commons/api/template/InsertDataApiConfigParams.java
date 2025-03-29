package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.data.type.ObjectProperty;

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
    private List<ObjectProperty> properties;

    public List<ObjectProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ObjectProperty> properties) {
        this.properties = properties;
    }

}
