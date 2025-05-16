package io.github.cloudstars.lowcode.file.commons;

import io.github.cloudstars.lowcode.commons.value.type.AbstractMapValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.MapPropertyConfig;
import io.github.cloudstars.lowcode.commons.value.type.TextValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.ValueTypeConfigClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Arrays;

/**
 * 文件数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "FILE", valueClass = File.class)
public class FileValueTypeConfig extends AbstractMapValueTypeConfig {

    public FileValueTypeConfig() {
    }

    public FileValueTypeConfig(JsonObject configJson) {
        super(configJson);
    }

    /**
     * 初始化文件数据格式配置下面的属性列表配置
     */
    private void initObjectProperties() {
        MapPropertyConfig keyPropertyConfig = new MapPropertyConfig();
        {
            keyPropertyConfig.setName("key");
            keyPropertyConfig.setLabel("文件标识");
            TextValueTypeConfig labelValueTypeConfig = new TextValueTypeConfig();
            keyPropertyConfig.setValueType(labelValueTypeConfig);
        }

        MapPropertyConfig namePropertyConfig = new MapPropertyConfig();
        {
            namePropertyConfig.setName("name");
            namePropertyConfig.setLabel("文件名称");
            TextValueTypeConfig valueValueTypeConfig = new TextValueTypeConfig();
            namePropertyConfig.setValueType(valueValueTypeConfig);
        }

        this.properties = Arrays.asList(keyPropertyConfig, namePropertyConfig);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }
}
