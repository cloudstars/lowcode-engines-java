package net.cf.object.engine.oql.method;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlMethodInvokeExpr;
import net.cf.object.engine.oql.FastOqlException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class OqlMethodUtils {

    private static final Map<String, OqlMethodDescriptor> methods = new HashMap<>();

    static {
        OqlMethodDescriptor now = new OqlMethodDescriptor();
        now.setName("NOW");
        now.setDescription("获取当前时间戳");

        OqlMethodDescriptor concat = new OqlMethodDescriptor();
        now.setName("CONCAT");
        now.setDescription("字符串拼接");
    }


    private OqlMethodUtils() {
    }

    /**
     * 判断方法调用是否合法
     *
     * @param invokeExpr
     * @return
     */
    public void checkValid(SqlMethodInvokeExpr invokeExpr) {
        String methodName = invokeExpr.getMethodName();
        String ucMethodName = methodName.toUpperCase();
        if (!methods.containsKey(ucMethodName)) {
            throw new FastOqlException("方法" + methodName + "不存在!");
        }

        List<SqlExpr> invokeArgs = invokeExpr.getArguments();
        OqlMethodDescriptor method = methods.get(ucMethodName);
        List<OqlMethodDescriptor.Argument> methodArgs = method.getArguments();
        int invokeArgsSize = invokeArgs != null ? invokeArgs.size() : 0;
        int methodArgsSize = methodArgs != null ? methodArgs.size() : 0;
        if (invokeArgsSize > methodArgsSize) {
            throw new FastOqlException("方法" + methodName + "的参数个数多于方法定义的个数!");
        }

        for (int i = 0; i < methodArgsSize; i++) {
            OqlMethodDescriptor.Argument methodArg = methodArgs.get(i);
            if (!methodArg.optional && i >= invokeArgsSize) {
                throw new FastOqlException("方法" + methodName + "的第" + (i + 1) + "个参数不能省略!");
            }
        }
    }
}
