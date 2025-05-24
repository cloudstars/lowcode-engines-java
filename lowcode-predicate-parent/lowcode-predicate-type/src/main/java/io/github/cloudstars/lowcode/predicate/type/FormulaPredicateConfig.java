package io.github.cloudstars.lowcode.predicate.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 公式断言配置
 *
 * @author clouds
 */
@PredicateConfigClass(name = "Formula")
public class FormulaPredicateConfig extends AbstractPredicateConfig {

    /**
     * 公式
     */
    private String formula;

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public FormulaPredicateConfig() {
    }

    public FormulaPredicateConfig(JsonObject configJson) {
        super(configJson);

        this.formula = (String) configJson.get(GlobalAttrNames.ATTR_FORMULA);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putRequired(configJson, GlobalAttrNames.ATTR_FORMULA, this.formula);

        return configJson;
    }

}
