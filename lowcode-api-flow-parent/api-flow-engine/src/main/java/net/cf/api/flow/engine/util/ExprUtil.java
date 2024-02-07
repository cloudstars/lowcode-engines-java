package net.cf.api.flow.engine.util;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/7
 */
public class ExprUtil {

    private static final String EXPRESSION_PREFIX = "{{";

    private static final String EXPRESSION_SUFFIX = "}}";

    private static final String REF_PREFIX = "${";

    private static final String REF_SUFFIX = "}";

    /**
     * 从定义的变量：{{ xxx }} 中获取 xxx
     * 例如: {{ output.list }} => output.list
     *
     * @param targetName
     * @return
     */
    public static String getExpressionByTargetName(String targetName) {
             if (targetName.startsWith(EXPRESSION_PREFIX) &&
                targetName.endsWith(EXPRESSION_SUFFIX)) {
            return targetName.substring(2, targetName.length() - 2);
        } else if (targetName.startsWith(REF_PREFIX) &&
                targetName.endsWith(REF_SUFFIX)) {
            return targetName.substring(2, targetName.length() - 1);
        } else {
            return targetName;
        }
    }
}
