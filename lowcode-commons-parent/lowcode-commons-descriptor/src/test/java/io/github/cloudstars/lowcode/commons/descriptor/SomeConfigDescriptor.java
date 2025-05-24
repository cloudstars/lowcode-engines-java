package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Arrays;
import java.util.List;

public class SomeConfigDescriptor extends AbstractConfigDescriptor<SomeConfig> {

    private static List<XConfigAttribute> ATTRS;

    static {
        final String ATTR_X = "x";
        final SomeConfigAttributeDescriptor attributeDescriptor = new SomeConfigAttributeDescriptor();
        XConfigAttribute attrX = new AbstractConfigAttribute<SomeConfig>(ATTR_X, "X标题") {

            @Override
            public void fromJson(JsonObject configJson, SomeConfig config) {
                config.setX(ConfigUtils.getString(configJson, ATTR_X));
            }

            @Override
            public void toJson(SomeConfig config, JsonObject configJson) {
                ConfigUtils.putIfNotNull(configJson, ATTR_X, config.getX());
            }
        };

        final String ATTR_ITEMS = "items";
        XConfigAttribute attrItems = new AbstractConfigAttribute<SomeConfig>(ATTR_ITEMS, "ITEMS标题") {

            @Override
            public void fromJson(JsonObject configJson, SomeConfig config) {
                List<SomeConfigAttribute> items = ConfigUtils.getList(configJson, ATTR_ITEMS, (v) -> {
                    return ConfigDescriptorUtils.build(attributeDescriptor, v);
                });
                config.setItems(items);
            }

            @Override
            public void toJson(SomeConfig config, JsonObject configJson) {
                ConfigUtils.putList(configJson, ATTR_ITEMS, config.getItems(), (v) -> {
                    return ConfigDescriptorUtils.toJson(attributeDescriptor, v);
                });
            }
        };

        ATTRS = Arrays.asList(attrX, attrItems);
    }

    @Override
    public List<XConfigAttribute> getAttributes() {
        return ATTRS;
    }

}
