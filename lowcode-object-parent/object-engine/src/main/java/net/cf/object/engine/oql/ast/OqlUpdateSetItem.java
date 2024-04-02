package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.oql.AbstractOqlObjectImpl;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * OQL更新Set字段
 * <p>
 * 区别于OqlUpdateItem，OQL更新Set字段中的expr可能包含OQL相关的expr，但是SqlUpdateSetItem中不会包含OqlXxxExpr
 *
 */
public class OqlUpdateSetItem extends AbstractOqlObjectImpl {

    private OqlExpr field;

    private SqlExpr value;

    public OqlUpdateSetItem() {
    }

    public OqlUpdateSetItem(OqlExpr field, SqlExpr value) {
        this.field = field;
        this.value = value;
    }

    public OqlExpr getField() {
        return field;
    }

    public void setField(OqlExpr field) {
        this.field = field;
        this.addChild(field);
    }

    public SqlExpr getValue() {
        return this.value;
    }

    public void setValue(SqlExpr value) {
        this.value = value;
        this.addChild(value);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChildren(visitor, this.field);
            this.nullSafeAcceptChildren(visitor, this.value);
        }

        visitor.endVisit(this);
    }

    @Override
    public OqlUpdateSetItem cloneMe() {
        OqlUpdateSetItem x = new OqlUpdateSetItem();
        if (this.field != null) {
            x.setField(this.field.cloneMe());
        }

        if (this.value != null) {
            x.setValue(this.value.cloneMe());
        }

        return x;
    }

}
