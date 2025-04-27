package io.github.cloudstars.lowcode.component.commons;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 一个类型为“ABC”的测试组件
 *
 * @author clouds
 */
@ComponentConfigClass(name = "ABC")
public class AbcComponent extends AbstractComponentConfig {

    // a 配置名称
    private static final String ATTR_A = "a";

    // b 配置名称
    private static final String ATTR_B = "b";

    // c 配置名称
    private static final String ATTR_C = "c";

    private String a;

    private String b;

    private String c;

    public AbcComponent() {
    }

    public AbcComponent(JsonObject configJson) {
        super(configJson);

        this.a = (String) configJson.get(ATTR_A);
        this.b = (String) configJson.get(ATTR_B);
        this.c = (String) configJson.get(ATTR_C);
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, ATTR_A, this.a);
        ConfigUtils.putIfNotNull(configJson, ATTR_B, this.b);
        ConfigUtils.putIfNotNull(configJson, ATTR_C, this.c);

        return configJson;
    }
}
