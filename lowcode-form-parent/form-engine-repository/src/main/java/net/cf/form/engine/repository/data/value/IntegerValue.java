package net.cf.form.engine.repository.data.value;

/**
 * 整形类型的值
 *
 * @author clouds
 */
@Deprecated
public class IntegerValue implements Valuable<Integer> {

    private Integer value;

    public IntegerValue(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }
}
