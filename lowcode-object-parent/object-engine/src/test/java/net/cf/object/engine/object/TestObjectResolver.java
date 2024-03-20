package net.cf.object.engine.object;

import com.alibaba.fastjson.JSONObject;
import net.cf.commons.test.util.FileTestUtils;
import net.cf.object.engine.def.ObjectDef;
import net.cf.object.engine.def.TestObjectImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型解析器
 *
 * @author clouds
 */
public class TestObjectResolver {

    /**
     * 模型映射表
     */
    private final static Map<String, TestObjectImpl> objectMap = new HashMap<>();

    private TestObjectResolver() {
    }

    static  {
        /**
         * 从类路径下加载测试模型
         */
        Map<String, String> objectsJson = FileTestUtils.loadTextsFromClasspath("object/*.json");
        for (Map.Entry<String, String> entry : objectsJson.entrySet()) {
            ObjectDef objectDef = JSONObject.parseObject(entry.getValue(), ObjectDef.class);
            TestObjectImpl object = new TestObjectImpl(objectDef);
            objectMap.put(object.getName(), object);
        }
    }

    /**
     * 根据模型名称解析模型
     *
     * @param objectCode
     * @return
     */
    public static TestObjectImpl resolveObject(String objectCode) {
        TestObjectImpl object = objectMap.get(objectCode);
        assert (object != null);
        return object;
    }
}
