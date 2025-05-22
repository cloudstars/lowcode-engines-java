package io.github.cloudstars.lowcode.commons.lang.util;

/**
 * 计算工具
 *
 * @author clouds
 */
public final class CalculateUtils {

    private CalculateUtils() {
    }

    /**
     * 解析数字
     *
     * @param text 文本
     * @return 数字
     */
    public static Number parseNumber(String text) {
        if (text == null) {
            return null;
        }

        if (text.indexOf(".") >= 0) {
            return Double.parseDouble(text);
        } else {
            return Integer.parseInt(text);
        }
    }

    /**
     * 两数相加
     *
     * @param v1 数1
     * @param v2 数2
     * @return 相加结果
     */
    public static Object add(Object v1, Object v2) {
        if (v1 == null) {
            return v2 == null ? 0 : v2;
        } else if (v2 == null) {
            return v1;
        } else if (v1 instanceof Integer) {
            if (v2 instanceof Integer) {
                return (Integer) v1 + (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Integer) v1 + (Float) v2;
            } else if (v2 instanceof Double) {
                return (Integer) v1 + (Double) v2;
            }
        } else if (v1 instanceof Float) {
            if (v2 instanceof Integer) {
                return (Float) v1 + (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Float) v1 + (Float) v2;
            } else if (v2 instanceof Double) {
                return (Float) v1 + (Double) v2;
            }
        } else if (v1 instanceof Double) {
            if (v2 instanceof Integer) {
                return (Double) v1 + (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Double) v1 + (Float) v2;
            } else if (v2 instanceof Double) {
                return (Double) v1 + (Double) v2;
            }
        }

        return 0;
    }

}
