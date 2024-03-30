package net.cf.func.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * 测试Aviator的函数
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class AviatorFunctionTest {

    /**
     * aviator已经提供了很多开箱即用的函数了
     */
    @Test
    public void test1() {
        // 返回4
        Long r1 = (Long) AviatorEvaluator.execute("math.round(4.3)");
        assert (r1 == 4);

        // 返回5
        Long r2 = (Long) AviatorEvaluator.execute("string.length('hello')");
        assert (r2 == 5);

        // 返回一个ArrayList：[1,2,3]
        Object r3 = AviatorEvaluator.execute("seq.list(1,2,3)");
        assert (r3 instanceof List && ((List) r3).size() == 3);
    }

    /**
     * 测试自定义函数，定义函数后注册到aviator中，像使用内置函数一样使用自定义函数
     */
    @Test
    public void test2() {
        // 注册
        AviatorEvaluator.addFunction(new AddFunction());

        // 使用
        long sum = (Long) AviatorEvaluator.getInstance().execute("add(3, 4)");
        assert (sum == 7);
    }

}
