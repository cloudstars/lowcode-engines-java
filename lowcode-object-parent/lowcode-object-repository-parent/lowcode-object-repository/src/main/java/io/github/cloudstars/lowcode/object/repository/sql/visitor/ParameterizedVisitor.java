package io.github.cloudstars.lowcode.object.repository.sql.visitor;

import java.util.List;

/**
 * 参数化AST迭代器
 *
 * @author clouds
 */
public interface ParameterizedVisitor extends PrintableVisitor {

    int getReplaceCount();

    void incrementReplaceCount();

    void setOutputParameters(List<Object> parameters);

    void config(VisitorFeature feature, boolean state);

    boolean isEnabled(VisitorFeature feature);
}
