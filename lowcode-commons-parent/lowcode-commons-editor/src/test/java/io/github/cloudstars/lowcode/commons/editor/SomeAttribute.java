package io.github.cloudstars.lowcode.commons.editor;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 某种配置类型下的属性规范
 *
 * @author clouds
 */
public class SomeAttribute  implements XConfig {

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

    @Override
    public JsonObject toJson() {
        return null;
    }

}
