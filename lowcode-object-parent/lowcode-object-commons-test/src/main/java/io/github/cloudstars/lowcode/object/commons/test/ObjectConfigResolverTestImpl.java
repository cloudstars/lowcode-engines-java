package io.github.cloudstars.lowcode.object.commons.test;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.object.core.editor.Spec1ObjectConfig;
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
public class ObjectConfigResolverTestImpl implements XObjectConfigResolver<Spec1ObjectConfig> {

    private final Map<String, Spec1ObjectConfig> configMap = new HashMap<>();

    private final String[] objectClassPaths = {"object/object-apply.json", "object/object-travel.json"};

    @PostConstruct
    public void init() {
        for (String objectClassPath : objectClassPaths) {
            JsonObject applyObjectConfigJson = JsonUtils.loadJsonObjectFromClasspath(objectClassPath);
            Spec1ObjectConfig applyObject = (Spec1ObjectConfig) ObjectConfigFactory.newInstance(applyObjectConfigJson);
            this.configMap.put(applyObject.getKey(), applyObject);
        }
    }

    @Override
    public Spec1ObjectConfig resolve(String objectKey) {
        return this.configMap.get(objectKey);
    }

}
