package io.github.cloudstars.lowcode.commons.editor;

import io.github.cloudstars.lowcode.commons.editor.XDescriptor.Attribute;
import io.github.cloudstars.lowcode.commons.lang.value.ObjectValueUtils;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;
import io.github.cloudstars.lowcode.commons.utils.object.ObjectUtils;

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
        Object descriptorValue = configJson.get("descriptor");
        if (descriptorValue == null) {
            throw new RuntimeException("JSON配置中必须包含descriptor规范说明的属性");
        }
        //XDescriptor descriptor = DescriptorFactory.get(descriptorValue.toString());
        Object configInstance = ObjectUtils.newInstance(descriptor.getConfigClassName());
        if (!(configInstance instanceof XConfig)) {
            throw new RuntimeException("规范" + descriptor + "中包含的配置类名必须实现XConfig");
        }
        XConfig config = (XConfig) configInstance;

        List<Attribute> attributes = descriptor.getAttributes();
        for (Attribute attribute : attributes) {
            String attrName = attribute.name;
            Object attrValue = configJson.get(attrName);
            ObjectValueUtils.setFieldValue(config, attrName, attrValue);
        }

        return config;
    }

}
