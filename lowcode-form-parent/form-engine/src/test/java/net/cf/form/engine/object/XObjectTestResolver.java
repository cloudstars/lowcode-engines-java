package net.cf.form.engine.object;

import net.cf.commons.test.util.FileUtils;
import net.cf.commons.util.ObjectUtils;
import net.cf.form.commons.ObjectDefinition;
import net.cf.form.engine.XObjectResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class XObjectTestResolver implements XObjectResolver {

    /**
     * 对象映射表
     */
    private final Map<String, XObject> objectMap = new HashMap<>();

    /**
     * 从类路径下加载测试对象
     */
    @PostConstruct
    protected void loadObjects() {
        Map<String, String> objectsJson = FileUtils.loadTextsFromClasspath("object/*.json");
        for (Map.Entry<String, String> entry : objectsJson.entrySet()) {
            ObjectDefinition objectDef = ObjectUtils.parseObject(entry.getValue(), ObjectDefinition.class);
            XObject object = new XObjectTestImpl(objectDef);
            objectMap.put(object.getName(), object);
        }
    }

    /**
     * 根据对象名称解析对象
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
