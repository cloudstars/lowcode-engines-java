package net.cf.func.aviator;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试一下Aviator的功能
 *
 * Aviator介绍：https://zhuanlan.zhihu.com/p/673299668
 * 案例来源：https://blog.csdn.net/weiwosuoai/article/details/132726776
 *
 * @author clouds
 */
public class AviatorExprTest {

    /**
     * 直接计算表达式的值
     */
    @Test
    public void test1() {
        Long r = (Long) AviatorEvaluator.execute("2 * (3 + 5)");
        assert (r == 16);
    }

    /**
     * 先编译后求值，以提升性能
     */
    @Test
    public void test2() {
        Expression expression = AviatorEvaluator.compile("2 * (3 + 5)");
        Long r = (Long) expression.execute();
        assert (r == 16);
    }

    /**
     * aviator表达式支持大部分的运算操作符，
     * 如常用的算术运算操作符(+、-、*、/、%)、逻辑运算操作符（&&、||、！）、
     * 比较运算操作符（>、>=、==、!=、<、<=）、
     * 位运算操作符（&、|、^、<<、>>）和优先级操作符，
     * 还支持三元操作表达(?:)、
     * 正则表达式(=~)。
     */
    @Test
    public void test3() {
        // 返回 hello world
        String r1 = (String) AviatorEvaluator.execute("'hello' + ' world'");
        assert ("hello world".equals(r1));

        // 返回 true
        Boolean r2 = (Boolean) AviatorEvaluator.execute("100 > 80 && 30 < 40");
        assert (r2);

        // 三元表达式，返回 30
        Long r3 = (Long) AviatorEvaluator.execute("100 > 80 ? 30 : 40");
        assert (r3 == 30);

        // 正则表达式，正则表达式放在//之间，返回 true
        Boolean r4 = (Boolean) AviatorEvaluator.execute("'hello' =~ /[\\w]+/");
        assert (r4);
    }

    /**
     * 支持变量表达式
     */
    @Test
    public void test4() {
        // 跟其他表达式引擎一样，aviator也是支持表达式求值时传入参数的：
        Long a = 12L;
        Boolean r1 = (Boolean) AviatorEvaluator.exec("a > 10", a);
        assert (r1);

        // 参数也可以是一个列表，如下：
        List<Long> b = new ArrayList<>();
        b.add(12L);
        b.add(20L);
        Boolean r2 = (Boolean) AviatorEvaluator.exec("b[0] > 10", b);
        assert (r2);

        // 也可以是一个对象：
        Person c = new Person("move", 25);
        Boolean r3 = (Boolean) AviatorEvaluator.exec("c.age > 10", c);
        assert (r3);

        // 也可以是一个Map:
        Map<String, Object> env = new HashMap<>();
        env.put("person", new Person("move", 25));
        env.put("a", 20L);
        Object r4 = AviatorEvaluator.execute("person.name", env);
        assert ("move".equals(r4));

        // 也可以解析Json字符串
        String jsonStr = "{\"a\": {\"b\": [{\"x\": 3}, {\"x\": 4}]}}";
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        // 结果返回 3
        Object r5 = AviatorEvaluator.execute("a.b[0]['x']", jsonObj);
        assert (r5 instanceof Integer && ((Integer) r5) == 3);
    }

    private static class Person {
        private String name;
        private Integer age;


        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

    }
}
