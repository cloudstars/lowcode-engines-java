package io.github.cloudstars.lowcode.commons.data.predicate.json;

import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

public class JsonPredicateConfigParser implements XConfigParser<XJsonPredicate> {

    @Override
    public XJsonPredicate fromJson(JsonObject configJson) {
        String type = (String) configJson.get(XTypedConfig.ATTR);
        if (XJsonPredicate.TYPE_LOGIC.equalsIgnoreCase(type)) {
            return new LogicJsonPredicate(configJson);
        } if (XJsonPredicate.TYPE_BINARY.equalsIgnoreCase(type)) {
            return new BinaryJsonPredicate(configJson);
        } else {
            throw new RuntimeException("断言类型[" + type + "]不支持");
        }
    }

    /**
     * 解析逻辑操作断言
     *
     * @param configJson
     * @return
     */
    private LogicJsonPredicate parseLogic(JsonObject configJson) {
        return null;
    }

    /**
     * 解析二元操作断言
     *
     * @param configJson
     * @return
     */
    private BinaryJsonPredicate parseBinary(JsonObject configJson) {
        return null;
    }

}
