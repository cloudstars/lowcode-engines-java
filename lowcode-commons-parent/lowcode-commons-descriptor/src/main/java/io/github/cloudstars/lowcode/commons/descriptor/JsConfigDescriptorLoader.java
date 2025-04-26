package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.commons.js.JsFunction;
import io.github.cloudstars.lowcode.commons.js.JsScriptUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.util.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JS函数文件定义的规范加载器
 *
 * @author clouds
 */
public final class JsConfigDescriptorLoader {

    private JsConfigDescriptorLoader() {
    }

    /**
     * 从类路径中加载JS格式的规范定义
     *
     * @param filePath 类路径
     * @return 规范定义
     */
    public static Descriptor loadFromClassPath(String filePath) {
        String content = FileUtils.loadTextFromClasspath(filePath);
        Object jsEvalResult = JsScriptUtils.eval(content);
        if (!(jsEvalResult instanceof JsFunction)) {
            throw new RuntimeException("规范定义文件必须返回一个JS函数并返回Json对象，请检查JS文件内容：" + content);
        }

        // 生成基本信息
        JsFunction jsFunction = (JsFunction) jsEvalResult;
        JsonObject descriptorJson = (JsonObject) jsFunction.executeOnce();
        Descriptor descriptor = new Descriptor();
        descriptor.setName(descriptorJson.get("name").toString());
        descriptor.setConfigClassName(descriptorJson.get("configClassName").toString());

        // 生成属性列表
        Object attributesValue = descriptorJson.get("attributes");
        if (!(attributesValue instanceof List)) {
            throw new RuntimeException("规范定义文件必须包含数组格式的attributes属性列表定义，请检查JS文件内容：" + content);
        }
        List<ConfigAttribute> attributes = new ArrayList<>();
        ((List) attributesValue).forEach(attributeValue -> {
            if (!(attributeValue instanceof JsonObject)) {
                throw new RuntimeException("规范定义文件中attributes下的每一个属性必须是一个对象格式，请检查JS文件内容：" + content);
            }

            Map<String, Object> attributeValueMap = (Map<String, Object>) attributeValue;
            ConfigAttribute attribute = new ConfigAttribute();
            attribute.setName(attributeValueMap.get("name").toString());
            Object dataType = attributeValueMap.get("dataType");
            if (dataType != null) {
                attribute.setDataType(AttributeDataTypeEnum.valueOf(dataType.toString().toUpperCase()));
            }

            // 当数据格式是数组时，需要解析数据下的子项描述
            if (attribute.getDataType() == AttributeDataTypeEnum.ARRAY) {
                Map<String, Object> arrayItemMap = (Map<String, Object>) attributeValueMap.get("items");
                ConfigAttribute.ArrayItem arrayItem = new ConfigAttribute.ArrayItem();
                attribute.setItems(arrayItem);
                Object itemDataType = arrayItemMap.get("dataType");
                if (itemDataType != null) {
                    arrayItem.setDataType(AttributeDataTypeEnum.valueOf(itemDataType.toString().toUpperCase()));
                    String refDescriptor = (String) arrayItemMap.get("descriptor");
                    if (refDescriptor == null) {
                        throw new RuntimeException("数据的子项数据格式是DESCRIPTOR时，必须指定descriptor属性来指明子项的配置规范");
                    }

                    arrayItem.setDescriptor(refDescriptor);
                }
            }

            attributes.add(attribute);
        });
        descriptor.setAttributes(attributes);

        return descriptor;
    }

}
