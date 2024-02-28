package net.cf.commons.test.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class JsonUtilsTest {

    @Test
    public void testLoadMap() {
        Map<String, Object> dataMap = JsonUtils.loadMapFromClasspath("json/map.json");
        Assert.assertTrue(dataMap != null && dataMap.size() == 2);
        Assert.assertTrue("xyz".equals(dataMap.get("abc")));
        Assert.assertTrue("wlz".equals(dataMap.get("efg")));
    }

    @Test
    public void testLoadList() {
        List<Map<String, Object>> dataMapList = JsonUtils.loadListFromClasspath("json/list.json");
        Assert.assertTrue(dataMapList != null && dataMapList.size() == 3);
        Assert.assertTrue("123".equals(dataMapList.get(2).get("f4")));
    }
}
