package net.cf.object.engine.object;

import com.alibaba.fastjson.JSONObject;
import net.cf.commons.test.util.FileTestUtils;
import net.cf.object.engine.def.ObjectDef;
import net.cf.object.engine.def.TestObjectImpl;
import net.cf.object.engine.oql.parser.XObjectResolver;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 模型解析器
 *
 * @author clouds
 */
public final class TestObjectResolver implements XObjectResolver {

    /**
     * 模型映射表
     */
    private final Map<String, TestObjectImpl> objectMap = new HashMap<>();

    @PostConstruct
    public void loadObject() {
        // 静态初始化，从类路径加载测试时用的模型
        // 要按序初始化，因为在构建含相关表的模武时会依赖已经构建的模型
        String[] orderedObjectNames = {"Hobby", "Staff", "Travel", "TravelTrip", "Expense", "Leave"};
        Map<String, String> objectsJson = FileTestUtils.loadTextsFromClasspath("object/*.json");
        for (String objectName : orderedObjectNames) {
            String objectDefJson = objectsJson.get(objectName + ".json");
            ObjectDef objectDef = JSONObject.parseObject(objectDefJson, ObjectDef.class);
            TestObjectImpl object = new TestObjectImpl(objectDef, this);
            this.objectMap.put(object.getName(), object);
        }
    }

    @Override
    public TestObjectImpl resolve(String objectName) {
        return objectMap.get(objectName);
    }

}
