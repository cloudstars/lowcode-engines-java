package net.cf.form.repository.sql.visitor;

/**
 * AST访问器特性
 *
 * @author clouds
 */
public enum VisitorFeature {
    OUTPUT_U_CASE,
    OUTPUT_PRETTY_FORMAT,
    OUTPUT_PARAMETERIZED,
    OUTPUT_NAME_QUOTE;

    public final int mask = 1 << this.ordinal();

    private VisitorFeature() {
    }

    public static boolean isEnabled(int features, VisitorFeature feature) {
        return (features & feature.mask) != 0;
    }

    public static int config(int features, VisitorFeature feature, boolean state) {
        if (state) {
            features |= feature.mask;
        } else {
            features &= ~feature.mask;
        }

        return features;
    }

    public static int of(VisitorFeature... features) {
        if (features == null) {
            return 0;
        } else {
            int value = 0;
            for (int i = 0, l = features.length; i < l; i++) {
                VisitorFeature feature = features[i];
                value |= feature.mask;
            }

            return value;
        }
    }
}
