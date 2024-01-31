package net.cf.form.engine.repository.data.value;

/**
 * 布尔类型的值
 *
 * @author clouds
 */
@Deprecated
public class BooleanValue implements Valuable<Boolean> {

    private Boolean value;

    public BooleanValue(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }
}
