package net.cf.form.engine.repository.data.value;

import java.util.Map;

/**
 * JSON对象类型的值（SQL中只能当作String处理）
 *
 * @author clouds
 */
@Deprecated
public class JsonObjectValue implements Valuable<Map<String, Object>> {

    private Map<String, Object> value;

    public JsonObjectValue(Map<String, Object> value) {
        this.value = value;
    }

    @Override
    public Map<String, Object> getValue() {
        return this.value;
    }

    @Override
    public void setValue(Map<String, Object> value) {
        this.value = value;
    }
}
