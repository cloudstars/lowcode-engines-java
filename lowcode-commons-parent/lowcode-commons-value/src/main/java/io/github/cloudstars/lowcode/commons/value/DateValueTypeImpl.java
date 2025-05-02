package io.github.cloudstars.lowcode.commons.value;

import io.github.cloudstars.lowcode.commons.value.type.DateValueTypeConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期数据格式实现
 *
 * @author clouds
 */
@ValueTypeClass(name = "DATE", valueTypeConfigClass = DateValueTypeConfig.class)
public class DateValueTypeImpl extends AbstractValueTypeImpl<DateValueTypeConfig, Date> {

    // 默认的日期格式
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public DateValueTypeImpl(DateValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public Date parseDefaultValue() throws InvalidDataException {
        Object defaultValueConfig = this.valueTypeConfig.getDefaultValue();
        if (defaultValueConfig != null) {
            if (defaultValueConfig instanceof String) {
                return this.parseStringDate((String) defaultValueConfig);
            }
        }

        return null;
    }

    @Override
    public Date parseValue(Object rawValue) throws InvalidDataException {
        if (rawValue == null) {
            return null;
        }

        if (rawValue instanceof String) {
            return this.parseStringDate((String) rawValue);
        }

        return (Date) rawValue;
    }

    /**
     * 解析字符串日期格式
     *
     * @param dateString 字符串日期
     * @return java.util.Date日期
     */
    private Date parseStringDate(String dateString) {
        String format = valueTypeConfig.getFormat();
        if (format == null) {
            format = DEFAULT_FORMAT;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new InvalidDataException("日期[" + dateString + "]格式不合法，正确的日期格式为：" + format);
        }
    }

    @Override
    public void validate(Date value) throws InvalidDataException {
    }

}
