package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.statement.SqlTableSource;

/**
 * OQL 模型源
 *
 * @author clouds
 */
public interface OqlObjectSource extends SqlTableSource {

    /**
     * 克隆自已
     *
     * @return
     */
    @Override
    OqlExprObjectSource cloneMe();

}
