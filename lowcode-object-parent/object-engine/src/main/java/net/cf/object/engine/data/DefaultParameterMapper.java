package net.cf.object.engine.data;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认的参数转化器
 *
 * @author clouds
 */
public class DefaultParameterMapper implements ParameterMapper {

    private List<FieldMapping> valueDefs;

    private Map<String, FieldMapping> valueDefsMap;

    public DefaultParameterMapper(List<FieldMapping> valueDefs) {
        this.valueDefs = valueDefs;
        this.valueDefsMap = new HashMap<>();
        for (FieldMapping valueDef : valueDefs) {
            this.valueDefsMap.put(valueDef.getFieldName(), valueDef);
        }
    }

    @Override
    public Map<String, Object> mapParameter(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }

        return this.mapValue(paramMap, valueDefs);
    }


    /**
     * 将一个Map值按子属性展开，如果子属性还需要展开的话，递归处理
     *
     * 如：{
     *   "id": "xx",
     *   "name": "yy",
     *   "sub": {
     *     "s1": "xx",
     *     "s2": "yy"
     *   }
     * }
     * 按 ["id", "name", "sub(s1, s2)"]展开后的结果为:
     * {
     *   "id": "xx",
     *   "name": "aa",
     *   "sub.s1": "xx",
     *   "sub.s2": "yy"
     * }
     *
     * {
     *   "id": "xx",
     *   "name": "yy",
     *   "sub": null
     * }
     * 按 ["id", "name", "sub(s1, s2)"]展开后的结果为:
     * {
     *   "id": "xx",
     *   "name": "aa",
     *   "sub.s1": null,
     *   "sub.s2": null
     * }
     *
     * @param value
     * @param subFields
     */
    private Map<String, Object> mapValue(Map<String, Object> value, List<FieldMapping> subFields) {
        Map<String, Object> targetMap = new HashMap<>();
        // 待展开值为空
        if (CollectionUtils.isEmpty(value)) {
            for (FieldMapping subField : subFields) {
                targetMap.put(subField.getColumnName(), null);
            }
        }
        targetMap.putAll(value);
        for (FieldMapping subField : subFields) {
            String subFieldName = subField.getFieldName();
            String subFieldColumnName = subField.getColumnName();
            List<FieldMapping> subSubFields = subField.getSubFields();
            boolean hasSubFields = subSubFields != null && subSubFields.size() > 0;
            if (hasSubFields) {
                Object subFieldValue = value.get(subFieldName);
                // 存在子字段时递归生成子字段的映射
                Map<String, Object> resultMap = this.mapSubFields(subField, subFieldValue);
                // 将子字段的处理结果合并到目标映射中
                this.mergeResultMap(resultMap, targetMap, subFieldColumnName);
            } else {
                String subColumnKey = subField.getColumnName();
                Object subValue = value.get(subFieldName);
                targetMap.put(subColumnKey, subValue);
            }
        }

        return targetMap;
    }

    /**
     * 将一个List<Map>的值，按子属性展开，如果子属性还需要展开的话，递归处理
     *
     * 如：[
     *   {"id": "xx", "name": "yy"},
     *   {"id": "aa", "name": "bb"}
     * ]
     * 按 ["id", "name"]展开后的结果为：
     * {
     *   "id": ["xx", "aa"],
     *   "name": ["yy", "bb"]
     * }
     *
     * 如果待展开值空，展开后结果为：
     * {
     *   "id": null,
     *   "name": null
     * }
     * @param value
     * @param subFields
     */
    private Map<String, Object> mapListValue(List<Map<String, Object>> value, List<FieldMapping> subFields) {
        Map<String, Object> targetMap = new HashMap<>();
        // 待展开值为空
        if (CollectionUtils.isEmpty(value)) {
            for (FieldMapping subField : subFields) {
                targetMap.put(subField.getColumnName(), null);
            }
            return targetMap;
        }
        int valueSize = value.size();
        for (FieldMapping subField : subFields) {
            String subFieldName = subField.getFieldName();
            String subFieldColumnName = subField.getColumnName();
            List<FieldMapping> subSubFields = subField.getSubFields();
            boolean hasSubFields = subSubFields != null && subSubFields.size() > 0;

            if (hasSubFields) {
                List<Map<String, ?>> subMapList = new ArrayList<>();
                for (int i = 0; i < valueSize; i++) {
                    Object subFieldValue = value.get(i).get(subFieldName);
                    // 存在子字段时递归生成子字段的映射
                    Map<String, Object> subMap = this.mapSubFields(subField, subFieldValue);
                    subMapList.add(subMap);
                }

                // 将子字段的处理结果合并到目标结果集中
                this.mergeResultMapList(subMapList, targetMap, subFieldColumnName);
            } else {
                // 依次从mapListValue中取一个值，组成一个当前属性的List
                List<Object> subFieldListValue = new ArrayList<>();
                for (int i = 0; i < valueSize; i++) {
                    Object subFieldValue = value.get(i).get(subFieldName);
                    subFieldListValue.add(subFieldValue);
                }
                // 后面按需加 subFieldValue = DataConvert.convert(subFieldValue, subField.getValueType());
                targetMap.put(subFieldColumnName, subFieldListValue);
            }
        }

        return targetMap;
    }

    /**
     * 处理一个子段的子字段
     *
     * @param field 当前字段的定义
     * @param value 当前字段的值
     * @return
     */
    private Map<String, Object> mapSubFields(FieldMapping field, Object value) {
        Map<String, Object> resultMap; // 目标结果
        boolean isArray = field.getValueType().isArray();
        List<FieldMapping> subFields = field.getSubFields();
        if (isArray) {
            List<Map<String, Object>> listSubValueItem = (List<Map<String, Object>>) value;
            resultMap = this.mapListValue(listSubValueItem, subFields);
        } else {
            Map<String, Object> mapSubValueItem = (Map<String, Object>) value;
            resultMap = this.mapValue(mapSubValueItem, subFields);
        }

        return resultMap;
    }

    /**
     * 合并解析结果
     *
     * @param resultMap
     * @param targetMap
     * @param owner
     */
    private void mergeResultMap(Map<String, ?> resultMap, Map<String, Object> targetMap, String owner) {
        if (resultMap != null) {
            for (Map.Entry<String, ?> entry : resultMap.entrySet()) {
                String targetColumnKey = this.getTargetColumnKey(owner, entry.getKey());
                targetMap.put(targetColumnKey, entry.getValue());
            }

            // 合并后，把原来的变量移除
            targetMap.remove(owner);
        }
    }

    /**
     * 合并子元数处理的结果到主元数中
     *
     * 比如子元素两次调用的结果为：
     * {
     *   "f31.f311": ["xx3111", "xx3112", "xx3113"],
     *   "f31.f312": [[1, 2, 3], -1, -1],
     *   "f31.f313": [1, 1, 1]
     * }
     *
     * {
     *   "f31.f311": ["xx4111", "xx4112", "xx4113"],
     *   "f31.f312": [[4, 5, 6], -2, -2],
     *   "f31.f313": [2, 2, 2]
     * }
     *
     * 合并后的结果
     *
     * {
     *   "f31.f311": [["xx3111", "xx3112", "xx3113"], ["xx4111", "xx4112", "xx4113"]]
     *   "f31.f312": [[[1, 2, 3], -1, -1], [[4, 5, 6], -2, -2]],
     *   "f31.f313": [[1, 1, 1], [2, 2, 2]]
     * }
     *
     * 把上面的合并的结果再合并到owner中
     */
    private void mergeResultMapList(List<Map<String, ?>> resultMapList, Map<String, Object> targetMap, String owner) {
        Map<String, List<Object>> mergedSubResultMap = new HashMap<>();
        int valueSize = resultMapList.size();
        for (int i = 0; i < valueSize; i++) {
            Map<String, ?> resultMap = resultMapList.get(i);

            // 第一条数据时，构建属性的合并后的数组对象
            if (i == 0) {
                for (Map.Entry<String, ?> entry : resultMap.entrySet()) {
                    mergedSubResultMap.put(entry.getKey(), new ArrayList<>());
                }
            }

            // 依次把子元数据的映射结果合并到mergedSubResultMa中
            for (Map.Entry<String, ?> entry : resultMap.entrySet()) {
                List<Object> subMergedValues = mergedSubResultMap.get(entry.getKey());
                subMergedValues.add(entry.getValue());
            }
        }

        // 将子字段的处理结果合并到目标映射中
        this.mergeResultMap(mergedSubResultMap, targetMap, owner);
    }

    /**
     * 获取结果集中的列名
     *
     * @param prefixName
     * @param columnName
     * @return
     */
    private String getTargetColumnKey(String prefixName, String columnName) {
        if (prefixName != null && prefixName.length() > 0) {
            return prefixName + "." + columnName;
        } else {
            return columnName;
        }
    }
}
