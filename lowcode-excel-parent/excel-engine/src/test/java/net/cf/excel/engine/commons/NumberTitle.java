package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.SingleExcelTitle;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-28 09:00
 * @Description: 整数Field实现类
 */
public class NumberTitle implements SingleExcelTitle {
    String code;

    String name;

    public NumberTitle(String code, String name) {
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
            public String format(Object value, Map<String, Object> dataMap) {
                return String.valueOf(value);
            }

            @Override
            public BigDecimal unFormat(Object value, Map<String, Object> dataMap) {
                BigDecimal dataValue = null;
                if (value == null) {
                    return null;
                } else if (value instanceof Integer) {
                    dataValue = BigDecimal.valueOf((Integer) value);
                } else if (value instanceof String) {
                    String result = String.valueOf(value);
                    if (StringUtils.isBlank(result)) {
                        return null;
                    }
                    dataValue = new BigDecimal(result);
                } else if (value instanceof Long) {
                    dataValue = BigDecimal.valueOf((Long) value);
                } else if (value instanceof Double) {
                    dataValue = BigDecimal.valueOf((Double) value);
                } else if (value instanceof BigDecimal) {
                    dataValue = (BigDecimal) value;
                }
                return dataValue;
            }
        };
    }
}