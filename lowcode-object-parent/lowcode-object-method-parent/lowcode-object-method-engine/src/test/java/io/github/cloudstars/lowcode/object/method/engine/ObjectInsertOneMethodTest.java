package io.github.cloudstars.lowcode.object.method.engine;


import io.github.cloudstars.lowcode.ObjectMethodEngineTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.object.core.editor.ObjectConfigFactory;
import io.github.cloudstars.lowcode.object.core.editor.XObjectConfig;
import io.github.cloudstars.lowcode.object.core.editor.XObjectConfigResolver;
import io.github.cloudstars.lowcode.object.method.editor.ObjectInsertOneMethodConfig;
import io.github.cloudstars.object.core.engine.XObject;
import io.github.cloudstars.object.core.engine.XObjectImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectMethodEngineTestApplication.class)
public class ObjectInsertOneMethodTest {

    @Resource
    private XObjectConfigResolver objectConfigResolver;

    @Test
    public void test1() {
        JsonObject objectConfigJson = JsonUtils.loadJsonObjectFromClasspath("object/object-apply.json");
        XObjectConfig objectConfig = ObjectConfigFactory.newInstance(objectConfigJson);
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("object/method/insert/insert-one-1.json");
        ObjectInsertOneMethodConfig methodConfig = new ObjectInsertOneMethodConfig(configJson);
        ObjectInsertOneMethod methodImpl = new ObjectInsertOneMethod(methodConfig);
        XObject object = new XObjectImpl(objectConfig);
        object.setValue("a", "aaa");
        int effectedRows = methodImpl.execute(object);
        Assert.assertEquals(1, effectedRows);
    }

}
