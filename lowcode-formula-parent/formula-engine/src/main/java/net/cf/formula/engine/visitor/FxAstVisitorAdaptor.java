package net.cf.formula.engine.visitor;

/**
 * AST访问适配器
 *
 * @author clouds
 */
public class FxAstVisitorAdaptor implements FxAstVisitor {
    protected int features;

    public FxAstVisitorAdaptor() {
    }

    public final boolean isEnabled(VisitorFeature feature) {
        return VisitorFeature.isEnabled(this.features, feature);
    }

    public void config(VisitorFeature feature, boolean state) {
        this.features = VisitorFeature.config(this.features, feature, state);
    }

    public int getFeatures() {
        return this.features;
    }

    public void setFeatures(int features) {
        this.features = features;
    }
}

