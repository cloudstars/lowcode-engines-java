package net.cf.object.engine.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认的结果转换器
 *
 * @author clouds
 */
public class DefaultResultReducer implements ResultReducer {

    private final List<FieldMapping> valueDefs;

    public DefaultResultReducer(List<FieldMapping> valueDefs) {
        this.valueDefs = valueDefs;
    }

    @Override
    public Map<String, Object> reduceResult(Map<String, Object> resultMap) {
        if (resultMap == null) {
            return null;
        }

        return this.reduceValue(resultMap, this.valueDefs);
    }

    /**
     * 从结果集中按子字段归并一个Map值，如果子字段还需要归并的话，递归处理
     * <p>
     * 如：
     * {
     * "id": "xx",
     * "name": "aa",
     * "sub.s1": "xx",
     * "sub.s3": "yy"
     * }
     * 按 ["id", "name", "sub(s1, s3)"]展开后的结果为:
     * {
     * "id": "xx",
     * "name": "yy",
     * "sub": {
     * "s1": "xx",
     * "s2": "yy"
     * }
     *
     * @param value
     * @param subFields
     */
    private Map<String, Object> reduceValue(Map<String, Object> value, List<FieldMapping> subFields) {
        Map<String, Object> targetMap = new HashMap<>();
        for (FieldMapping subField : subFields) {
            String subFieldName = subField.getFieldName();
            String subFieldColumnName = subField.getColumnName();
            Object subFieldValue;
            List<FieldMapping> subSubFields = subField.getSubFields();
            boolean hasSubFields = subSubFields != null && subSubFields.size() > 0;
            if (hasSubFields) {
                // 存在子字段时，归并子字段的值
                subFieldValue = this.reduceSubFields(subField, value);
            } else {
                // 列名称拼上前缀
                subFieldValue = value.get(subFieldColumnName);
            }
            // 作数据转换（特殊情况的处理，按需）
            //subFieldValue = DataConvert.convert(subFieldValue, subField.getValueType());
            targetMap.put(subFieldName, subFieldValue);
        }

        return targetMap;
    }

    /**
     * 从结果集中按子字段归并一个List<Map>值，，按子字段展开，如果子字段还需要归并的话，递归处理
     * <p>
     * 如：
     * {
     * "id": ["xx", "aa"],
     * "name": ["yy", "bb"]
     * }
     * 按 ["id", "name"]归并后的结果为：
     * [
     * {"id": "xx", "name": "yy"},
     * {"id": "aa", "name": "bb"}
     * ]
     *
     * @param value
     * @param subFields
     */
    private List<Map<String, Object>> reduceToListValue(Map<String, Object> value, List<FieldMapping> subFields) {
        // 为每一个子字段生成一个List的值
        int valueSize = 0;
        Map<String, List<?>> subFieldListValueMap = new HashMap<>();
        for (FieldMapping subField : subFields) {
            String subFieldName = subField.getFieldName();
            String subFieldColumnName = subField.getColumnName();
            List<FieldMapping> subSubFields = subField.getSubFields();
            boolean hasSubFields = subSubFields != null && subSubFields.size() > 0;

            Object subFieldValue;
            if (hasSubFields) {
                // 存在子字段时，递归归并子字段的值
                //String columnNamePrefix = this.getTargetColumnKey(prefixColumnName, subFieldColumnName);
                subFieldValue = this.reduceSubFields(subField, value/*, columnNamePrefix*/);
                if (subFieldValue instanceof Map) { // isArray = false
                    // 将子字段的值归并后，还需要将子子段对应值拆散到一个个Map中，形成一个List<Map>，参见示例
                    subFieldValue = this.mergeReduceValue(subSubFields, (Map<String, List<Object>>) subFieldValue);
                } else { // isArray = true
                    // 如果当将字段是数组的情况下，需要参照上面非数组的情况，进一条条数据进行处理
                    List<List<Object>> mergedTargetMapList = new ArrayList();
                    List<Map<String, List<Object>>> subSubFieldListValue = (List<Map<String, List<Object>>>) subFieldValue;
                    for (int i = 0, l = subSubFieldListValue.size(); i < l; i++) {
                        Map<String, List<Object>> subSubList = subSubFieldListValue.get(i);
                        List<Object> subSubValue = this.mergeReduceValue(subSubFields, subSubList);
                        mergedTargetMapList.add(subSubValue);
                    }
                    subFieldValue = mergedTargetMapList;
                }
            } else {
                //String subFieldColumnKey = this.getTargetColumnKey(prefixColumnName, subFieldColumnName);
                subFieldValue = value.get(subFieldColumnName);
            }

            if (subFieldValue instanceof String) {
                subFieldValue = DataConvert.stringToList((String) subFieldValue);
            }

            if (subFieldValue != null) {
                assert (subFieldValue instanceof List);
                List<?> subFieldListValue = (List<?>) subFieldValue;
                subFieldListValueMap.put(subFieldName, subFieldListValue);

                // 可能存在不同子字段的数组长度不一致的情形，如：[1, 2, 3]、[4, 5]
                if (subFieldListValue.size() > valueSize) {
                    valueSize = subFieldListValue.size();
                }
            }
        }

        // 将上面的处理结果处理成一个长度为valueSize的List<Map>
        List<Map<String, Object>> targetMapList = new ArrayList<>();
        for (int i = 0; i < valueSize; i++) {
            Map<String, Object> targetMap = new HashMap<>();
            for (FieldMapping subField : subFields) {
                String subFieldKey = subField.getFieldName();
                List<?> subFieldListValue = subFieldListValueMap.get(subFieldKey);
                if (subFieldListValue.size() > i) {
                    targetMap.put(subFieldKey, subFieldListValue.get(i));
                }
            }
            targetMapList.add(targetMap);
        }

        return targetMapList;
    }

