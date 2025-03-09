package net.cf.func.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试Aviator脚本能力
 * aviator已经升级为一个脚本语言，所以不仅仅能进行表达式求值，还可以执行脚本程序。
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class AviatorScriptTest {

    /**
     * 测试脚本的直接执行
     */
    @Test
    public void test1() {
        // 返回1
        Object r = AviatorEvaluator.execute("if (true) { return 1; } else { return 2; }");
        assert (r instanceof Long && ((Long) r) == 1);
    }

    /**
     * 测试执行外部加载的脚本文件 aviatorScript脚本一般放到独立的脚本文件中，文件名后缀一般为.av
     */
    @Test
    public void test2() {
        Map<String, Object> env = new HashMap<>();
        env.put("a", 30);
        String script = FileTestUtils.loadTextFromClasspath("script/hello.av");
        Expression exp = AviatorEvaluator.getInstance().compile(script, true);
        Object result = exp.execute(env);
        assert (result instanceof Long && ((Long) result) == 10);
    }

    /**
     * 测试lambda函数
     *
     */
    @Test
    public void testLambda() {
        Map<String, Object> env = new HashMap<>();
        env.put("a", 30);
        String script = FileTestUtils.loadTextFromClasspath("script/lambda.av");
        Expression exp = AviatorEvaluator.getInstance().compile(script, true);
        Object result = exp.execute(env);
        assert (result instanceof Long && ((Long) result) == 3);
    }

}
