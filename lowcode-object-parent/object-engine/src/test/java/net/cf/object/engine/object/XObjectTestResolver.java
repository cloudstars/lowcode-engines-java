package net.cf.object.engine.object;

import com.alibaba.fastjson.JSONObject;
import net.cf.commons.test.util.FileUtils;
import net.cf.object.engine.def.ObjectDefinition;
import net.cf.object.engine.def.ObjectTestImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型解析器
 *
 * @author clouds
 */
public class XObjectTestResolver {

    /**
     * 模型映射表
     */
    private final static Map<String, ObjectTestImpl> objectMap = new HashMap<>();

    private XObjectTestResolver() {
    }

    static  {
        /**
         * 从类路径下加载测试模型
         */
        Map<String, String> objectsJson = FileUtils.loadTextsFromClasspath("object/*.json");
        for (Map.Entry<String, String> entry : objectsJson.entrySet()) {
            ObjectDefinition objectDef = JSONObject.parseObject(entry.getValue(), ObjectDefinition.class);
            ObjectTestImpl object = new ObjectTestImpl(objectDef);
            objectMap.put(object.getCode(), object);
        }
    }

    /**
     * 根据模型名称解析模型
     *
     * @param objectCode
     * @return
     */
    public static ObjectTestImpl resolveObject(String objectCode) {
        ObjectTestImpl object = objectMap.get(objectCode);
        assert (object != null);
        return object;
    }
}
