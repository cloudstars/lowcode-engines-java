package net.cf.form.repository.sql.parser;

/**
 * 表达式解析的特性
 *
 * @author clouds
 */
public enum ParserFeature {
    KEEP_COMMENTS,
    SKIP_COMMENTS,
    KEEP_SOURCE_LOCATION,
    PRINT_SQL_WHILE_PARSING_FAILED;

    public final int mask = 1 << this.ordinal();

    private ParserFeature() {
    }

    /**
     * 是否启用了某个特性
     *
     * @param features
     * @param feature
     * @return
     */
    public static boolean isEnabled(int features, ParserFeature feature) {
        return (features & feature.mask) != 0;
    }

    /**
     * 启用某个特性
     *
     * @param features
     * @param feature
     * @return
     */
    public static int enable(int features, ParserFeature feature) {
        return features | feature.mask;
    }

    /**
     * 禁用某个特性
     *
     * @param features
     * @param feature
     * @return
     */
    public static int disable(int features, ParserFeature feature) {
        return features & ~feature.mask;
    }

    /**
     * 计算多个特性组成的特征码
     *
     * @param features
     * @return
     */
    public static int of(ParserFeature... features) {
        if (features == null) {
            return 0;
        } else {
            int value = 0;
            for (int i = 0; i < features.length; i++) {
                ParserFeature feature = features[i];
                value |= feature.mask;
            }

            return value;
        }
    }
}
