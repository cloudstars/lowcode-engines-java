package io.github.cloudstars.lowcode.commons.editor;

import io.github.cloudstars.lowcode.commons.editor.XDescriptor.Attribute;
import io.github.cloudstars.lowcode.commons.utils.io.FileUtils;
import io.github.cloudstars.lowcode.commons.utils.js.JsScriptUtils;

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
        /*if (!(jsEvalResult instanceof JsFunction)) {
            throw new RuntimeException("规范定义文件必须是一个JS函数，请检查JS文件内容：" + content);
        }
        Object descriptorValue = ((JsFunction) jsEvalResult).execute(new JsonObject());
        */if (!(jsEvalResult instanceof Map)) {
            throw new RuntimeException("规范定义文件必须返回一个JSON对象，请检查JS文件内容：" + content);
        }

        // 生成基本信息
        Map<String, Object> descriptorMap = (Map<String, Object>) jsEvalResult;
        Descriptor descriptor = new Descriptor();
        descriptor.setName(descriptorMap.get("name").toString());
        descriptor.setConfigClassName(descriptorMap.get("configClassName").toString());

        // 生成属性列表
        Object attributesValue = descriptorMap.get("attributes");
        if (!(attributesValue instanceof List)) {
            throw new RuntimeException("规范定义文件必须包含数组格式的attributes属性列表定义，请检查JS文件内容：" + content);
        }
        List<Attribute> attributes = new ArrayList<>();
        ((List) attributesValue).forEach(item -> {
            if (!(item instanceof Map)) {
                throw new RuntimeException("规范定义文件中attributes下的每一个属性必须是一个对象格式，请检查JS文件内容：" + content);
            }

            Map<String, Object> itemMap = (Map<String, Object>) item;
            Attribute attribute = new Attribute();
            attribute.name = itemMap.get("name").toString();
            attributes.add(attribute);
        });
        descriptor.setAttributes(attributes);

        return descriptor;
    }

}
