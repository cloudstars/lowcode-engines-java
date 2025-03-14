package io.github.cloudstars.lowcode.commons.editor;

import io.github.cloudstars.lowcode.commons.editor.XDescriptor.Attribute;
import io.github.cloudstars.lowcode.commons.lang.util.FileUtils;
import io.github.cloudstars.lowcode.commons.lang.util.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.util.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * JS文件定义的规范加载器
 *
 * @author clouds
 */
public final class DescriptorJsLoader {

    private DescriptorJsLoader() {
    }

    /**
     * 从类路径中加载JS格式的规范定义
     *
     * @param filePath 类路径
     * @return 规范定义
     */
    public static Descriptor loadFromClassPath(String filePath) {
        String content = FileUtils.loadTextFromClasspath(filePath);
        JsonObject descriptorJson = JsonUtils.parseObject(content);
        Descriptor descriptor = new Descriptor();
        descriptor.setName(descriptorJson.get("name").toString());
        descriptor.setConfigClassName(descriptorJson.get("configClassName").toString());
        Object attributesValue = descriptorJson.get("attributes");
        List<Attribute> attributes = new ArrayList<>();
        assert (attributesValue instanceof JsonArray);
        ((JsonArray) attributesValue).forEach(item -> {
            assert (item instanceof JsonObject);
            JsonObject itemJson = (JsonObject) item;
            Attribute attribute = new Attribute();
            attribute.name = itemJson.get("name").toString();
            attributes.add(attribute);
        });
        descriptor.setAttributes(attributes);

        return descriptor;
    }

}
