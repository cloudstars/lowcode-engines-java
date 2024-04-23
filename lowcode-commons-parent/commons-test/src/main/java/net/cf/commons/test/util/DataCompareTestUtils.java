package net.cf.commons.test.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据比较工具
 *
 * @author clouds
 */
public final class DataCompareTestUtils {

    private final static Logger logger = LoggerFactory.getLogger(DataCompareTestUtils.class);

    private DataCompareTestUtils() {
    }

    /**
     * 判断源对象中的属性是否与目标对象中的属性完全相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean equalsObject(Object source, Object target) {
        return ObjectTestUtils.compareObjectNullSafe(source, target, (s, t) -> {
            boolean isEqual;
            if (s instanceof JSONObject && t instanceof JSONObject) {
                isEqual = DataCompareTestUtils.equalsJsonObject((JSONObject) s, (JSONObject) t);
            } else if (s instanceof JSONArray && t instanceof JSONArray) {
                isEqual = DataCompareTestUtils.equalsJsonArray((JSONArray) s, (JSONArray) t);
            } else if (s instanceof Map && t instanceof Map) {
                isEqual = DataCompareTestUtils.equalsMap((Map<String, Object>) s, (Map<String, Object>) t);
            } else if (s instanceof List && t instanceof List) {
                isEqual = DataCompareTestUtils.equalsList((List<Object>) s, (List<Object>) t);
            } else if (s instanceof JSONObject && t instanceof JSONArray) {
                isEqual = false;
            } else if (s instanceof JSONArray && t instanceof JSONObject) {
                isEqual = false;
            } else {
                return ObjectTestUtils.equals(s, t);
            }

            return isEqual;
        });
    }

    /**
     * 判断源Map对象中的属性与目标Map对象中的属性是否完成相等
     *
     * @param sourceMap
     * @param targetMap
     * @return
     */
    public static boolean equalsMap(Map<String, Object> sourceMap, Map<String, Object> targetMap) {
        return ObjectTestUtils.compareObjectNullSafe(sourceMap, targetMap, (s, t) -> {
            int ss = s.size();
            int ts = t.size();
            if (ss != ts) {
                return false;
            }

            for (Map.Entry<String, Object> entry : s.entrySet()) {
                Object source = entry.getValue();
                Object target = t.get(entry.getKey());
                if (!DataCompareTestUtils.equalsObject(source, target)) {
                    logger.warn("对象的属性{}比较失败，源对象值：{}，目标对象值：{}", entry.getKey(), source, target);
                    return false;
                }
            }

            return true;
        });
    }

    /**
     * 判断源List对象中的属性与目标List对象中的属性完全相等，并且数量一致
     *
     * @param sourceList
     * @param targetList
     * @return
     */
    public static <S extends Object, T extends Object> boolean equalsList(List<S> sourceList, List<T> targetList) {
        return ObjectTestUtils.compareObjectNullSafe(sourceList, targetList, (s, t) -> {
            int ss = sourceList.size();
            int ts = targetList.size();
            if (ss != ts) {
                return false;
            }

            for (int i = 0; i < ss; i++) {
                Object source = sourceList.get(i);
                Object target = targetList.get(i);
                if (!DataCompareTestUtils.equalsObject(source, target)) {
                    logger.warn("对象比较失败，源对象：{}，目标对象：{}", JSONObject.toJSONString(source), JSONObject.toJSONString(target));
                    return false;
                }
            }

            return true;
        });
    }

    /**
     * 判断源JSONObject对象中的属性与目标JSONObject对象中的属性完全相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean equalsJsonObject(JSONObject source, JSONObject target) {
        return DataCompareTestUtils.equalsMap(source, target);
    }

    /**
     * 判断源JSONArray对象中的属性是否与目标JSONArray对象中的属性完全相等，并且数量一致
     *
     * @param sourceArray
     * @param targetArray
     * @return
     */
    public static boolean equalsJsonArray(JSONArray sourceArray, JSONArray targetArray) {
        return DataCompareTestUtils.equalsList(sourceArray, targetArray);
    }

    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isAssignableFromObject(Object source, Object target) {
        return ObjectTestUtils.compareObjectNullSafe(source, target, (s, t) -> {
            boolean isAssignable;
            if (s instanceof JSONObject && t instanceof JSONObject) {
                isAssignable = DataCompareTestUtils.isAssignableFromJsonObject((JSONObject) s, (JSONObject) t);
            } else if (s instanceof JSONArray && t instanceof JSONArray) {
                isAssignable = DataCompareTestUtils.isAssignableFromJsonArray((JSONArray) s, (JSONArray) t);
            } else if (s instanceof Map && t instanceof Map) {
                isAssignable = DataCompareTestUtils.isAssignableFromMap((Map<String, Object>) s, (Map<String, Object>) t);
            } else if (s instanceof JSONObject && t instanceof JSONArray) {
                isAssignable = false;
            } else if (s instanceof JSONArray && t instanceof JSONObject) {
                isAssignable = false;
            } else {
                return ObjectTestUtils.isAssignableFrom(s, t);
            }

            return isAssignable;
        });
    }


    /**
     * 判断源Map对象中的属性是否在目标Map对象中都存在，并且值相等
     *
     * @param sourceMap
     * @param targetMap
     * @return
     */
    public static boolean isAssignableFromMap(Map<String, Object> sourceMap, Map<String, Object> targetMap) {
        return ObjectTestUtils.compareObjectNullSafe(sourceMap, targetMap, (s, v) -> {
            List<String> fieldNames = sourceMap.keySet().stream().collect(Collectors.toList());
            return DataCompareTestUtils.isAssignableFromMapWithProperties(s, v, fieldNames);
        });
    }

