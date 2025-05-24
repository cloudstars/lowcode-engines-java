package io.github.cloudstars.lowcode.value.loader;

import io.github.cloudstars.lowcode.value.type.TimeValueTypeConfig;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间数据格式实现（运行态）
 *
 * @author clouds
 */
@ValueTypeClass(name = "TIME", valueTypeConfigClass = TimeValueTypeConfig.class)
public class TimeValueType extends AbstractValueType<TimeValueTypeConfig, Time> {

    // 默认的时间格式
    private static final String DEFAULT_FORMAT = "HH:mm:ss";

    public TimeValueType(TimeValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public Time parseDefaultValue() throws InvalidDataException {
        Object defaultValueConfig = this.valueTypeConfig.getDefaultValue();
        if (defaultValueConfig != null) {
            if (defaultValueConfig instanceof String) {
                return this.parseStringTime((String) defaultValueConfig);
            }
        }

        return null;
    }

    @Override
    public Time parseValue(Object rawValue) throws InvalidDataException {
        if (rawValue == null) {
            return null;
        }

        if (rawValue instanceof String) {
            return this.parseStringTime((String) rawValue);
        }

        return (Time) rawValue;
    }

    /**
     * 解析字符串时间格式
     *
     * @param timeString 字符串时间
     * @return java.sql.Time 时间
     */
    private Time parseStringTime(String timeString) {
        String format = valueTypeConfig.getFormat();
        if (format == null) {
            format = DEFAULT_FORMAT;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(timeString);
            long seconds = date.getTime() % (24 * 60 * 60);
            int hour = (int) (seconds / 3600);
            int minute = (int) ((seconds - hour * 3600) / 60);
            int second = (int) (seconds % 60);
            return new Time(hour, minute, second);
        } catch (ParseException e) {
            throw new InvalidDataException("时间[" + timeString + "]格式不合法，正确的时间格式为：" + format);
        }
    }

    @Override
    public void validate(Time value) throws InvalidDataException {
    }

}
