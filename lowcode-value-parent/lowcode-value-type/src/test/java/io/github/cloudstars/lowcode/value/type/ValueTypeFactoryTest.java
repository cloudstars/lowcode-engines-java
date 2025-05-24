package io.github.cloudstars.lowcode.value.type;

import io.github.cloudstars.lowcode.CommonsValueTypeTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 测试工厂能加载到正确的类型，不关注细节
 *
 * @author clouds 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsValueTypeTestApplication.class)
public class ValueTypeFactoryTest {

    @Test
    public void testArray() {
        JsonObject configJson= JsonUtils.loadJsonObjectFromClasspath("value/type/array/array-text.json");
        XValueTypeConfig valueType = ValueTypeConfigFactory.newInstance(configJson);
        Assert.assertEquals(ArrayValueTypeConfig.class, valueType.getClass());
    }

    @Test
    public void testBoolean() {
        JsonObject configJson= JsonUtils.loadJsonObjectFromClasspath("value/type/boolean/boolean-simple.json");
        XValueTypeConfig valueType = ValueTypeConfigFactory.newInstance(configJson);
        Assert.assertEquals(BooleanValueTypeConfig.class, valueType.getClass());
    }

}
