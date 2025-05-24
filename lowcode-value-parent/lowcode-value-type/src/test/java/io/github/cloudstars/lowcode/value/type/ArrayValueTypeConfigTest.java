package io.github.cloudstars.lowcode.value.type;

import io.github.cloudstars.lowcode.CommonsValueTypeTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsValueTypeTestApplication.class)
public class ArrayValueTypeConfigTest {

    /**
     * 测试一个简单的文本数组
     */
    @Test
    public void testArray() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("value/type/array/array-text.json");
        XValueTypeConfig valueType = ValueTypeConfigFactory.newInstance(configJson);
        Assert.assertEquals(ArrayValueTypeConfig.class, valueType.getClass());
        JsonTestUtils.assertDerivedFrom(configJson, valueType.toJson());
    }

    /**
     * 测试一个对象数组
     */
    @Test
    public void testArrayWithObjectItems() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("value/type/array/array-object.json");
        XValueTypeConfig valueType = ValueTypeConfigFactory.newInstance(configJson);
        Assert.assertEquals(ArrayValueTypeConfig.class, valueType.getClass());
        JsonTestUtils.assertDerivedFrom(configJson, valueType.toJson());
    }

    /**
     * 测试一个选项数组
     */
    @Test
    public void testArrayWithOptionItems() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("value/type/array/array-option.json");
        XValueTypeConfig valueType = ValueTypeConfigFactory.newInstance(configJson);
        Assert.assertEquals(ArrayValueTypeConfig.class, valueType.getClass());
        JsonTestUtils.assertDerivedFrom(configJson, valueType.toJson());
    }

}
