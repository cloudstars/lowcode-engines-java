package io.github.cloudstars.lowcode.commons.js;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

/**
 * 对JS函数类型的封装
 *
 * @author cloudss
 */
public class JsFunction {

    private Context context;

    /**
     * Graalvm的值
     */
    private Value function;

    public JsFunction(Context context, Value function) {
        assert (function.canExecute());
        this.context = context;
        this.function = function;
    }

    public JsFunction(Value function) {
        assert (function.canExecute());
        this.function = function;
    }

    /**
     * 带参数执行函数（只能执行一次）
     *
     * @param arguments
     * @return
     */
    public Object executeOnce(Object... arguments) {
        return this.execute(true, arguments);
    }

    /**
     * 带参数执行函数（只能执行一次）
     *
     * @param arguments
     * @return
     */
    public Object execute(Object... arguments) {
        return this.execute(false, arguments);
    }

    /**
     * 带参数执行函数
     *
     * @param executeOnce 是否只支持执行一次
     * @param arguments 执行函数时传入的参数值
     * @return
     */
    private Object execute(boolean executeOnce, Object... arguments) {
        for (int i = 0, l = arguments.length; i < l; i++) {
            // 需要进行转换后，JS代码才能识别
            arguments[i] = JsDataConverter.toJsObject(arguments[i]);
        }

        Value result = function.execute(arguments);

        if (executeOnce) {
            // 此处关闭context，如果没有调用executeOnce,可能存在内存泄露
            if (context != null) {
                context.close();
            }
        }

        return JsDataConverter.toJavaObject(result);
    }

}