    /**
     * 判断源List对象中的属性是否在目标List对象中都存在，并且值相等，并且数量一致
     *
     * @param sourceList
     * @param targetList
     * @return
     */
    public static <S extends Object, T extends Object> boolean isAssignableFromList(List<S> sourceList, List<T> targetList) {
        return ObjectTestUtils.compareObjectNullSafe(sourceList, targetList, (s, v) -> {
            List<String> fieldNames = ObjectTestUtils.getDeclaredFieldNames(s);
            return DataCompareTestUtils.isAssignableFromListWithProperties(s, v, fieldNames);
        });
    }

    /**
     * 判断源对象JSONObject中的属性是否在目标JSONObject对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isAssignableFromJsonObject(JSONObject source, JSONObject target) {
        return DataCompareTestUtils.isAssignableFromMap(source, target);
    }

    /**
     * 判断源JSONArray对象中的属性是否在目标JSONArray对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isAssignableFromJsonArray(JSONArray source, JSONArray target) {
        return DataCompareTestUtils.isAssignableFromList(source, target);
    }


    /**
     * 判断源对象中的指定属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isAssignableFromObjectWithProperties(Object source, Object target, List<String> properties) {
        return ObjectTestUtils.compareObjectNullSafe(source, target, (s, t) -> {
            // 只要有一个是基础类型就直接比较
            if (ObjectTestUtils.isGeneralValue(s) || ObjectTestUtils.isGeneralValue(t)) {
                return s.equals(t);
            }

            boolean isEqual;
            if (s instanceof JSONObject && t instanceof JSONObject) {
                isEqual = DataCompareTestUtils.isAssignableFromJsonObjectWithProperties((JSONObject) s, (JSONObject) t, properties);
            } else if (s instanceof JSONArray && t instanceof JSONArray) {
                isEqual = DataCompareTestUtils.isAssignableFromJsonArrayWithProperties((JSONArray) s, (JSONArray) t, properties);
            } else if (s instanceof Map && t instanceof Map) {
                isEqual = DataCompareTestUtils.isAssignableFromMapWithProperties((Map<String, Object>) s, (Map<String, Object>) t, properties);
            } else if (s instanceof JSONObject && t instanceof JSONArray) {
                isEqual = false;
            } else if (s instanceof JSONArray && t instanceof JSONObject) {
                isEqual = false;
            } else {
                // 两个都是对象类型时，简单起见，统一序列化为字符串后再反序列化
                Object sv = JSON.parse(JSON.toJSONString(s, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue));
                Object tv = JSON.parse(JSON.toJSONString(t, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue));
                if (sv instanceof JSONObject && tv instanceof JSONObject) {
                    isEqual = DataCompareTestUtils.isAssignableFromJsonObjectWithProperties((JSONObject) sv, (JSONObject) tv, properties);
                } else if (sv instanceof JSONArray && tv instanceof JSONArray) {
                    isEqual = DataCompareTestUtils.isAssignableFromJsonArrayWithProperties((JSONArray) sv, (JSONArray) tv, properties);
                } else {
                    isEqual = ObjectTestUtils.isAssignableFromWithProperties(s, t, properties);
                }
            }

            return isEqual;
        });
    }

    /**
     * 判断源Map对象中指定的属性是否在目标Map对象中都存在，并且值相等
     *
     * @param sourceMap
     * @param targetMap
     * @return
     */
    public static boolean isAssignableFromMapWithProperties(Map<String, Object> sourceMap, Map<String, Object> targetMap, List<String> properties) {
        return ObjectTestUtils.compareObjectNullSafe(sourceMap, targetMap, (s, t) -> {
            for (String property : properties) {
                Object source = sourceMap.get(property);
                Object target = targetMap.get(property);
                if (!DataCompareTestUtils.isAssignableFromObjectWithProperties(source, target, properties)) {
                    return false;
                }
            }

            return true;
        });
    }


    /**
     * 判断源对象中指定的属性是否在目标对象中都存在，并且值相等
     *
     * @param sourceList
     * @param targetList
     * @return
     */
    public static <S extends Object, T extends Object> boolean isAssignableFromListWithProperties(List<S> sourceList, List<T> targetList, List<String> properties) {
        return ObjectTestUtils.compareObjectNullSafe(sourceList, targetList, (s, t) -> {
            if (s.size() != t.size()) {
                return false;
            }

            for (int i = 0, l = s.size(); i < l; i++) {
                Object source = sourceList.get(i);
                Object target = targetList.get(i);
                if (!DataCompareTestUtils.isAssignableFromObjectWithProperties(source, target, properties)) {
                    return false;
                }
            }

            return true;
        });
    }


    /**
     * 判断源对象中指定的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isAssignableFromJsonObjectWithProperties(JSONObject source, JSONObject target, List<String> properties) {
        return DataCompareTestUtils.isAssignableFromObjectWithProperties(source, target, properties);
    }


    /**
     * 判断源对象中指定的属性是否在目标对象中都存在，并且值相等
     *
     * @param sourceArray
     * @param targetArray
     * @return
     */
    public static boolean isAssignableFromJsonArrayWithProperties(JSONArray sourceArray, JSONArray targetArray, List<String> properties) {
        return DataCompareTestUtils.isAssignableFromListWithProperties(sourceArray, targetArray, properties);
    }

}
