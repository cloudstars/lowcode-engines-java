package io.github.cloudstars.lowcode.commons.lang.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.*;

@RunWith(JUnit4ClassRunner.class)
public class ObjectPropertyUtilsTest {

    @Test
    public void testGetFromMap() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("a", "AAA");
        dataMap.put("b", 123);
        Map<String, Object> dataCMap = new HashMap<>();
        dataCMap.put("a", "AAA");
        dataCMap.put("b", 123);
        dataMap.put("c", dataCMap);

        Object valueA = ObjectPropertyUtils.getPropertyValue(dataMap, "a");
        Assert.assertEquals("AAA", valueA);
        Object valueB = ObjectPropertyUtils.getPropertyValue(dataMap, "b");
        Assert.assertEquals(123, valueB);
        Object valueC = ObjectPropertyUtils.getPropertyValue(dataMap, "c");
        Assert.assertEquals(dataCMap, valueC);
        Object valueD = ObjectPropertyUtils.getPropertyValue(dataMap, "d");
        Assert.assertNull(valueD);
        Object valueCA = ObjectPropertyUtils.getPropertyValue(dataMap, "c.a");
        Assert.assertEquals("AAA", valueCA);
        Object valueCB = ObjectPropertyUtils.getPropertyValue(dataMap, "c.b");
        Assert.assertEquals(123, valueCB);
        Object valueCC = ObjectPropertyUtils.getPropertyValue(dataMap, "c.c");
        Assert.assertNull(valueCC);
    }

    @Test
    public void testGetFromList() throws Exception {
        List<Map> dataList = new ArrayList<>();


        Map<String, Object> data1Map = new HashMap<>();
        data1Map.put("a", "AAA");
        data1Map.put("b", 123);
        Map<String, Object> data1CMap = new HashMap<>();
        data1CMap.put("a", "AAA");
        data1CMap.put("b", 123);
        data1Map.put("c", data1CMap);
        dataList.add(data1Map);

        Map<String, Object> data2Map = new HashMap<>();
        data2Map.put("a", "BBB");
        data2Map.put("b", 456);
        Map<String, Object> data2CMap = new HashMap<>();
        data2CMap.put("a", "BBB");
        data2CMap.put("b", 456);
        data2Map.put("c", data2CMap);
        dataList.add(data2Map);

        Object valueA = ObjectPropertyUtils.getPropertyValue(dataList, "a");
        Assert.assertEquals(Arrays.asList("AAA", "BBB"), valueA);
        Object valueB = ObjectPropertyUtils.getPropertyValue(dataList, "b");
        Assert.assertEquals(Arrays.asList(123, 456), valueB);
        Object valueC = ObjectPropertyUtils.getPropertyValue(dataList, "c");
        Assert.assertEquals(Arrays.asList(data1CMap, data2CMap), valueC);
        Object valueD = ObjectPropertyUtils.getPropertyValue(dataList, "d");
        Assert.assertNull(valueD);
        Object valueCA = ObjectPropertyUtils.getPropertyValue(dataList, "c.a");
        Assert.assertEquals(Arrays.asList("AAA", "BBB"), valueCA);
        Object valueCB = ObjectPropertyUtils.getPropertyValue(dataList, "c.b");
        Assert.assertEquals(Arrays.asList(123, 456), valueCB);
        Object valueCC = ObjectPropertyUtils.getPropertyValue(dataList, "c.c");
        Assert.assertNull(valueCC);

    }

}
