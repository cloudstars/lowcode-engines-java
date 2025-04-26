package net.cf.formula.engine;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;
import net.clouds.lowcode.formula.TestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class FormulaEngineTest {

    @Resource
    private FormulaEngine engine;

    @Test
    public void testJuel() throws Exception {
        // ExpressionFactory类的实现是de.odysseus.el.ExpressionFactoryImpl
        ExpressionFactory factory = new ExpressionFactoryImpl();

        // de.odysseus.el.util provides包提供即时可用的子类ELContext
        //创建上下文对象context
        SimpleContext context = new SimpleContext();

        // 函数的前缀 函数的名称 ，执行的方法 三个参数的含义
        context.setFunction("fx", "min", Math.class.getMethod("min", int.class, int.class));

        // foo值为3
        context.setVariable("foo", factory.createValueExpression(3, Object.class));

        // bar值为2
        context.setVariable("bar", factory.createValueExpression(2, Object.class));

        // 解析表达式
        ValueExpression e = factory.createValueExpression(context, "${fx:min(foo, bar)}", int.class);

        // get value for our expression
        Assert.assertTrue(e.getValue(context).equals(2));
    }

    @Test
    public void test1() {
        String expr = "a + b + 3";
        Object result = engine.getValue(expr);
        Assert.assertTrue("5".equals(result.toString()));
    }

    @Test
    public void test2() {
        String expr = "a + o.a.b + 3";
        Object result = engine.getValue(expr);
        Assert.assertTrue("6".equals(result.toString()));
    }

    @Test
    public void test3() {
        String expr = "now()";
        Object result = engine.getValue(expr);
        Assert.assertTrue(System.currentTimeMillis() - (Long) result < 1000);
    }

    @Test
    public void test4() {
        String expr = "max(-1, 3)";
        Object result = engine.getValue(expr);
        Assert.assertTrue((Long) result == 3);
    }


    @Test
    public void test5() {
        String expr = "max(a, b.a)";
        Object result = engine.getValue(expr);
        Assert.assertTrue((Integer) result == 2);
    }
}
