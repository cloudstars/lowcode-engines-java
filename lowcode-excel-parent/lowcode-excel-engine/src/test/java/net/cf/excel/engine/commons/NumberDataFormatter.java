package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-03-05 20:35
 * @Description: 数字dataFormatter
 */
public class NumberDataFormatter implements DataFormatter<BigDecimal> {
    @Override
    public String getDataFormatterCode() {
        return "number";
    }

    @Override
    public String getDataFormatterName() {
        return "数字";
    }

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
}