package io.github.cloudstars.lowcode.object.commons.test;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.object.core.editor.FormBasedObjectConfig;
import io.github.cloudstars.lowcode.object.core.editor.ObjectConfigFactory;
import io.github.cloudstars.lowcode.object.core.editor.XObjectConfigResolver;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试用的模型配置解析器
 *
 * @author clouds
 */
public class ObjectConfigResolverTestImpl implements XObjectConfigResolver<FormBasedObjectConfig> {

    private final Map<String, FormBasedObjectConfig> configMap = new HashMap<>();

    private final String[] objectClassPaths = {"object/object-apply.json", "object/object-travel.json"};

    @PostConstruct
    public void init() {
        for (String objectClassPath : objectClassPaths) {
            JsonObject applyObjectConfigJson = JsonUtils.loadJsonObjectFromClasspath(objectClassPath);
            FormBasedObjectConfig applyObject = (FormBasedObjectConfig) ObjectConfigFactory.newInstance(applyObjectConfigJson);
            this.configMap.put(applyObject.getKey(), applyObject);
        }
    }

    @Override
    public FormBasedObjectConfig resolve(String objectKey) {
        return this.configMap.get(objectKey);
    }

}
