package io.github.cloudstars.lowcode.commons.utils.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * FastJSON工具测试类
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class FastJsonUtilsTest {

    @Test
    public void testWrapJsonObject() {
        JSONObject jsonObject = new JSONObject();
        Object wrappedObject = FastJsonUtils.wrap(jsonObject);
        Assert.assertEquals(JsonObject.class, wrappedObject.getClass());
    }

    @Test
    public void testWrapJsonArray() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONObject());
        jsonArray.add(new Object());
        Object wrappedObject = FastJsonUtils.wrap(jsonArray);
        Assert.assertEquals(JsonArray.class, wrappedObject.getClass());
        JsonArray wrappedJsonArray = (JsonArray) wrappedObject;
        Assert.assertEquals(JsonObject.class, wrappedJsonArray.get(0).getClass());
        Assert.assertEquals(Object.class, wrappedJsonArray.get(1).getClass());
    }

    @Test
    public void testWrapJsonArrayForEach() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONObject());
        jsonArray.add(new JSONObject());
        Object wrappedObject = FastJsonUtils.wrap(jsonArray);
        Assert.assertEquals(JsonArray.class, wrappedObject.getClass());
        JsonArray wrappedJsonArray = (JsonArray) wrappedObject;
        wrappedJsonArray.forEach((item) -> {
            Assert.assertEquals(JsonObject.class, item.getClass());
        });
    }

}
