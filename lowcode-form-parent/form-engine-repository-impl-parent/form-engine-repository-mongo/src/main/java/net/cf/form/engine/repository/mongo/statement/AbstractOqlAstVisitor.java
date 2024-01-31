package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.DataObjectResolver;

public abstract class AbstractOqlAstVisitor {
    /**
     * 对象解析器
     */
    protected DataObjectResolver resolver;

    /**
     * 当前查询的对象
     */
    protected DataObject resolvedDataObject;

    public AbstractOqlAstVisitor(DataObjectResolver resolver) {
        this.resolver = resolver;
    }

    public DataObject getResolvedDataObject( ) {
        return this.resolvedDataObject;
    }
}