    /**
     * 处理一个子段的子字段
     *
     * @param field
     * @param resultMap
     * @return
     */
    private Object reduceSubFields(FieldMapping field, Map<String, Object> resultMap) {
        Object targetValue; // 目标结果
        boolean isArray = field.getValueType().isArray();
        List<FieldMapping> subItems = field.getSubFields();
        if (isArray) { // 字段展开且是数组的情况
            targetValue = this.reduceToListValue(resultMap, subItems);
        } else { // 模型或字段展开且非数组的情况
            targetValue = this.reduceValue(resultMap, subItems);
        }

        return targetValue;
    }

    /**
     * reduceToListValue函数内部递归调用reduceSubFields后，需要将返回的结果按照子字段的名称并成成一个个Map，形成一个List<Map>
     *
     * 比如，递归调用reduceSubFields返回：{ "f321": ["xx321", "xx421"], "f322": [-1, -1], "f323": [1, 1] }
     * 合并后为一个数据：[{"f321":  "xx321", "f322":  -1, "f323": 1}, {"f321":  "xx421", "f322":  -1, "f323": 1}]
     * 每个数组中都包含原来Map中的键，数组的长度等于每个键对应的数组值的长度（正常来说每一个数组长度相等），
     * 上面的数据可以拿人员字段来对比，人员下面有两个属性f321、f322，并且可以多选，递归调用的数据是数据库里存储的多列f321、f321
     * 合并后的数据是引擎层合并后的数据，可以认为是两条用记数据
     *
     * @param subFields
     * @param reduceValue
     * @return
     */
    private List<Object> mergeReduceValue(List<FieldMapping> subFields, Map<String, List<Object>> reduceValue) {
        List<Object> subSubList = new ArrayList<>();
        String firstFieldName = subFields.get(0).getFieldName();
        int subValueSize = reduceValue.get(firstFieldName).size();
        for (int j = 0; j < subValueSize; j++) {
            Map<String, Object> subSubItem = new HashMap<>();
            for (FieldMapping subField : subFields) {
                String k = subField.getFieldName();
                Object v = reduceValue.get(k).get(j);
                subSubItem.put(k, v);
            }
            subSubList.add(subSubItem);
        }

        return subSubList;
    }

}
