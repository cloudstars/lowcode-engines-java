package io.github.cloudstars.lowcode.commons.data.predicate;

import io.github.cloudstars.lowcode.commons.data.predicate.json.JsonExpressParser;
import io.github.cloudstars.lowcode.commons.data.predicate.json.JsonExpression;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * JSON表达式配置
 *
 * @author clouds
 */
@ExpressionConfigClass(name = "JSON")
public class JsonExpressionConfig extends AbstractExpressionConfig {

    // JSON配置名称
    private static final String ATTR_JSON = "json";


    /**
     * JSON表达式
     */
    private JsonExpression json;

    private JsonExpressParser jsonExpressParser = new JsonExpressParser();

    public JsonExpression getJson() {
        return json;
    }

    public void setJson(JsonExpression json) {
        this.json = json;
    }

    public JsonExpressionConfig(JsonObject configJson) {
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
