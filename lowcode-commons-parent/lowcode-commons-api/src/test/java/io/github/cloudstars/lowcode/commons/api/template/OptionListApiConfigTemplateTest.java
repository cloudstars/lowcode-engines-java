package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.CommonsApiTestApplication;
import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.api.config.ApiConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsApiTestApplication.class)
public class OptionListApiConfigTemplateTest {

    @Resource
    private ApiConfigParser parser;

    @Resource
    private OptionListApiConfigTemplate template;

    @Test
    public void test1() {
        OptionsApiConfigParams params = new OptionsApiConfigParams();
        params.setLabelField("a");
        params.setValueField("b");
        params.setValueDataType(OptionsApiConfigParams.ValueDataTypeEnum.NUMBER);
        ApiConfig apiConfig = this.template.newInstance(params);

        JsonObject expectedConfigJson = JsonUtils.loadJsonObjectFromClasspath("template/option-list.json");
        ApiConfig expectedApiConfig = this.parser.fromJson(expectedConfigJson);
        JsonTestUtils.assertDerivedFrom(expectedApiConfig.toJson().toJsonString(), apiConfig.toJson().toJsonString());
    }

}
