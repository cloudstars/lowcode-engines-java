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
        text = text.trim();

        if (text.indexOf(".") >= 0) {
            return Double.parseDouble(text);
        } else {
            return Integer.parseInt(text);
        }
    }

    /**
     * 求一个数的负数
     *
     * @param number 输入的数
     * @return 输入输的负数
     */
    public static Number negativeNumber(Object number) {
        if (number == null) {
            return null;
        }

        if (number instanceof Integer) {
            return -((Integer) number);
        }

        if (number instanceof Float) {
            return -((Float) number);
        }

        if (number instanceof Double) {
            return -((Double) number);
        }

        return null;
    }

    /**
     * 两数相加
     *
     * @param v1 数1
     * @param v2 数2
     * @return 相加结果
     */
    public static Object plus(Object v1, Object v2) {
        if (v1 == null) {
            v1 = 0;
        }

        if (v2 == null) {
            v2 = 0;
        }

        if (v1 instanceof Integer) {
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

        return null;
    }

    /**
     * 两数相减
     *
     * @param v1 数1
     * @param v2 数2
     * @return 相减结果
     */
    public static Object minus(Object v1, Object v2) {
        if (v1 == null) {
            v1 = 0;
        }

        if (v2 == null) {
            v2 = 0;
        }

        if (v1 instanceof Integer) {
            if (v2 instanceof Integer) {
                return (Integer) v1 - (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Integer) v1 - (Float) v2;
            } else if (v2 instanceof Double) {
                return (Integer) v1 - (Double) v2;
            }
        } else if (v1 instanceof Float) {
            if (v2 instanceof Integer) {
                return (Float) v1 - (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Float) v1 - (Float) v2;
            } else if (v2 instanceof Double) {
                return (Float) v1 - (Double) v2;
            }
        } else if (v1 instanceof Double) {
            if (v2 instanceof Integer) {
                return (Double) v1 - (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Double) v1 - (Float) v2;
            } else if (v2 instanceof Double) {
                return (Double) v1 - (Double) v2;
            }
        }

        return null;
    }


    /**
     * 两数相乘
     *
     * @param v1 数1
     * @param v2 数2
     * @return 相乘结果
     */
    public static Object multiply(Object v1, Object v2) {
        if (v1 == null) {
            v1 = 0;
        }

        if (v2 == null) {
            v2 = 0;
        }

        if (v1 instanceof Integer) {
            if (v2 instanceof Integer) {
                return (Integer) v1 * (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Integer) v1 * (Float) v2;
            } else if (v2 instanceof Double) {
                return (Integer) v1 * (Double) v2;
            }
        } else if (v1 instanceof Float) {
            if (v2 instanceof Integer) {
                return (Float) v1 * (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Float) v1 * (Float) v2;
            } else if (v2 instanceof Double) {
                return (Float) v1 * (Double) v2;
            }
        } else if (v1 instanceof Double) {
            if (v2 instanceof Integer) {
                return (Double) v1 * (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Double) v1 * (Float) v2;
            } else if (v2 instanceof Double) {
                return (Double) v1 * (Double) v2;
            }
        }

        return null;
    }

    /**
     * 两数相除
     *
     * @param v1 数1
     * @param v2 数2
     * @return 相除结果
     */
    public static Object divide(Object v1, Object v2) {
        if (v1 == null) {
            v1 = 0;
        }

        if (v2 == null) {
            v2 = 0;
        }

        if (v1 instanceof Integer) {
            if (v2 instanceof Integer) {
                return (Integer) v1 / (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Integer) v1 / (Float) v2;
            } else if (v2 instanceof Double) {
                return (Integer) v1 / (Double) v2;
            }
        } else if (v1 instanceof Float) {
            if (v2 instanceof Integer) {
                return (Float) v1 / (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Float) v1 / (Float) v2;
            } else if (v2 instanceof Double) {
                return (Float) v1 / (Double) v2;
            }
        } else if (v1 instanceof Double) {
            if (v2 instanceof Integer) {
                return (Double) v1 / (Integer) v2;
            } else if (v2 instanceof Float) {
                return (Double) v1 / (Float) v2;
            } else if (v2 instanceof Double) {
                return (Double) v1 / (Double) v2;
            }
        }

        return null;
    }

    /**
     * 两数计算（有问题，Number +- Number不允许）
     *
     * @param v1   数1
     * @param v2   数2
     * @param calc 计算方法
     * @return 计算结果
     */
    private static Object calc(Object v1, Object v2, Calculator calc) {
        if (v1 == null) {
            return v2 == null ? 0 : v2;
        } else if (v2 == null) {
            return v1;
        } else if (v1 instanceof Integer) {
            if (v2 instanceof Integer) {
                return calc.apply((Integer) v1, (Integer) v2);
            } else if (v2 instanceof Float) {
                return calc.apply((Integer) v1, (Float) v2);
            } else if (v2 instanceof Double) {
                return calc.apply((Integer) v1, (Double) v2);
            }
        } else if (v1 instanceof Float) {
            if (v2 instanceof Integer) {
                return calc.apply((Float) v1, (Integer) v2);
            } else if (v2 instanceof Float) {
                return calc.apply((Float) v1, (Float) v2);
            } else if (v2 instanceof Double) {
                return calc.apply((Float) v1, (Double) v2);
            }
        } else if (v1 instanceof Double) {
            if (v2 instanceof Integer) {
                return calc.apply((Double) v1, (Integer) v2);
            } else if (v2 instanceof Float) {
                return calc.apply((Double) v1, (Float) v2);
            } else if (v2 instanceof Double) {
                return calc.apply((Double) v1, (Double) v2);
            }
        }

        return null;
    }

    interface Calculator {
        Number apply(Number v1, Number v2);
    }

}
