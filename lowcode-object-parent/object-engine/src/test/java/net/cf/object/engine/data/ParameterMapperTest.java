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
public class ParameterMapperTest {

    @Test
    public void test1() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList1In.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList1();
        ParameterMapper mapper = new DefaultParameterMapper(valueDefs);
        Map<String, Object> mapResult = mapper.mapParameter(dataMap);

        Map<String, Object> expectedMapResult = JsonTestUtils.loadMapFromClasspath("data/mapList1Out.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(mapResult, expectedMapResult));
    }

    @Test
    public void test2() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList2In.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList2();
        ParameterMapper mapper = new DefaultParameterMapper(valueDefs);
        Map<String, Object> mapResult = mapper.mapParameter(dataMap);

        Map<String, Object> expectedMapResult = JsonTestUtils.loadMapFromClasspath("data/mapList2Out.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(mapResult, expectedMapResult));
    }

    @Test
    public void test3() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList3In.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList3();
        ParameterMapper mapper = new DefaultParameterMapper(valueDefs);
        Map<String, Object> mapResult = mapper.mapParameter(dataMap);

        Map<String, Object> expectedMapResult = JsonTestUtils.loadMapFromClasspath("data/mapList3Out.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(mapResult, expectedMapResult));
    }

    @Test
    public void test4() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList4In.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList4();
        ParameterMapper mapper = new DefaultParameterMapper(valueDefs);
        Map<String, Object> mapResult = mapper.mapParameter(dataMap);

        Map<String, Object> expectedMapResult = JsonTestUtils.loadMapFromClasspath("data/mapList4Out.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(mapResult, expectedMapResult));
    }

    @Test
    public void test5() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList5In.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList5();
        ParameterMapper mapper = new DefaultParameterMapper(valueDefs);
        Map<String, Object> mapResult = mapper.mapParameter(dataMap);

        Map<String, Object> expectedMapResult = JsonTestUtils.loadMapFromClasspath("data/mapList5Out.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(mapResult, expectedMapResult));
    }

    @Test
    public void test6() {
        Map<String, Object> dataMap = JsonTestUtils.loadMapFromClasspath("data/mapList6In.json");
        List<FieldMapping> valueDefs = FieldMappingDefs.getDefList6();
        ParameterMapper mapper = new DefaultParameterMapper(valueDefs);
        Map<String, Object> mapResult = mapper.mapParameter(dataMap);

        Map<String, Object> expectedMapResult = JsonTestUtils.loadMapFromClasspath("data/mapList6Out.json");
        Assert.assertTrue(DataCompareTestUtils.equalsMap(mapResult, expectedMapResult));
    }
}
