package io.github.cloudstars.lowcode.commons.utils.js;

import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyArray;
import org.graalvm.polyglot.proxy.ProxyObject;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 数据转换器
 *
 * @author clouds
 */
final class JsDataConverter {

    private JsDataConverter() {}

    /**
     * 将js的值转为Java的对象
     *
     * @param value
     * @return
     */
    public static Object toJavaObject(Value value) {
        if (value == null) {
            return null;
        }

        Object result = null;
        if (value.isNull()) {
            result = null;
        } else if (value.canExecute()) {
            // 是一个函数，原封不动返回
            result = new JsFunction(value);
        } else if (value.hasArrayElements()) {
            result = new ArrayList<>();
            for (long i = 0, l = value.getArraySize(); i < l; i++) {
                Value arrayItemValue = value.getArrayElement(i);
                ((ArrayList) result).add(JsDataConverter.toJavaObject(arrayItemValue));
            }
        } else if (value.hasMembers()) {
            result = new HashMap<>();
            Set<String> memberKeys = value.getMemberKeys();
            for (String memberKey : memberKeys) {
                ((Map) result).put(memberKey, JsDataConverter.toJavaObject(value.getMember(memberKey)));
            }
        } else if (value.isString()) {
            result = value.asString();
        } else if (value.isBoolean()) {
            result = value.asBoolean();
        } else if (value.isNumber()) {
            if (value.fitsInLong()) {
                result = value.asLong();
            } else if (value.fitsInInt()) {
                result = value.asInt();
            } else if (value.fitsInShort()) {
                result = value.asShort();
            } else if (value.fitsInDouble()) {
                result = value.asDouble();
            } else if (value.fitsInFloat()) {
                result = value.asFloat();
            }
        } else if (value.isDate()) {
            result = value.asDate();
        } else if (value.isTime()) {
            result = value.asTime();
        }

        return result;
    }

    /**
     * 将Java的对象转换为JS的对象
     *
     * todo 需要递归处理自定义对象
     * @param value
     * @return
     */
    public static Object toJsObject(Object value) {
        if (value == null) {
            return null;
        }

        if (JsDataConverter.isGeneralValue(value)) {
            return value;
        }

        if (value.getClass().isEnum()) {
            return value.toString();
        }

        Object result;
        if (value instanceof Collection) {
            List<Object> listObject = new ArrayList<>();
            for (Object subItem : (List) value) {
                listObject.add(JsDataConverter.toJsObject(subItem));
            }
            result = ProxyArray.fromList(listObject);
        } else if (value instanceof Map) {
            Map<String, Object> mapObject = new HashMap<>();
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) value).entrySet()) {
                mapObject.put(entry.getKey(), JsDataConverter.toJsObject(entry.getValue()));
            }
            result = ProxyObject.fromMap(mapObject);
        } else {
            // 认为是一个自定义对象
            Map<String, Object> mapObject = new HashMap<>();
            Field[] fields = value.getClass().getDeclaredFields();
            for (int i = 0, l = fields.length; i < l; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                try {
                    Object fieldValue = field.get(value);
                    mapObject.put(field.getName(), JsDataConverter.toJsObject(fieldValue));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            result = ProxyObject.fromMap(mapObject);
        }

        return result;
    }

    /**
     * 是否基础类型的值
     *
     * @param value
     */
    private static boolean isGeneralValue(Object value) {
        if (value == null) {
            return true;
        }

        return (value instanceof String || value instanceof Number || value instanceof Boolean || value instanceof Date);
    }


}
