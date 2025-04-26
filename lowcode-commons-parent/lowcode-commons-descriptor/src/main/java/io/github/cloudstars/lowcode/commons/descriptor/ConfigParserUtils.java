package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.commons.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置解析器
 *
 * @author clouds
 */
public class ConfigParserUtils {

    /**
     * 根据规范、JSON配置生成配置对象
     *
     * @param descriptor 配置规范
     * @param configJson 配置JSON
     * @return 配置对象
     */
    public static XConfig fromJson(XDescriptor descriptor, JsonObject configJson) {
        Object configInstance = ObjectUtils.newInstance(descriptor.getConfigClassName());
        if (!(configInstance instanceof XConfig)) {
            throw new RuntimeException("规范" + descriptor + "中包含的配置类名必须实现XConfig");
        }
        XConfig config = (XConfig) configInstance;

        List<ConfigAttribute> attributes = descriptor.getAttributes();
        for (ConfigAttribute attribute : attributes) {
            String attrName = attribute.getName();
            Object attrValue = configJson.get(attrName);
            if (attrValue == null) {
                if (attribute.isRequired()) {
                    throw new RuntimeException("属性" + attrName + "的值不能为空，请检查!");
                }
                continue;
            }

            AttributeDataTypeEnum attrDataType = attribute.getDataType();
            if (attrDataType == AttributeDataTypeEnum.ARRAY) {
                ConfigAttribute.ArrayItem arrayItem = attribute.getItems();
                if (arrayItem.getDataType() == AttributeDataTypeEnum.REFERENCE) {
                    if (!(attrValue instanceof List)) {
                        throw new RuntimeException("属性" + attrName + "的配置必须是一个数组，请检查：" + JsonUtils.toJsonString(attrValue));
                    }

                    XDescriptor itemDescriptor = XDescriptorFactory.get(arrayItem.getDescriptor());
                    List<XConfig> itemConfigs = new ArrayList<>();
                    List<Object> attrValueList = (List<Object>) attrValue;
                    for (Object attrValueItem : attrValueList) {
                        XConfig itemConfig = ConfigParserUtils.fromJson(itemDescriptor, (JsonObject) attrValueItem);
                        itemConfigs.add(itemConfig);
                    }

                    ObjectUtils.setFieldValue(config, attrName, itemConfigs);
                }
            } else {
                ObjectUtils.setFieldValue(config, attrName, attrValue);
            }
        }

        return config;
    }

}
