package io.github.cloudstars.lowcode.predicate.type.json;

import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.predicate.type.json.binary.BinaryJsonPredicateConfig;
import io.github.cloudstars.lowcode.predicate.type.json.logic.LogicJsonPredicateConfig;

/**
 * JSON断言配置解析器
 *
 * @author clouds
 */
public final class JsonPredicateConfigParser {

    public static XJsonPredicateConfig fromJson(JsonObject configJson) {
        String type = (String) configJson.get(XTypedConfig.ATTR);
        if (XJsonPredicateConfig.TYPE_LOGIC.equalsIgnoreCase(type)) {
            return new LogicJsonPredicateConfig(configJson);
        } if (XJsonPredicateConfig.TYPE_BINARY.equalsIgnoreCase(type)) {
            return new BinaryJsonPredicateConfig(configJson);
        } else {
            throw new RuntimeException("断言类型[" + type + "]不支持");
        }
    }

}
