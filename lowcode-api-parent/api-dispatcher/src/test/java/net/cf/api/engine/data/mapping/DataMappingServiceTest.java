package net.cf.api.engine.data.mapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.cf.commons.test.util.FileTestUtils;
import net.cf.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/25 16:42
 */
public class DataMappingServiceTest {
    private DataMappingService dataMappingService;
    private List<DataMappingHandler> dataMappingHandlers;
    @Before
    public void setUp() {
        dataMappingHandlers = new ArrayList<>();
        dataMappingService = new DataMappingService(dataMappingHandlers);
    }

    @Test
    public void getParamTypesTest() {
        String schema = FileTestUtils.loadTextFromClasspath("data/mapping/schema/getParamTypesTestJsonSchema.json");
        Map<String, String> paramTypes = dataMappingService.getParamTypes(JSON.parseObject(schema));
        Assert.assertNotNull(paramTypes);
    }

    @Test
    public void convertTest() {
        String schema = FileTestUtils.loadTextFromClasspath("data/mapping/schema/convertTestJsonSchema.json");
        Map<String, Object> map = JsonTestUtils.loadMapFromClasspath("data/mapping/input/convertTestJsonInput.json");
        JSONObject input = new JSONObject(map);
        Object convert = dataMappingService.convert(JSON.parseObject(schema), input);
        Assert.assertNotNull(convert);
    }
}