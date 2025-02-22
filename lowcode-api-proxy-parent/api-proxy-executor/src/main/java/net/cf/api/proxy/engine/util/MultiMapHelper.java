package net.cf.api.proxy.engine.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 多值map处理工具类
 * @author 80345746
 * @version v1.0
 * @date 2024/1/29 15:37
 */
public class MultiMapHelper {

    private MultiMapHelper() {
    }

    public static MultiValueMap<String, String> toMultiMap(Map<String, Object> map) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap();
        if (Objects.isNull(map)) {
            return null;
        }
        map.forEach((k, v) -> {
            if (v instanceof List) {
                ((List<?>) v).forEach(e -> multiValueMap.add(k, castValueType(e)));
            } else {
                multiValueMap.add(k, castValueType(v));
            }
        });
        return multiValueMap;
    }

    public static Map<String, Object> toSingleMap(MultiValueMap<String, String> multiValueMap) {
        if (Objects.isNull(multiValueMap)) {
            return null;
        }
        Map<String, Object> map = new HashMap<>(multiValueMap.size());
        multiValueMap.forEach((k, v) -> {
            if (v.size() > 1) {
                map.put(k, v);
            } else {
                map.put(k, v.get(0));
            }
        });
        return map;
    }

    private static String castValueType(Object obj) {
        if (obj instanceof String) {
            return ((String) obj);
        }
        throw new IllegalArgumentException("参数有误，value的类型必须为String");
    }
}
