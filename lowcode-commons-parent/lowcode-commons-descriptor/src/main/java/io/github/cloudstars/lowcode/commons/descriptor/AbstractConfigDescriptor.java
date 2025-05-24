package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 抽象的配置规范
 *
 * @author clouds
 * @param <T> 配置类型
 */
public abstract class AbstractConfigDescriptor<T extends AbstractConfig> implements XConfigDescriptor<T> {

    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置类型
     */
    private Class<T> configClass;

    /**
     * 配置的属性列表
     */
    private List<XConfigAttribute> attributes;

    public AbstractConfigDescriptor() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        this.configClass = (Class<T>) typeArguments[0];
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<T> getConfigClass() {
        return configClass;
    }

    @Override
    public List<XConfigAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<XConfigAttribute> attributes) {
        this.attributes = attributes;
    }

}
