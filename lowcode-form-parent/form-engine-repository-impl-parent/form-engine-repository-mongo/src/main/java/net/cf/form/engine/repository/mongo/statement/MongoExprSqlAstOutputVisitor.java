package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.oql.visitor.OqlAstPrintableVisitorAdaptor;

public abstract class MongoExprSqlAstOutputVisitor extends OqlAstPrintableVisitorAdaptor {

    /**
     * 对象解析器
     */
    protected DataObjectResolver resolver;

    /**
     * 当前查询的对象
     */
    protected DataObject resolvedDataObject;

    public MongoExprSqlAstOutputVisitor(DataObjectResolver resolver) {
        super(new StringBuilder());
        this.resolver = resolver;
    }

    public DataObject getResolvedDataObject( ) {
        return this.resolvedDataObject;
    }
}
