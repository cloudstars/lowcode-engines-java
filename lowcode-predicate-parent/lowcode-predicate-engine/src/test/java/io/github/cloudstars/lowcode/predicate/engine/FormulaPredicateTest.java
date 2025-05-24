package io.github.cloudstars.lowcode.predicate.engine;

import io.github.cloudstars.lowcode.PredicateEngineTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.predicate.type.FormulaPredicateConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PredicateEngineTestApplication.class)
public class FormulaPredicateTest {

    @Test
    public void test() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("formula/f1.json");
        FormulaPredicateConfig config = new FormulaPredicateConfig(configJson);
        FormulaPredicate predicate = new FormulaPredicate(config);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("a", 10);
        dataMap.put("b", 20);
        dataMap.put("c", 30);
        predicate.test(dataMap);
    }

}
