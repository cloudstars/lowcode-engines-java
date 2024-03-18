package net.cf.form.repository.mysql;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.Map;

/**
 * 支持对象下子属性的Map结构的参数源
 *
 * @author 80274507
 */
public class AdvancedMapSqlParameterSource extends MapSqlParameterSource {

    public AdvancedMapSqlParameterSource(Map<String, Object> values) {
        super(values);
    }

    @Override
    public boolean hasValue(String paramName) {
        if (super.hasValue(paramName)) {
            return true;
        }

        int lastIndex = -1;
        Map<String, Object> currValues = this.getValues();
        String currParamName = paramName;
        do {
            int from  = lastIndex + 1;
            int currIndex = paramName.indexOf('.', from);
            if (currIndex > 0) {
                currParamName = paramName.substring(from, currIndex);
                if (!currValues.containsKey(currParamName)) {
                    return false;
                }
                Object ownerValue = currValues.get(currParamName);
                if (!(ownerValue instanceof Map)) {
                    return false;
                }
                currValues = (Map<String, Object>) ownerValue;
            } else {
                currParamName = paramName.substring(from);
            }
            lastIndex = currIndex;
        } while (lastIndex > 0);

        return currValues.containsKey(currParamName);
    }

    @Override
    public Object getValue(String paramName) {
        Map<String, Object> currValues = this.getValues();
        if (currValues.containsKey(paramName)) {
            return currValues.get(paramName);
        }

        int lastIndex = -1;
        String currParamName = paramName;
        do {
            int from = lastIndex + 1;
            int currIndex = paramName.indexOf('.', from);
            if (currIndex > 0) {
                currParamName = paramName.substring(from, currIndex);
                if (!currValues.containsKey(currParamName)) {
                    throw new IllegalArgumentException("No value registered for key '" + paramName + "'");
                }
                Object ownerValue = currValues.get(currParamName);
                if (!(ownerValue instanceof Map)) {
                    throw new IllegalArgumentException("No value registered for key '" + paramName + "'");
                }
                currValues = (Map<String, Object>) ownerValue;
            } else {
                currParamName = paramName.substring(from);
            }

            lastIndex = currIndex;
        } while (lastIndex > 0);

        if (currValues.containsKey(currParamName)) {
            return currValues.get(currParamName);
        } else {
            throw new IllegalArgumentException("No value registered for key '" + paramName + "'");
        }
    }
}
