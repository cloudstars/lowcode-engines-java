package io.github.cloudstars.lowcode.commons.descriptor.js;

import io.github.cloudstars.lowcode.commons.descriptor.XConfigDescriptor;

import java.util.List;

/**
 * 规范对象（先有规范，通过规范实例化为配置）
 *
 * @author clouds
 */
public class JsDescriptor implements XConfigDescriptor {

    /**
     * 规范的名称
     */
    private String name;

    /**
     * 规范的类名
     *
     * @return
     */
    private String configClassName;

    /**
     * 规范的属性
     */
    private List<ConfigAttribute> attributes;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Class getConfigClass() {
        Class<?> clz = null;
        try {
            clz = ClassLoader.getSystemClassLoader().loadClass(configClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return clz;
    }

    public String getConfigClassName() {
        return configClassName;
    }

    public void setConfigClassName(String configClassName) {
        this.configClassName = configClassName;
    }

    @Override
    public List<ConfigAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ConfigAttribute> attributes) {
        this.attributes = attributes;
    }

}
