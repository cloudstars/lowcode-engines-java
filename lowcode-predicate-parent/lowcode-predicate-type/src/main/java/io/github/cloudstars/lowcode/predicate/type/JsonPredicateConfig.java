package io.github.cloudstars.lowcode.predicate.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.predicate.type.json.JsonPredicateConfigParser;
import io.github.cloudstars.lowcode.predicate.type.json.XJsonPredicateConfig;

/**
 * JSON断言配置
 *
 * @author clouds
 */
@PredicateConfigClass(name = "JSON")
public class JsonPredicateConfig extends AbstractPredicateConfig {

    // JSON配置名称
    private static final String ATTR_JSON = "json";

    /**
     * JSON断言
     */
    private XJsonPredicateConfig json;

    private JsonPredicateConfigParser jsonExpressParser = new JsonPredicateConfigParser();

    public XJsonPredicateConfig getJson() {
        return json;
    }

    public void setJson(XJsonPredicateConfig json) {
        this.json = json;
    }

    public JsonPredicateConfig(JsonObject configJson) {
        super(configJson);

        JsonObject jsonConfigJson = (JsonObject) configJson.get(ATTR_JSON);
        this.json = this.jsonExpressParser.fromJson(jsonConfigJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_JSON, this.json.toJson());
        return configJson;
    }

}
