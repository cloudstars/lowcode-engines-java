package io.github.cloudstars.lowcode.object.view.table.config.query;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询配置
 *
 * @author clouds
 */
public class QueryConfig extends AbstractConfig {

    /**
     * 展示列
     */
    private List<QuerySelectFieldConfig> selectFields;

    /**
     * 过滤条件
     */
    private QueryFilterConfig filterConfig;

    /**
     * 查询分页配置
     */
    private QueryPageConfig pageConfig;

    /**
     * 查询排序规则
     */
    private QueryOrderConfig orderConfig;

    public QueryConfig() {
    }

    public QueryConfig(JsonObject configJson) {
        Object selectFieldsValue = configJson.get("selectFields");
        if (selectFieldsValue != null) {
            List<QuerySelectFieldConfig> selectFieldConfigs = new ArrayList<>();
            JsonArray selectFieldsArray = (JsonArray) selectFieldsValue;
            for (Object selectField: selectFieldsArray) {
                JsonObject selectFieldJson = (JsonObject) selectField;
                QuerySelectFieldConfig selectFieldConfig = new QuerySelectFieldConfig(selectFieldJson);
                selectFieldConfigs.add(selectFieldConfig);
            }
            this.setSelectFields(selectFieldConfigs);
        }
    }

    public List<QuerySelectFieldConfig> getSelectFields() {
        return selectFields;
    }

    public void setSelectFields(List<QuerySelectFieldConfig> selectFields) {
        this.selectFields = selectFields;
    }

    public QueryFilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(QueryFilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public QueryPageConfig getPageConfig() {
        return pageConfig;
    }

    public void setPageConfig(QueryPageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    public QueryOrderConfig getOrderConfig() {
        return orderConfig;
    }

    public void setOrderConfig(QueryOrderConfig orderConfig) {
        this.orderConfig = orderConfig;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put("selectFields", this.selectFields);

        return configJson;
    }
}
