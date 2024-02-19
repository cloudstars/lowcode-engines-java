package net.cf.form.repository.sql.ast.expr;

import net.cf.form.repository.sql.ast.SqlCommentHint;
import net.cf.form.repository.sql.ast.SqlDataType;
import net.cf.form.repository.sql.ast.SqlObject;

import java.util.List;

/**
 *
 * SQL AST 中的表达式节点
 *
 * @author clouds
 */
public interface SqlExpr extends SqlObject {

    @Override
    SqlExpr cloneMe();

    SqlDataType computeDataType();

    List<SqlExpr> getChildren();

    SqlCommentHint getHint();
}
