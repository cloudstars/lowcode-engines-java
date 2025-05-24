package io.github.cloudstars.lowcode.commons.config;

import io.github.cloudstars.lowcode.commons.CommonsConfigTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsConfigTestApplication.class)
class ConfigUtilsTest {

    @Test
    public void get() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("config1.json");
        // 获取字符串类型
        Object result1 = ConfigUtils.get(configJson, "name");
        JsonTestUtils.assertEquals("简单字段类型的表单", result1);
        // 获取列表类型
        Object result2 = ConfigUtils.get(configJson, "fields");
        JsonTestUtils.assertEquals(configJson.get("fields"), result2);
        // 获取对象类型
        Object result3 = ConfigUtils.get(configJson, "props");
        JsonTestUtils.assertEquals(configJson.get("props"), result3);
        // 获取布尔类型
        Object result4 = ConfigUtils.get(configJson, "isDetail");
        JsonTestUtils.assertEquals(configJson.get("isDetail"), result4);
        // 获取数字类型
        Object result5 = ConfigUtils.get(configJson, "number");
        JsonTestUtils.assertEquals(configJson.get("number"), result5);
        // 获取空值
        Object result6 = ConfigUtils.get(configJson, "111");
        Assert.assertNull(result6);
    }

    @Test
    public void getString() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("config1.json");
        // 获取字符串类型
        String result1 = ConfigUtils.getString(configJson, "name");
        Assert.assertEquals("简单字段类型的表单", result1);
        // 获取列表类型
        Assert.assertThrows(ClassCastException.class, () -> {
            ConfigUtils.getString(configJson, "fields");
        });
        // 获取对象类型
        Assert.assertThrows(ClassCastException.class, () -> {
            ConfigUtils.getString(configJson, "props");
        });
        // 获取布尔类型
        Assert.assertThrows(ClassCastException.class, () -> {
            ConfigUtils.getString(configJson, "isDetail");
        });
        // 获取数字类型
        Assert.assertThrows(ClassCastException.class, () -> {
            ConfigUtils.getString(configJson, "number");
        });
        // 获取空值
        String result2 = ConfigUtils.getString(configJson, "111");
        Assert.assertNull(result2);
    }

    @Test
    public void getBoolean() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("config1.json");
        // 获取布尔类型
        Boolean result1 = ConfigUtils.getBoolean(configJson, "isDetail");
        JsonTestUtils.assertEquals(configJson.get("isDetail"), result1);
        // 获取非布尔类型，抛异常
        // 获取列表类型
        Assert.assertThrows(ClassCastException.class, () -> {
            ConfigUtils.getBoolean(configJson, "fields");
        });
        // 获取对象类型
        Assert.assertThrows(ClassCastException.class, () -> {
            ConfigUtils.getBoolean(configJson, "props");
        });
        // 获取字符串类型
        Assert.assertThrows(ClassCastException.class, () -> {
            ConfigUtils.getBoolean(configJson, XResourceConfig.ATTR_NAME);
        });
        // 获取数字类型
        Assert.assertThrows(ClassCastException.class, () -> {
            ConfigUtils.getBoolean(configJson, "number");
        });
        // 获取空值
        Boolean result2 = ConfigUtils.getBoolean(configJson, "111");
        Assert.assertNull(result2);
    }

    @Test
    public void consume() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("config2.json");
        ConfigUtils.consume(configJson, "oldList", (v) -> {
            List<Integer> list = (List<Integer>) v;
            list.add(5);
        });
        JsonTestUtils.assertEquals(configJson.get("newList"), configJson.get("oldList"));
    }

    @Test
    public void consumeIfPresent() {
        JsonObject oldConfigJson2 = JsonUtils.loadJsonObjectFromClasspath("config2.json");
        JsonObject newConfigJson2 = JsonUtils.loadJsonObjectFromClasspath("config2.json");
        // 222在config2不存在，当key不存在，不做消费
        ConfigUtils.consumeIfPresent(oldConfigJson2, "test", (v) -> {
            String s = (String) v;
            newConfigJson2.put("test2", v);
        });
        JsonTestUtils.assertEquals(newConfigJson2, oldConfigJson2);
        // 当key存在时
        JsonObject configJson3 = JsonUtils.loadJsonObjectFromClasspath("config3.json");
        JsonObject configJson5 = JsonUtils.loadJsonObjectFromClasspath("config5.json");
        ConfigUtils.consumeIfPresent(configJson3, "test", (v) -> {
            String s = (String) v;
            configJson3.put("test2", v);
        });
        JsonTestUtils.assertEquals(configJson5, configJson3);
    }

    @Test
    public void put() {
        JsonObject oldConfigJson = JsonUtils.loadJsonObjectFromClasspath("config2.json");
        JsonObject newConfigJson = JsonUtils.loadJsonObjectFromClasspath("config3.json");
        ConfigUtils.putRequired(oldConfigJson, "test", "test");
        JsonTestUtils.assertEquals(newConfigJson, oldConfigJson);
    }

    @Test
    public void putIfNotNull() {
        JsonObject oldConfigJson = JsonUtils.loadJsonObjectFromClasspath("config2.json");
        JsonObject newConfigJson = JsonUtils.loadJsonObjectFromClasspath("config2.json");
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("config3.json");

        // 如果值为空
        ConfigUtils.put(oldConfigJson, "test", null);
        JsonTestUtils.assertEquals(newConfigJson, oldConfigJson);

        // 如果值不为空
        ConfigUtils.put(oldConfigJson, "test", "test");
        Assert.assertNotEquals(newConfigJson, oldConfigJson);
        JsonTestUtils.assertEquals(configJson, oldConfigJson);
    }

    @Test
    public void putJson() {
        JsonObject oldConfigJson = JsonUtils.loadJsonObjectFromClasspath("config3.json");
        JsonObject newConfigJson = JsonUtils.loadJsonObjectFromClasspath("config4.json");
        XConfig xConfig = new XConfig() {

            @Override
            public String getDescription() {
                return "我是描述";
            }

            @Override
            public JsonObject<String, Object> toJson() {
                JsonObject<String, Object> configJson = new JsonObject<>();
                ConfigUtils.put(configJson, ATTR_DESCRIPTION, getDescription());

                return configJson;
            }
        };
        ConfigUtils.putRequiredJsonObject(oldConfigJson, "props", xConfig);
        JsonTestUtils.assertEquals(newConfigJson, oldConfigJson);
    }

    @Test
    void putJsonIfNotNull() {
        JsonObject oldConfigJson3 = JsonUtils.loadJsonObjectFromClasspath("config3.json");
        JsonObject newConfigJson3 = JsonUtils.loadJsonObjectFromClasspath("config3.json");
        // 如果值为空
        ConfigUtils.putJsonObject(oldConfigJson3, "props", null);
        JsonTestUtils.assertEquals(newConfigJson3, oldConfigJson3);
        // 如果值不为空
        JsonObject configJson4 = JsonUtils.loadJsonObjectFromClasspath("config4.json");
        XConfig xConfig = new XConfig() {

            @Override
            public String getDescription() {
                return "我是描述";
            }

            @Override
            public JsonObject<String, Object> toJson() {
                JsonObject<String, Object> configJson = new JsonObject<>();
                ConfigUtils.put(configJson, ATTR_DESCRIPTION, getDescription());

                return configJson;
            }
        };
        ConfigUtils.putJsonObject(oldConfigJson3, "props", xConfig);
        JsonTestUtils.assertEquals(configJson4, oldConfigJson3);

    }

    @Test
    public void putArray() {
    }

    @Test
    void putArrayIfNotNull() {
    }

    @Test
    void putAll() {
    }

    @Test
    void putAllIfNotNull() {
    }

    @Test
    void putAllIgnorePresent() {
    }

    @Test
    void toJsonArray() {
    }

    /*@Test toList方法已删除
    public void toList() {
        JsonObject configJson6 = JsonUtils.loadJsonObjectFromClasspath("config6.json");
        JsonArray array6 = (JsonArray) configJson6.get("array");
        // 不包含参数为JsonObject的构造函数，抛异常
        Assert.assertThrows("类：io.github.cloudstars.lowcode.commons.config.XConfig必须包含参数为JsonObject的构造函数", RuntimeException.class, () -> ConfigUtils.toList(array6, XConfig.class));
        // 类转换失败的，抛异常
        Assert.assertThrows("java.lang.InstantiationException", RuntimeException.class, () -> ConfigUtils.toList(array6, AbstractConfig.class));
        // 成功转换
        JsonObject configJson7 = JsonUtils.loadJsonObjectFromClasspath("config7.json");
        JsonArray array7 = (JsonArray) configJson7.get("array");
        List<ConfigTest> result = ConfigUtils.toList(array7, ConfigTest.class);
        List<ConfigTest> expect = new ArrayList<>();
        ConfigTest configTest = new ConfigTest();
        configTest.setDescription("我是描述");
        expect.add(configTest);
        JsonTestUtils.assertEquals(expect, result);
    }*/

    @Test
    void fromJsonArray() {
    }
}