package io.github.cloudstars.lowcode.object.view.table.config.query;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XConfigUtils;
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
     * 展示列配置
     */
    private List<QuerySelectFieldConfig> selectFields;

    /**
     * 过滤条件
     */
    private QueryFilterConfig filter;

    /**
     * 查询分页配置
     */
    private QueryPaginationConfig pagination;

    /**
     * 查询排序规则
     */
    private QueryOrderConfig order;

    public QueryConfig() {
    }

    public QueryConfig(JsonObject configJson) {
        // 解析查询字段配置
        Object selectFieldsValue = configJson.get("selectFields");
        List<QuerySelectFieldConfig> selectFieldConfigs = new ArrayList<>();
        JsonArray selectFieldsArray = (JsonArray) selectFieldsValue;
        for (Object selectField : selectFieldsArray) {
            JsonObject selectFieldJson = (JsonObject) selectField;
            QuerySelectFieldConfig selectFieldConfig = new QuerySelectFieldConfig(selectFieldJson);
            selectFieldConfigs.add(selectFieldConfig);
        }
        this.setSelectFields(selectFieldConfigs);

        // 解析过滤条件配置
        Object filterConfigValue = configJson.get("filter");
        if (filterConfigValue != null) {
            this.setFilter(new QueryFilterConfig((JsonObject) filterConfigValue));
        }

        // 解析分页配置
        Object paginationValue = configJson.get("pagination");
        if (paginationValue != null) {
            this.setPagination(new QueryPaginationConfig((JsonObject) paginationValue));
        }

        Object orderValue = configJson.get("order");
        if (orderValue != null) {
            this.setOrder(new QueryOrderConfig((JsonObject) orderValue));
        }
    }

    public List<QuerySelectFieldConfig> getSelectFields() {
        return selectFields;
    }

    public void setSelectFields(List<QuerySelectFieldConfig> selectFields) {
        this.selectFields = selectFields;
    }

    public QueryFilterConfig getFilter() {
        return filter;
    }

    public void setFilter(QueryFilterConfig filter) {
        this.filter = filter;
    }

    public QueryPaginationConfig getPagination() {
        return pagination;
    }

    public void setPagination(QueryPaginationConfig pagination) {
        this.pagination = pagination;
    }

    public QueryOrderConfig getOrder() {
        return order;
    }

    public void setOrder(QueryOrderConfig order) {
        this.order = order;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put("selectFields", XConfigUtils.toJsonArray(this.selectFields));
        if (this.filter != null) {
            configJson.put("filter", this.filter.toJson());
        }
        if (this.pagination != null) {
            configJson.put("pagination", this.pagination.toJson());
        }
        if (this.order != null) {
            configJson.put("order", this.order.toJson());
        }

        return configJson;
    }
}
