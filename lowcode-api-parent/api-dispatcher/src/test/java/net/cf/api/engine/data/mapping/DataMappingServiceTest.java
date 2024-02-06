package net.cf.api.engine.data.mapping;

import com.alibaba.fastjson.JSON;
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
        String schema = "{\n" +
                "\t\"definitions\": {},\n" +
                "\t\"$schema\": \"http://json-schema.org/draft-07/schema#\", \n" +
                "\t\"$id\": \"https://example.com/object1646817236.json\", \n" +
                "\t\"title\": \"Root\", \n" +
                "\t\"type\": \"object\",\n" +
                "\t\"required\": [\n" +
                "\t\t\"city\",\n" +
                "\t\t\"number\",\n" +
                "\t\t\"gov\",\n" +
                "\t\t\"user\"\n" +
                "\t],\n" +
                "\t\"properties\": {\n" +
                "\t\t\"city\": {\n" +
                "\t\t\t\"$id\": \"#root/city\", \n" +
                "\t\t\t\"title\": \"City\", \n" +
                "\t\t\t\"type\": \"string\",\n" +
                "\t\t\t\"default\": \"\",\n" +
                "\t\t\t\"minLength\": 1,\n" +
                "            \"maxLength\": 40\n" +
                "\t\t},\n" +
                "\t\t\"number\": {\n" +
                "\t\t\t\"$id\": \"#root/number\", \n" +
                "\t\t\t\"title\": \"Number\", \n" +
                "\t\t\t\"type\": \"integer\",\n" +
                "\t\t\t\"minLength\": 1,\n" +
                "            \"maxLength\": 6,\n" +
                "\t\t\t\"default\": 0\n" +
                "\t\t},\n" +
                "\t\t\"gov\": {\n" +
                "\t\t\t\"$id\": \"#root/gov\", \n" +
                "\t\t\t\"title\": \"Gov\", \n" +
                "\t\t\t\"type\": \"object\",\n" +
                "\t\t\t\"required\": [\n" +
                "\t\t\t\t\"adrress\",\n" +
                "\t\t\t\t\"personnum\"\n" +
                "\t\t\t],\n" +
                "\t\t\t\"properties\": {\n" +
                "\t\t\t\t\"adrress\": {\n" +
                "\t\t\t\t\t\"$id\": \"#root/gov/adrress\", \n" +
                "\t\t\t\t\t\"title\": \"Adrress\", \n" +
                "\t\t\t\t\t\"type\": \"string\",\n" +
                "\t\t\t\t\t\"default\": \"\",\n" +
                "\t\t\t        \"minLength\": 1,\n" +
                "                    \"maxLength\": 40\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"personnum\": {\n" +
                "\t\t\t\t\t\"$id\": \"#root/gov/personnum\", \n" +
                "\t\t\t\t\t\"title\": \"Personnum\", \n" +
                "\t\t\t\t\t\"type\": \"integer\",\n" +
                "\t\t\t        \"minLength\": 1,\n" +
                "                    \"maxLength\": 8,\n" +
                "\t\t\t\t\t\"default\": 0\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"user\": {\n" +
                "\t\t\t\"$id\": \"#root/user\", \n" +
                "\t\t\t\"title\": \"User\", \n" +
                "\t\t\t\"type\": \"array\",\n" +
                "\t\t\t\"default\": [],\n" +
                "\t\t\t\"items\":{\n" +
                "\t\t\t\t\"$id\": \"#root/user/items\", \n" +
                "\t\t\t\t\"title\": \"Items\", \n" +
                "\t\t\t\t\"type\": \"object\",\n" +
                "\t\t\t\t\"required\": [\n" +
                "\t\t\t\t\t\"name\",\n" +
                "\t\t\t\t\t\"age\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"properties\": {\n" +
                "\t\t\t\t\t\"name\": {\n" +
                "\t\t\t\t\t\t\"$id\": \"#root/user/items/name\", \n" +
                "\t\t\t\t\t\t\"title\": \"Name\", \n" +
                "\t\t\t\t\t\t\"type\": \"string\",\n" +
                "\t\t\t\t\t\t\"default\": \"\",\n" +
                "\t\t\t            \"minLength\": 1,\n" +
                "                        \"maxLength\": 40\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"age\": {\n" +
                "\t\t\t\t\t\t\"$id\": \"#root/user/items/age\", \n" +
                "\t\t\t\t\t\t\"title\": \"Age\", \n" +
                "\t\t\t\t\t\t\"type\": \"integer\",\n" +
                "\t\t\t            \"minLength\": 1,\n" +
                "                        \"maxLength\": 4,\n" +
                "\t\t\t\t\t\t\"default\": 0\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                " \n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";

        Map<String, String> paramTypes = dataMappingService.getParamTypes(JSON.parseObject(schema));
        paramTypes.forEach((k, v) -> {
            System.out.println(k + "-> " + v);
        });
        Assert.assertNotNull(paramTypes);
    }

    @Test
    public void convertTest() {
        String schema = "{\n" +
                "\t\"definitions\": {},\n" +
                "\t\"$schema\": \"http://json-schema.org/draft-07/schema#\", \n" +
                "\t\"$id\": \"https://example.com/object1646817236.json\", \n" +
                "\t\"title\": \"Root\", \n" +
                "\t\"type\": \"object\",\n" +
                "\t\"required\": [\n" +
                "\t\t\"city\",\n" +
                "\t\t\"number\",\n" +
                "\t\t\"gov\",\n" +
                "\t\t\"user\"\n" +
                "\t],\n" +
                "\t\"properties\": {\n" +
                "\t\t\"city\": {\n" +
                "\t\t\t\"$id\": \"#root/city\", \n" +
                "\t\t\t\"title\": \"City\", \n" +
                "\t\t\t\"type\": \"string\",\n" +
                "\t\t\t\"default\": \"\",\n" +
                "\t\t\t\"minLength\": 1,\n" +
                "            \"maxLength\": 40\n" +
                "\t\t},\n" +
                "\t\t\"number\": {\n" +
                "\t\t\t\"$id\": \"#root/number\", \n" +
                "\t\t\t\"title\": \"Number\", \n" +
                "\t\t\t\"type\": \"integer\",\n" +
                "\t\t\t\"minLength\": 1,\n" +
                "            \"maxLength\": 6,\n" +
                "\t\t\t\"default\": 0\n" +
                "\t\t},\n" +
                "\t\t\"gov\": {\n" +
                "\t\t\t\"$id\": \"#root/gov\", \n" +
                "\t\t\t\"title\": \"Gov\", \n" +
                "\t\t\t\"type\": \"object\",\n" +
                "\t\t\t\"required\": [\n" +
                "\t\t\t\t\"adrress\",\n" +
                "\t\t\t\t\"personnum\"\n" +
                "\t\t\t],\n" +
                "\t\t\t\"properties\": {\n" +
                "\t\t\t\t\"adrress\": {\n" +
                "\t\t\t\t\t\"$id\": \"#root/gov/adrress\", \n" +
                "\t\t\t\t\t\"title\": \"Adrress\", \n" +
                "\t\t\t\t\t\"type\": \"string\",\n" +
                "\t\t\t\t\t\"default\": \"\",\n" +
                "\t\t\t        \"minLength\": 1,\n" +
                "                    \"maxLength\": 40\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"personnum\": {\n" +
                "\t\t\t\t\t\"$id\": \"#root/gov/personnum\", \n" +
                "\t\t\t\t\t\"title\": \"Personnum\", \n" +
                "\t\t\t\t\t\"type\": \"integer\",\n" +
                "\t\t\t        \"minLength\": 1,\n" +
                "                    \"maxLength\": 8,\n" +
                "\t\t\t\t\t\"default\": 0\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"user\": {\n" +
                "\t\t\t\"$id\": \"#root/user\", \n" +
                "\t\t\t\"title\": \"User\", \n" +
                "\t\t\t\"type\": \"array\",\n" +
                "\t\t\t\"default\": [],\n" +
                "\t\t\t\"items\":{\n" +
                "\t\t\t\t\"$id\": \"#root/user/items\", \n" +
                "\t\t\t\t\"title\": \"Items\", \n" +
                "\t\t\t\t\"type\": \"object\",\n" +
                "\t\t\t\t\"required\": [\n" +
                "\t\t\t\t\t\"name\",\n" +
                "\t\t\t\t\t\"age\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"properties\": {\n" +
                "\t\t\t\t\t\"name\": {\n" +
                "\t\t\t\t\t\t\"$id\": \"#root/user/items/name\", \n" +
                "\t\t\t\t\t\t\"title\": \"Name\", \n" +
                "\t\t\t\t\t\t\"type\": \"string\",\n" +
                "\t\t\t\t\t\t\"default\": \"\",\n" +
                "\t\t\t            \"minLength\": 1,\n" +
                "                        \"maxLength\": 40\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"age\": {\n" +
                "\t\t\t\t\t\t\"$id\": \"#root/user/items/age\", \n" +
                "\t\t\t\t\t\t\"title\": \"Age\", \n" +
                "\t\t\t\t\t\t\"type\": \"integer\",\n" +
                "\t\t\t            \"minLength\": 1,\n" +
                "                        \"maxLength\": 4,\n" +
                "\t\t\t\t\t\t\"default\": 0\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                " \n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        String input = "{\n" +
                "\t\"city\":\"chicago\",\n" +
                "\t\"number\":20,\n" +
                "\t\"gov\":{\n" +
                "\t\t\"adrress\":\"chicago\",\n" +
                "\t\t\"personnum\":10\n" +
                "\t},\n" +
                "\t\"user\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"name\":\"Alex\",\n" +
                "\t\t\t\"age\":20\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        Object convert = dataMappingService.convert(JSON.parseObject(schema), JSON.parse(input));
        Assert.assertNotNull(convert);
    }
}