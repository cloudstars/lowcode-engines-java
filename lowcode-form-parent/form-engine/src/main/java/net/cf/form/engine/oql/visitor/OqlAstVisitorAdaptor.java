package net.cf.form.engine.oql.visitor;

/**
 * AST访问适配器
 *
 * @author clouds
 */
public class OqlAstVisitorAdaptor implements OqlAstVisitor {
    protected int features;

    public OqlAstVisitorAdaptor() {
    }

    public final boolean isEnabled(OqlAstVisitorFeature feature) {
        return OqlAstVisitorFeature.isEnabled(this.features, feature);
    }

    public void config(OqlAstVisitorFeature feature, boolean state) {
        this.features = OqlAstVisitorFeature.config(this.features, feature, state);
    }

    public int getFeatures() {
        return this.features;
    }

    public void setFeatures(int features) {
        this.features = features;
    }

}

