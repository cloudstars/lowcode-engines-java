package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Arrays;
import java.util.List;

public class SomeConfigAttributeDescriptor extends AbstractConfigDescriptor<SomeConfigAttribute> {

    private static List<XConfigAttribute> ATTRS;

    static {
        final String ATTR_ATTR1 = "attr1";
        final String ATTR_ATTR2 = "attr2";
        XConfigAttribute attr1 = new AbstractConfigAttribute<SomeConfigAttribute>(ATTR_ATTR1, "属性1标题") {
            @Override
            public void fromJson(JsonObject configJson, SomeConfigAttribute config) {
                config.setAttr1(ConfigUtils.getString(configJson, ATTR_ATTR1));
            }

            @Override
            public void toJson(SomeConfigAttribute config, JsonObject configJson) {
                ConfigUtils.put(configJson, ATTR_ATTR1, config.getAttr1());
            }
        };

        XConfigAttribute attr2 = new AbstractConfigAttribute<SomeConfigAttribute>(ATTR_ATTR2, "属性2标题") {
            @Override
            public void fromJson(JsonObject configJson, SomeConfigAttribute config) {
                config.setAttr2(ConfigUtils.getString(configJson, ATTR_ATTR2));
            }

            @Override
            public void toJson(SomeConfigAttribute config, JsonObject configJson) {
                ConfigUtils.put(configJson, ATTR_ATTR2, config.getAttr2());
            }
        };

        ATTRS = Arrays.asList(attr1, attr2);
    }

    @Override
    public List<XConfigAttribute> getAttributes() {
        return ATTRS;
    }

}
