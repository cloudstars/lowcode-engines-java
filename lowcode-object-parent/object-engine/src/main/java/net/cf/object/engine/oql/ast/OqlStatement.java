package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.statement.SqlStatement;

/**
 * OQL 语句
 *
 * @author clouds
 */
public interface OqlStatement extends SqlStatement {

    /**
     * 克隆自已
     *
     * @return
     */
    @Override
    OqlStatement cloneMe();

}
