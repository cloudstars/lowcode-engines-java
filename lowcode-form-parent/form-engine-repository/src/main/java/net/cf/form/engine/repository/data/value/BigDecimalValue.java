package net.cf.form.engine.repository.data.value;

import java.math.BigDecimal;

/**
 * 金额类型的值
 *
 * @author clouds
 */
@Deprecated
public class BigDecimalValue implements Valuable<BigDecimal> {

    private BigDecimal value;

    public BigDecimalValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal getValue() {
        return this.value;
    }

    @Override
    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
