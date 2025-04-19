package io.github.cloudstars.lowcode.commons.data.datasource;

import io.github.cloudstars.lowcode.commons.data.valuetype.DataTypeEnum;

/**
 * 静态数据源配置
 *
 * @author clouds
 */
@DataSourceConfigClass(name = "STATIC")
public class StaticDataSourceConfig extends AbstractDataSourceConfig {

    /**
     * 数据类型
     */
    private DataTypeEnum dataType;

    /**
     * 静态数据
     */
    private Object data;

    public DataTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEnum dataType) {
        this.dataType = dataType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
