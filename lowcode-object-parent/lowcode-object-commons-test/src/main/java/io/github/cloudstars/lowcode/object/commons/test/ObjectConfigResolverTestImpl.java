package io.github.cloudstars.lowcode.object.commons.test;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.object.commons.ObjectConfigFactory;
import io.github.cloudstars.lowcode.object.commons.XObjectConfig;
import io.github.cloudstars.lowcode.object.commons.XObjectConfigResolver;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试用的模型配置解析器
 *
 * @author clouds
 */
public class ObjectConfigResolverTestImpl implements XObjectConfigResolver<XObjectConfig> {

    private final Map<String, XObjectConfig> configMap = new HashMap<>();

    private final String[] objectClassPaths = {"object/object-apply.json", "object/object-travel.json"};

    @PostConstruct
    public void init() {
        for (String objectClassPath : objectClassPaths) {
            JsonObject applyObjectConfigJson = JsonUtils.loadJsonObjectFromClasspath(objectClassPath);
            XObjectConfig applyObject = ObjectConfigFactory.newInstance(applyObjectConfigJson);
            this.configMap.put(applyObject.getKey(), applyObject);
        }
    }

    @Override
    public XObjectConfig resolve(String objectKey) {
        return this.configMap.get(objectKey);
    }

}
