package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.object.engine.object.XObject;


/**
 * 抽象的SQL指令，用于一次 SQL 执行
 *
 * @param <S>
 */
public abstract class AbstractSqlCmd<S extends SqlStatement> {

    /**
     * 解析后的本表模型
     */
    protected XObject resolvedObject;

    /**
     * 解析后的SQL语句
     */
    protected S statement;

    /**
     * 是否批量指令
     */
    private boolean isBatch;

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

    public boolean isBatch() {
        return isBatch;
    }

    public void setBatch(boolean batch) {
        isBatch = batch;
    }
}
