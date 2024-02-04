package net.cf.form.repository.sql.parser;


public enum SqlParserFeature {
    OptimizedForParameterized,
    KeepComments,
    SkipComments,
    KeepSourceLocation;

    public final int mask = 1 << this.ordinal();

    private SqlParserFeature() {
    }

    public static boolean isEnabled(int features, SqlParserFeature feature) {
        return (features & feature.mask) != 0;
    }

    public static int config(int features, SqlParserFeature feature, boolean state) {
        if (state) {
            features |= feature.mask;
        } else {
            features &= ~feature.mask;
        }

        return features;
    }

    public static int of(SqlParserFeature... features) {
        if (features == null) {
            return 0;
        } else {
            int value = 0;
            SqlParserFeature[] var2 = features;
            int var3 = features.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                SqlParserFeature feature = var2[var4];
                value |= feature.mask;
            }

            return value;
        }
    }
}

