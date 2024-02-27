package net.cf.object.engine.object;

import com.alibaba.fastjson.JSONObject;
import net.cf.commons.test.util.FileUtils;
import net.cf.object.engine.def.ObjectDefinition;
import net.cf.object.engine.def.ObjectTestImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class XObjectTestResolver implements XObjectResolver {

    /**
     * 模型映射表
     */
    private final Map<String, XObject> objectMap = new HashMap<>();

    /**
     * 从类路径下加载测试模型
     */
    @PostConstruct
    protected void loadObjects() {
        Map<String, String> objectsJson = FileUtils.loadTextsFromClasspath("object/*.json");
        for (Map.Entry<String, String> entry : objectsJson.entrySet()) {
            ObjectDefinition objectDef = JSONObject.parseObject(entry.getValue(), ObjectDefinition.class);
            XObject object = new ObjectTestImpl(objectDef);
            objectMap.put(object.getCode(), object);
        }
    }

    /**
     * 根据模型名称解析模型
     *
     * @param objectName
     * @return
     */
    @Override
    public XObject resolveObject(String objectName) {
        XObject object = objectMap.get(objectName);
        assert (object != null);
        return object;
    }
}
