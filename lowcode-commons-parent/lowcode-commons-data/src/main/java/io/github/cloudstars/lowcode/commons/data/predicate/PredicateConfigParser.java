package io.github.cloudstars.lowcode.commons.data.predicate;

import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 断言解析器
 *
 * @author clouds
 */
public class PredicateConfigParser implements XConfigParser<XPredicateConfig> {

    @Override
    public XPredicateConfig fromJson(JsonObject configJson) {
        Object typeValue = configJson.get(XTypedConfig.ATTR);
        if (typeValue == null) {
            throw new RuntimeException("断言配置中type不能为空，请检查您的配置：" + configJson);
        }

        return PredicateConfigFactory.newInstance(configJson);
    }

}
