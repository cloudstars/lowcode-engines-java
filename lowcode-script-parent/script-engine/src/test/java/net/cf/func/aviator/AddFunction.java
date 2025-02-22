package net.cf.func.aviator;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * Aviator函数定义
 *
 * @author clouds
 */
public class AddFunction extends AbstractFunction {

    /**
     * 函数实现的功能
     *
     * @param env  参数
     * @param arg1 函数的第一个参数
     * @param arg2 函数的第二个参数
     * @return 返回值
     */
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        long num1 = FunctionUtils.getNumberValue(arg1, env).longValue();
        long num2 = FunctionUtils.getNumberValue(arg2, env).longValue();
        return AviatorLong.valueOf(num1 + num2);
    }

    /**
     * 注册到aviator的名字
     *
     * @return 函数名字
     */
    @Override
    public String getName() {
        return "add";
    }
}
