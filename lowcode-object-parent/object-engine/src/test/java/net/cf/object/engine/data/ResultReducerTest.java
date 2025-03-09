package net.cf.object.engine.data;

import io.github.cloudstars.lowcode.commons.test.util.DataCompareTestUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class ResultReducerTest {

    @Test
    public void test1() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList1Out.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList1();
        DefaultResultReducer reducer = new DefaultResultReducer(valueDefs);
        Map<String, Object> reduceResult = reducer.reduceResult(dataMap);

        Map<String, Object> expectedReduceResult = JsonTestUtils.loadMapFromClasspath("data/mapList1In.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(reduceResult, expectedReduceResult));
    }

    @Test
    public void test2() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList2Out.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList2();
        DefaultResultReducer reducer = new DefaultResultReducer(valueDefs);
        Map<String, Object> reduceResult = reducer.reduceResult(dataMap);

        Map<String, Object> expectedReduceResult = JsonTestUtils.loadMapFromClasspath("data/mapList2In.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(reduceResult, expectedReduceResult));
    }

    @Test
    public void test3() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList3Out.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList3();
        DefaultResultReducer reducer = new DefaultResultReducer(valueDefs);
        Map<String, Object> reduceResult = reducer.reduceResult(dataMap);

        Map<String, Object> expectedReduceResult = JsonTestUtils.loadMapFromClasspath("data/mapList3In.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(reduceResult, expectedReduceResult));
    }

    @Test
    public void test4() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList4Out.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList4();
        DefaultResultReducer reducer = new DefaultResultReducer(valueDefs);
        Map<String, Object> reduceResult = reducer.reduceResult(dataMap);

        Map<String, Object> expectedReduceResult = JsonTestUtils.loadMapFromClasspath("data/mapList4In.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(reduceResult, expectedReduceResult));
    }

    @Test
    public void test5() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList5Out.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList5();
        DefaultResultReducer reducer = new DefaultResultReducer(valueDefs);
        Map<String, Object> reduceResult = reducer.reduceResult(dataMap);

        Map<String, Object> expectedReduceResult = JsonTestUtils.loadMapFromClasspath("data/mapList5In.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(reduceResult, expectedReduceResult));
    }

    @Test
    public void test6() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList6Out.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList6();
        DefaultResultReducer reducer = new DefaultResultReducer(valueDefs);
        Map<String, Object> reduceResult = reducer.reduceResult(dataMap);

        Map<String, Object> expectedReduceResult = JsonTestUtils.loadMapFromClasspath("data/mapList6In.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(reduceResult, expectedReduceResult));
    }

}
