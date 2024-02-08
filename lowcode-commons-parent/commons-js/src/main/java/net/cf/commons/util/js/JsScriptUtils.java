package net.cf.commons.util.js;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import javax.script.*;

/**
 * JS脚本工具
 * <p>
 * 使用时请添加graalvm的两个optional依赖
 *
 * @author clouds
 */
public final class JsScriptUtils {

    // 实始化一个引擎
    private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");

    private JsScriptUtils() {
    }

    /**
     * 直接执行代码
     *
     * @param script
     * @return
     */
    public static Object eval(String script) {
        Context context = Context.newBuilder().build();
        Value value = context.eval("js", script);
        return JsDataConverter.toJavaObject(value);
    }


    /**
     * 直接执行代码
     *
     * @param script
     * @param arguments
     * @return
     */
    public static Object execute(String script, Object... arguments) {
        Context context = Context.newBuilder().build();
        Value value = context.eval("js", script);
        assert (value.canExecute());
        JsFunction jsFunction = new JsFunction(context, value);
        return jsFunction.executeOnce(arguments);
    }
}
