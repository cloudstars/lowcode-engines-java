package io.github.cloudstars.lowcode.object.repository.sql.visitor;

/**
 * SQL AST访问适配器
 *
 * @author clouds
 */
public class SqlAstVisitorAdaptor implements SqlAstVisitor {

    protected int features;

    public SqlAstVisitorAdaptor() {
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

