package net.cf.form.repository.sql.visitor;

import java.util.List;

public interface ParameterizedVisitor extends PrintableVisitor {

    int getReplaceCount();

    void incrementReplaceCount();

    void setOutputParameters(List<Object> parameters);

    void config(VisitorFeature feature, boolean state);

    boolean isEnabled(VisitorFeature feature);
}
