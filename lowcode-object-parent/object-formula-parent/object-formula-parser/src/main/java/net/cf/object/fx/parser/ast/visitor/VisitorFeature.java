package net.cf.object.fx.parser.ast.visitor;

/**
 * 访问器特性
 *
 * @author clouds
 */
public enum VisitorFeature {
    OUTPUT_U_CASE,
    OUTPUT_PRETTY_FORMAT,
    OUTPUT_PARAMETERIZED;

    public final int mask = 1 << this.ordinal();

    VisitorFeature() {
    }

    public static boolean isEnabled(int features, VisitorFeature feature) {
        return (features & feature.mask) != 0;
    }

    public static int config(int features, VisitorFeature feature, boolean state) {
        int result = features;
        if (state) {
            result |= feature.mask;
        } else {
            result &= ~feature.mask;
        }

        return result;
    }

    public static int of(VisitorFeature... features) {
        if (features == null) {
            return 0;
        } else {
            int value = 0;
            VisitorFeature[] var2 = features;
            int var3 = features.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                VisitorFeature feature = var2[var4];
                value |= feature.mask;
            }

            return value;
        }
    }
}
