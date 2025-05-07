package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.*;

import java.util.Arrays;
import java.util.List;

/**
 * 分页列表数据源
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "PAGINATION-LIST", valueClass = List.class)
public class PaginationListDataSourceValueType extends AbstractObjectValueTypeConfig {


    public PaginationListDataSourceValueType() {
    }

    public PaginationListDataSourceValueType(JsonObject configJson) {
        super(configJson);

        ObjectPropertyConfig totalPropertyConfig = new ObjectPropertyConfig();
        {
            totalPropertyConfig.setName("total");
            totalPropertyConfig.setLabel("数据总条数");
            NumberValueTypeConfig totalValueTypeConfig = new NumberValueTypeConfig();
            totalValueTypeConfig.setPrecision(0);
            totalValueTypeConfig.setMaxValue(Integer.MAX_VALUE);
            totalPropertyConfig.setValueType(totalValueTypeConfig);
        }

        ObjectPropertyConfig listPropertyConfig = new ObjectPropertyConfig();
        {
            listPropertyConfig.setName("list");
            listPropertyConfig.setLabel("当前页数据");
            listPropertyConfig.setValueType(new ListDataSourceValueType(configJson));
        }

        this.properties = Arrays.asList(totalPropertyConfig, listPropertyConfig);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();

        return configJson;
    }
}


