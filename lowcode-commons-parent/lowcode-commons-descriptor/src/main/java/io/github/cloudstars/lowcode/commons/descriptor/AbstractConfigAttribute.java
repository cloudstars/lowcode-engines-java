package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;

/**
 * 配置的属性接口，表示某个属性
 *
 * @author clouds
 */
public abstract class AbstractConfigAttribute<T extends AbstractConfig> implements XConfigAttribute<T> {

    /**
     * 属性的名称
     */
    private String name;

    /**
     * 属性的标题
     */
    private String label;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public AbstractConfigAttribute() {
    }

    public AbstractConfigAttribute(String name, String label) {
        this.name = name;
        this.label = label;
    }

}