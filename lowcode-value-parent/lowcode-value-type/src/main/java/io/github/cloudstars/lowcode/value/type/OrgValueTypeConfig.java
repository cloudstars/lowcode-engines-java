package io.github.cloudstars.lowcode.value.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Arrays;

/**
 * 机构数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "ORG", valueClass = Org.class)
public class OrgValueTypeConfig extends AbstractMapValueTypeConfig {

    private static final String ORG_KEY_FIELD_NAME = "key";
    private static final String ORG_NAME_FIELD_NAME = "name";

    public OrgValueTypeConfig() {
    }

    public OrgValueTypeConfig(JsonObject configJson) {
        super(configJson);

        MapPropertyConfig keyPropertyConfig = new MapPropertyConfig();
        {
            keyPropertyConfig.setName(ORG_KEY_FIELD_NAME);
            keyPropertyConfig.setLabel("机构编号");
            TextValueTypeConfig keyValueTypeConfig = new TextValueTypeConfig();
            keyPropertyConfig.setValueType(keyValueTypeConfig);
        }

        MapPropertyConfig namePropertyConfig = new MapPropertyConfig();
        {
            namePropertyConfig.setName(ORG_NAME_FIELD_NAME);
            namePropertyConfig.setLabel("机构名称");
            TextValueTypeConfig nameValueTypeConfig = new TextValueTypeConfig();
            namePropertyConfig.setValueType(nameValueTypeConfig);
        }

        this.properties = Arrays.asList(keyPropertyConfig, namePropertyConfig);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
