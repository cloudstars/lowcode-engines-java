package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Arrays;

/**
 * 用户数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "USER", valueClass = User.class)
public class UserValueTypeConfig extends AbstractObjectValueTypeConfig {

    // 用户编号属性名称
    private static final String USER_KEY_FIELD_NAME = "key";
    // 用户名称属性名称
    private static final String USER_NAME_FIELD_NAME = "name";
    
    public UserValueTypeConfig() {
    }

    public UserValueTypeConfig(JsonObject configJson) {
        super(configJson);

        ObjectPropertyConfig keyPropertyConfig = new ObjectPropertyConfig();
        {
            keyPropertyConfig.setName(USER_KEY_FIELD_NAME);
            keyPropertyConfig.setLabel("用户编号");
            TextValueTypeConfig keyValueTypeConfig = new TextValueTypeConfig();
            keyPropertyConfig.setValueType(keyValueTypeConfig);
        }

        ObjectPropertyConfig namePropertyConfig = new ObjectPropertyConfig();
        {
            namePropertyConfig.setName(USER_NAME_FIELD_NAME);
            namePropertyConfig.setLabel("用户名称");
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
