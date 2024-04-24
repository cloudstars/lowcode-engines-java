package net.cf.object.engine.oqlnew.info;

import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.object.engine.object.XObject;

public abstract class AbstractOqlInfo<S extends SqlStatement> {

    /**
     * 解析后的本表模型
     */
    protected XObject resolvedObject;

    /**
     * 解析后的SQL语句
     */
    protected S statement;

    public XObject getResolvedObject() {
        return resolvedObject;
    }

    public void setResolvedObject(XObject resolvedObject) {
        this.resolvedObject = resolvedObject;
    }

    public S getStatement() {
        return statement;
    }

    public void setStatement(S statement) {
        this.statement = statement;
    }

}
