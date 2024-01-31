package net.cf.form.engine.repository.data.value;

import java.util.Date;

/**
 * 日期类型的值
 *
 * @author clouds
 */
@Deprecated
public class DateValue implements Valuable<Date> {

    private Date value;

    public DateValue(Date value) {
        this.value = value;
    }

    @Override
    public Date getValue() {
        return this.value;
    }

    @Override
    public void setValue(Date value) {
        this.value = value;
    }
}
