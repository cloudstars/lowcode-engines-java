package net.cf.form.engine.repository.data.value;

/**
 * 整形类型的值
 *
 * @author clouds
 */
@Deprecated
public class LongValue implements Valuable<Long> {

    private Long value;

    public LongValue(Long value) {
        this.value = value;
    }

    @Override
    public Long getValue() {
        return this.value;
    }

    @Override
    public void setValue(Long value) {
        this.value = value;
    }
}
