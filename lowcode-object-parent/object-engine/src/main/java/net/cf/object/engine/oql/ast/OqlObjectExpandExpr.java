package net.cf.object.engine.oql.ast;

import net.cf.object.engine.oql.visitor.OqlAstVisitor;
import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型展开表达式（用于相关表、主表、子表的C/R/U操作）
 *
 * @author clouds
 */
public class OqlObjectExpandExpr extends AbstractSqlExprImpl {

    protected String objectCode;

    protected final List<SqlExpr> fields = new ArrayList();

    public OqlObjectExpandExpr() {
    }

    public OqlObjectExpandExpr(String objectCode) {
    }

    public OqlObjectExpandExpr(String objectCode, List<SqlExpr> fields) {
        this.objectCode = objectCode;
        this.fields.addAll(fields);
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public List<SqlExpr> getFields() {
        return fields;
    }

    public void setField(int i, SqlExpr field) {
        this.fields.add(i, field);
    }

    public void addField(SqlExpr field) {
        this.fields.add(field);
    }

    public final void accept(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (SqlExpr field : this.fields) {
                if (field != null) {
                    field.accept(visitor);
                }
            }
        }

        visitor.endVisit(this);
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        this.accept(visitor);
    }

    @Override
    public SqlExpr cloneMe() {
        return null;
    }

    @Override
    public List<SqlExpr> getChildren() {
        return null;
    }
}

