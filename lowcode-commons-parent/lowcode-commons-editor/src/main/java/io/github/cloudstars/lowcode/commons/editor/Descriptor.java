package io.github.cloudstars.lowcode.commons.editor;

import java.util.List;

/**
 * 规范对象
 *
 * @author clouds
 */
public class Descriptor implements XDescriptor {

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
    private List<Attribute> attributes;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getConfigClassName() {
        return configClassName;
    }

    public void setConfigClassName(String configClassName) {
        this.configClassName = configClassName;
    }

    @Override
    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

}
