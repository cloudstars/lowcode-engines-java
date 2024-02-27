package net.cf.commons.test.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据比较工具
 *
 * @author clouds
 */
public final class DataCompareUtils {

    private DataCompareUtils() {
    }

    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean equalsObject(Object source, Object target) {
        return ObjectUtils.equalsObject(source, target, (s, t) -> {
            boolean isEqual;
            if (source instanceof JSONObject && target instanceof JSONObject) {
                isEqual = DataCompareUtils.equalsJsonObject((JSONObject) source, (JSONObject) target);
            } else if (source instanceof JSONArray && source instanceof JSONArray) {
                isEqual = DataCompareUtils.equalsJsonArray((JSONArray) source, (JSONArray) target);
            } else if (source instanceof JSONObject && target instanceof JSONArray) {
                isEqual = false;
            } else if (source instanceof JSONArray && target instanceof JSONObject) {
                isEqual = false;
            } else {
                isEqual = source.equals(target);
            }

            return isEqual;
        });
    }

    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static <S extends Object, T extends Object> boolean equalsList(List<S> source, List<T> target) {
        return ObjectUtils.equalsList(source, target, (s, t) -> DataCompareUtils.equalsObject(s, t));
    }

    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean equalsJsonObject(JSONObject source, JSONObject target) {
        return ObjectUtils.equalsObject(source, target, (s, t) -> {
            int sl = source.size();
            int tl = target.size();
            if (sl != tl) {
                return false;
            }

            Set<String> keys = source.keySet();
            for (String key : keys) {
                Object sv = source.get(key);
                Object tv = target.get(key);
                if (!DataCompareUtils.equalsObject(sv, tv)) {
                    return false;
                }
            }
            return true;
        });
    }

    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean equalsJsonArray(JSONArray source, JSONArray target) {
        return ObjectUtils.equalsObject(source, target, (s, t) -> {
            int sl = source.size();
            int tl = target.size();
            if (sl != tl) {
                return false;
            }

            for (int i = 0; i < sl; i++) {
                Object sv = source.get(i);
                Object tv = target.get(i);
                if (!DataCompareUtils.equalsObject(sv, tv)) {
                    return false;
                }
            }

            return true;
        });
    }

    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean equalsObjectWithProperties(Object source, Object target, List<String> properties) {
        return ObjectUtils.equalsObject(source, target, (s, t) -> {
            // 只要有一个是基础类型就直接比较
            if (ObjectUtils.isGeneralValue(source) || ObjectUtils.isGeneralValue(target)) {
                return source.equals(target);
            }

            boolean isEqual;
            if (source instanceof JSONObject && source instanceof JSONObject) {
                isEqual = DataCompareUtils.equalsJsonObjectWithProperties((JSONObject) source, (JSONObject) target, properties);
            } else if (source instanceof JSONArray && target instanceof JSONArray) {
                isEqual = DataCompareUtils.equalsJsonArrayWithProperties((JSONArray) source, (JSONArray) target, properties);
            } else if (source instanceof JSONObject && target instanceof JSONArray) {
                isEqual = false;
            } else if (source instanceof JSONArray && target instanceof JSONObject) {
                isEqual = false;
            } else {
                // 两个都是对象类型时，简单起见，统一转见JSON字符串后再反序列化
                Object sv = JSON.parse(JSON.toJSONString(source, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue));
                Object tv = JSON.parse(JSON.toJSONString(target, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue));
                if (sv instanceof JSONObject && tv instanceof JSONObject) {
                    isEqual = DataCompareUtils.equalsJsonObjectWithProperties((JSONObject) sv, (JSONObject) tv, properties);
                } else if (sv instanceof JSONArray && tv instanceof JSONArray) {
                    isEqual = DataCompareUtils.equalsJsonArrayWithProperties((JSONArray) sv, (JSONArray) tv, properties);
                } else {
                    isEqual = sv.equals(tv);
                }
            }

            return isEqual;
        });
    }

    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param sourceList
     * @param targetList
     * @return
     */
    public static <S extends Object, T extends Object> boolean equalsListWithProperties(List<S> sourceList, List<T> targetList, List<String> properties) {
        return ObjectUtils.equalsList(sourceList, targetList, (s, t) -> DataCompareUtils.equalsObjectWithProperties(s, t, properties));
    }


    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean equalsJsonObjectWithProperties(JSONObject source, JSONObject target, List<String> properties) {
        return ObjectUtils.equalsObject(source, target, (s, t) -> {
            for (String property : properties) {
                Object svi = source.get(property);
                Object tvi = target.get(property);
                if (!DataCompareUtils.equalsObjectWithProperties(svi, tvi, properties)) {
                    return false;
                }
            }

            return true;
        });
    }


    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param sourceArray
     * @param targetArray
     * @return
     */
    public static boolean equalsJsonArrayWithProperties(JSONArray sourceArray, JSONArray targetArray, List<String> properties) {
        return ObjectUtils.equalsList(sourceArray, targetArray, (s, t) -> DataCompareUtils.equalsObjectWithProperties(s, t, properties));
    }

    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isAssignableFromObject(Object source, Object target) {
        if (source == null) {
            return true;
        } else if (target == null) {
            return false;
        }

        // 只要有一个是基础类型就直接比较
        if (ObjectUtils.isGeneralValue(source) || ObjectUtils.isGeneralValue(target)) {
            return source.equals(target);
        }

        boolean isAssignableFrom;
        if (source instanceof JSONObject && target instanceof JSONObject) {
            isAssignableFrom = DataCompareUtils.isAssignableFromJsonObject((JSONObject) source, (JSONObject) target);
        } else if (source instanceof JSONArray && source instanceof JSONArray) {
            isAssignableFrom = DataCompareUtils.isAssignableFromJsonArray((JSONArray) source, (JSONArray) target);
        } else if (source instanceof JSONObject && target instanceof JSONArray) {
            isAssignableFrom = false;
        } else if (source instanceof JSONArray && target instanceof JSONObject) {
            isAssignableFrom = false;
        } else {
            // 两个都是对象类型时
            Object sourceJson = JSON.parse(JSON.toJSONString(source));
            Object targetJson = JSON.parse(JSON.toJSONString(target));
            if (sourceJson instanceof JSONObject && targetJson instanceof JSONObject) {
                isAssignableFrom = DataCompareUtils.isAssignableFromJsonObject((JSONObject) sourceJson, (JSONObject) targetJson);
            } else if (sourceJson instanceof JSONArray && targetJson instanceof JSONArray) {
                isAssignableFrom = DataCompareUtils.isAssignableFromJsonArray((JSONArray) sourceJson, (JSONArray) targetJson);
            } else {
                isAssignableFrom = sourceJson.equals(targetJson);
            }
        }

        return isAssignableFrom;
    }


    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isAssignableFromList(List<Object> source, List<Object> target) {
        if (source == null) {
            return true;
        } else if (target == null) {
            return false;
        }

        if (source.size() > target.size()) {
            return false;
        }

        for (int i = 0, l = source.size(); i < l; i++) {
            Object svi = source.get(i);
            Object tvi = target.get(i);
            if (!DataCompareUtils.isAssignableFromObject(svi, tvi)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isAssignableFromJsonObject(JSONObject source, JSONObject target) {
        if (source == null) {
            return true;
        } else if (target == null) {
            return false;
        }

        if (source.size() > target.size()) {
            return false;
        }

        for (Map.Entry<String, Object> entry : source.entrySet()) {
            Object svi = source.get(entry.getKey());
            Object tvi = target.get(entry.getKey());
            if (!DataCompareUtils.isAssignableFromObject(svi, tvi)) {
                return false;
            }
        }

        return true;
    }


    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isAssignableFromJsonArray(JSONArray source, JSONArray target) {
        return DataCompareUtils.isAssignableFromList(source, target);
    }

}
