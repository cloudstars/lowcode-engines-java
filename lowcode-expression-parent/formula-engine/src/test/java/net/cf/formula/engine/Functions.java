package net.cf.formula.engine;

/**
 * 测试用的函数
 *
 * @author clouds
 */
public class Functions {

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long now() {
        return System.currentTimeMillis();
    }


    /**
     * 求两个数的最大值
     *
     * @param n1
     * @param n2
     * @return
     */
    public static Object max(Number n1, Number n2) {
        if (n1 instanceof Integer) {
            if (n2 instanceof Integer) {
                return (Integer) n1 > (Integer) n2 ? n1 : n2;
            }  else if (n2 instanceof Long) {
                return (Integer) n1 > (Long) n2 ? n1 : n2;
            } else if (n2 instanceof Float) {
                return (Integer) n1 > (Float) n2 ? n1 : n2;
            } else if (n2 instanceof Double) {
                return (Integer) n1 > (Double) n2 ? n1 : n2;
            }
        } else if (n1 instanceof Long) {
            if (n2 instanceof Integer) {
                return (Long) n1 > (Integer) n2 ? n1 : n2;
            }  else if (n2 instanceof Long) {
                return (Long) n1 > (Long) n2 ? n1 : n2;
            } else if (n2 instanceof Float) {
                return (Long) n1 > (Float) n2 ? n1 : n2;
            } else if (n2 instanceof Double) {
                return (Long) n1 > (Double) n2 ? n1 : n2;
            }
        } else if (n1 instanceof Float) {
            if (n2 instanceof Integer) {
                return (Float) n1 > (Integer) n2 ? n1 : n2;
            }  else if (n2 instanceof Long) {
                return (Float) n1 > (Long) n2 ? n1 : n2;
            } else if (n2 instanceof Float) {
                return (Float) n1 > (Float) n2 ? n1 : n2;
            } else if (n2 instanceof Double) {
                return (Float) n1 > (Double) n2 ? n1 : n2;
            }
        } else if (n1 instanceof Double) {
            if (n2 instanceof Integer) {
                return (Double) n1 > (Integer) n2 ? n1 : n2;
            }  else if (n2 instanceof Long) {
                return (Double) n1 > (Long) n2 ? n1 : n2;
            } else if (n2 instanceof Float) {
                return (Double) n1 > (Float) n2 ? n1 : n2;
            } else if (n2 instanceof Double) {
                return (Double) n1 > (Double) n2 ? n1 : n2;
            }
        }

        throw new FastFxException("无法比较两个数：" + n1.toString() + ", " + n2.toString());
    }
}
