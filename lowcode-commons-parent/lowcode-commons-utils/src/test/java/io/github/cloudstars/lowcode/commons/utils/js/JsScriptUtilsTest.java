package io.github.cloudstars.lowcode.commons.utils.js;

import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4ClassRunner.class)
public class JsScriptUtilsTest {

    @Test
    public void testExecuteEs6() {
        String script = FileTestUtils.loadTextFromClasspath("js/es6.js");
        Object result = JsScriptUtils.eval(script);
        assert (result instanceof List);
        Assert.assertTrue(((List) result).size() == 9);
    }

    @Test
    public void testExecuteIIFE() {
        String script = FileTestUtils.loadTextFromClasspath("js/iife.js");
        Object result = JsScriptUtils.eval(script);
        assert (result != null && result instanceof Map);
        Map<String, Object> mapResult = (Map) result;
        assert (mapResult.size() == 2);
        assert ("a".equals(mapResult.get("a")));
        Object mapResultPropB = mapResult.get("b");
        assert (mapResultPropB instanceof JsFunction);
        JsFunction functionResultPropB = (JsFunction) mapResultPropB;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("b", "b");
        Object functionBResult = functionResultPropB.executeOnce(paramMap);
        assert ("b".equals(functionBResult));
    }

    @Test
    public void testF() {
        String script = FileTestUtils.loadTextFromClasspath("js/f.js");
        Object result = JsScriptUtils.eval(script);
        Assert.assertTrue(result != null && result instanceof JsFunction);
        JsFunction function = (JsFunction) result;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("redundant", true);
        Object functionResult = function.executeOnce(paramMap);
        Assert.assertTrue(functionResult != null && functionResult instanceof List && ((List<?>) functionResult).size() == 2);
    }
}
