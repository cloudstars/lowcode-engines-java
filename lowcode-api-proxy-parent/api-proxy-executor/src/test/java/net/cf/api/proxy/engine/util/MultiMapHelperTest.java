package net.cf.api.proxy.engine.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/30 11:23
 */

public class MultiMapHelperTest {
    private static final Logger log = LoggerFactory.getLogger(MultiMapHelperTest.class);

    @Test
    public void toMultiMapNormTest1() {
        JSONObject map = JSON.parseObject("{\"test\" : \"test\"}");
        MultiValueMap<String, String> multiValueMap = MultiMapHelper.toMultiMap(map);
        Assert.assertEquals(multiValueMap.get("test").get(0), "test");
    }

    @Test
    public void toMultiMapNormTest2() {
        JSONObject map = JSON.parseObject("{\"test\" : \"test\"}");
        MultiValueMap<String, String> multiValueMap = MultiMapHelper.toMultiMap(map);
        Assert.assertEquals(multiValueMap.get("test").size(), 1);
    }

    @Test
    public void toMultiMapNormTest3() {
        JSONObject map = JSON.parseObject("{\"test\" : [\"test\"]}");
        MultiValueMap<String, String> multiValueMap = MultiMapHelper.toMultiMap(map);
        log.info("{}", multiValueMap);
        Assert.assertEquals(multiValueMap.get("test").get(0), "test");
    }

    @Test
    public void toMultiMapNormTest4() {
        JSONObject map = JSON.parseObject("{\"test\" : [\"test\"]}");
        MultiValueMap<String, String> multiValueMap = MultiMapHelper.toMultiMap(map);
        log.info("{}", multiValueMap);
        Assert.assertEquals(multiValueMap.get("test").size(), 1);
    }

    @Test
    public void toMultiMapNormTest5() {
        JSONObject map = JSON.parseObject("{\"test\" : [\"test\", \"test\", \"test\"]}");
        MultiValueMap<String, String> multiValueMap = MultiMapHelper.toMultiMap(map);
        log.info("{}", multiValueMap);
        Assert.assertEquals(multiValueMap.get("test").size(), 3);
    }

    @Test
    public void toMultiMapNullTest() {
        MultiValueMap<String, String> stringStringMultiValueMap = MultiMapHelper.toMultiMap(null);
        Assert.assertNull(stringStringMultiValueMap);
    }

    @Test
    public void toMultiMapExceptionTest() {
        JSONObject map = JSON.parseObject("{\"test\" : [\"test\", \"test\", 1]}");
        Assert.assertThrows(IllegalArgumentException.class, () -> MultiMapHelper.toMultiMap(map));
    }
    @Test
    public void toMultiMapExceptionTest2() {
        JSONObject map = JSON.parseObject("{\"test\" : 1}");
        Assert.assertThrows(IllegalArgumentException.class, () -> MultiMapHelper.toMultiMap(map));
    }


    @Test
    public void toSingleMapNormTest() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        Map<String, Object> map = MultiMapHelper.toSingleMap(multiValueMap);
        Assert.assertNotNull(map);
    }

    @Test
    public void toSingleMapNormTest1() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("test", "test");
        Map<String, Object> map = MultiMapHelper.toSingleMap(multiValueMap);
        Assert.assertEquals(map.get("test"), "test");
    }
    @Test
    public void toSingleMapNormTest2() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("test", "test");
        multiValueMap.add("test", "test");
        Map<String, Object> map = MultiMapHelper.toSingleMap(multiValueMap);
        Assert.assertTrue(map.get("test")instanceof List);
    }
    @Test
    public void toSingleMapNormTest3() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("test", "test");
        multiValueMap.add("test", "test");
        Map<String, Object> map = MultiMapHelper.toSingleMap(multiValueMap);
        Object test = map.get("test");
        List<String> test1 = (List<String>) test;
        assertEquals("test", test1.get(1));
    }

    @Test
    public void toSingleMapNullTest() {
        Map<String, Object> map = MultiMapHelper.toSingleMap(null);
        Assert.assertNull(map);
    }

}