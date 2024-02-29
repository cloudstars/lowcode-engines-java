package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.SingleExcelField;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-28 09:00
 * @Description: 整数Field实现类
 */
public class NumberField implements SingleExcelField {
    String code;

    String name;

    public NumberField(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DataType getDataType() {
        return DataType.BIG_DECIMAL;
    }

    @Override
    public DataFormatter<BigDecimal> getDataFormatter() {
        return new DataFormatter<BigDecimal>() {
            @Override
            public String format(Object data) {
                return String.valueOf(data);
            }

            @Override
            public BigDecimal unFormat(Object data) {
                BigDecimal dataValue = null;
                if (data == null) {
                    return null;
                } else if (data instanceof Integer) {
                    dataValue = BigDecimal.valueOf((Integer) data);
                } else if (data instanceof String) {
                    String result = String.valueOf(data);
                    if (StringUtils.isBlank(result)) {
                        return null;
                    }
                    dataValue = new BigDecimal(result);
                } else if (data instanceof Long) {
                    dataValue = BigDecimal.valueOf((Long) data);
                } else if (data instanceof Double) {
                    dataValue = BigDecimal.valueOf((Double) data);
                } else if (data instanceof BigDecimal) {
                    dataValue = (BigDecimal) data;
                }
                return dataValue;
            }
        };
    }
}