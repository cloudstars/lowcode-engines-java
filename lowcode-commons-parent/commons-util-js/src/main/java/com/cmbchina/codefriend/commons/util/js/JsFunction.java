package com.cmbchina.codefriend.commons.util.js;

import org.graalvm.polyglot.Value;

/**
 * 对JS函数类型的封装
 *
 * @author 80274507s
 */
public class JsFunction {

    /**
     * Graalvm的值
     */
    private Value function;

    public JsFunction(Value function) {
        assert (function.canExecute());
        this.function = function;
    }

    /**
     * 带参数执行函数
     *
     * @param arguments
     * @return
     */
    public Object execute(Object... arguments) {
        for (int i = 0, l = arguments.length; i < l; i++) {
            // 需要进行转换后，JS代码才能识别
            arguments[i] = JsDataConverter.toJsObject(arguments[i]);
        }

        Value result = function.execute(arguments);
        return JsDataConverter.toJavaObject(result);
    }
}
