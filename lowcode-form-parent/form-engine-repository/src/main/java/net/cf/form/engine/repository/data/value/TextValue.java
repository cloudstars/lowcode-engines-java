package net.cf.form.engine.repository.data.value;

/**
 * 文本类型的值
 *
 * @author clouds
 */
@Deprecated
public class TextValue implements Valuable<String> {

    private String value;

    public TextValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
