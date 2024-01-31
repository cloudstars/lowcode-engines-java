package net.cf.form.engine.repository.data.value;

import java.sql.Time;

/**
 * 时间类型的值
 *
 * @author clouds
 */
@Deprecated
public class TimeValue implements Valuable<Time> {

    private Time value;

    public TimeValue(Time value) {
        this.value = value;
    }

    @Override
    public Time getValue() {
        return this.value;
    }

    @Override
    public void setValue(Time value) {
        this.value = value;
    }
}
