package net.cf.form.engine.oql.visitor;

/**
 * 访问器特性
 *
 * @author clouds
 */
public enum OqlAstVisitorFeature {
    OutputUCase,
    OutputPrettyFormat,
    OutputParameterized;

    public final int mask = 1 << this.ordinal();

    private OqlAstVisitorFeature() {
    }

    public static boolean isEnabled(int features, OqlAstVisitorFeature feature) {
        return (features & feature.mask) != 0;
    }

    public static int config(int features, OqlAstVisitorFeature feature, boolean state) {
        if (state) {
            features |= feature.mask;
        } else {
            features &= ~feature.mask;
        }

        return features;
    }

    public static int of(OqlAstVisitorFeature... features) {
        if (features == null) {
            return 0;
        } else {
            int value = 0;
            OqlAstVisitorFeature[] var2 = features;
            int var3 = features.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                OqlAstVisitorFeature feature = var2[var4];
                value |= feature.mask;
            }

            return value;
        }
    }
}
