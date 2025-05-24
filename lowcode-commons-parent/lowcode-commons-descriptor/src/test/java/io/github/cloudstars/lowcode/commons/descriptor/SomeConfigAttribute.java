package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 某种配置类型下的属性规范
 *
 * @author clouds
 */
public class SomeConfigAttribute extends AbstractConfig {

    private String attr1;

    private String attr2;

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public SomeConfigAttribute() {
    }

    public SomeConfigAttribute(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return null;
    }

}
