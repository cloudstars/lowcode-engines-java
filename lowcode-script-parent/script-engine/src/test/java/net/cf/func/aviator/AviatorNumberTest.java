package net.cf.func.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import net.cf.commons.test.util.FileTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.DataOutput;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 测试数字的能力
 *
 * 案例来源：https://zhuanlan.zhihu.com/p/673299668
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class AviatorNumberTest {

    /**
     * 测试大整数
     */
    @Test
    public void test1() {
        String script = FileTestUtils.loadTextFromClasspath("script/bigint.av");
        Expression exp = AviatorEvaluator.getInstance().compile(script, true);
        Object result = exp.execute();
        assert (result instanceof BigInteger && "10223372036854776807".equals(result.toString()));
    }

    /**
     * 测试浮点数
     *
     * 数字除了整数之外，AviatorScript 同样支持浮点数，但是仅支持 double 类型，也就是双精度 64 位，符合 IEEE754 规范的浮点数。
     * 传入的 java float 也将转换为 double 类型。所有的浮点数都被认为是 double 类型。浮点数的表示形式有两种：
     *
     * 十进制的带小数点的数字，比如 1.34159265 ， 0.33333 等等。
     * 科学计数法表示，如 1e-2 ， 2E3 等等，大小写字母 e 皆可。
     */
    @Test
    public void test2() {
        String script = FileTestUtils.loadTextFromClasspath("script/double.av");
        Expression exp = AviatorEvaluator.getInstance().compile(script, true);
        Object result = exp.execute();
        assert (result instanceof DataOutput && "1.414213562373095".equals(result.toString()));
    }

    /**
     * 测试高精度
     *
     * 浮点数是无法用于需要精确运算的场景，比如货币运算或者物理公式运算等，
     * 这种情况下如果在 Java 里一般推荐使用 BigDecimal 类型，调用它的 add/sub 等方法来做算术运算。
     *
     * AviatorScript 将 BigDecimal 作为基本类型来支持（下文简称 decimal.av 类型），
     * 只要浮点数以 M 结尾就会识别类型为 deicmal，例如 1.34M 、 0.333M 或者科学计数法 2e-3M 等等。
     *
     */
    @Test
    public void test3() {
        String script = FileTestUtils.loadTextFromClasspath("script/decimal.av");
        Expression exp = AviatorEvaluator.getInstance().compile(script, true);
        Object result = exp.execute();
        assert (result instanceof BigDecimal && "1.41421356237309504880168962350253".equals(result.toString()));
    }

    /**
     * 数字类型在运算的时候，会遵循一定的类型转换规则：
     *
     * 单一类型参与的运算，结果仍然为该类型，比如整数和整数相除仍然是整数，double 和 double 运算结果还是 double。
     * 多种类型参与的运算，按照下列顺序： long -> bigint -> decimal -> double 自动提升，
     * 比如 long 和 bigint 运算结果为 bigint， long 和 decimal 运算结果为 decimal，
     * 任何类型和 double 一起运算结果为 double
     *
     */
    @Test
    public void testDataConvert() {
        String script = FileTestUtils.loadTextFromClasspath("script/dataConvert.av");
        Expression exp = AviatorEvaluator.getInstance().compile(script, true);
        Object result = exp.execute();
        assert (result instanceof Double && ((Double) result) == 0.5);
    }

}
