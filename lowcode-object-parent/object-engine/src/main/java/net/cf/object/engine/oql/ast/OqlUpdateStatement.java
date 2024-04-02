package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * OQL更新语句
 *
 * @author clouds
 */
public class OqlUpdateStatement extends AbstractOqlStatementImpl implements OqlStatement {

    /**
     * 更新的模型源
     */
    protected OqlExprObjectSource objectSource;

    /**
     * 更新的字段值
     */
    protected final List<OqlUpdateSetItem> setItems = new ArrayList<>();

    /**
     * 查询条件
     */
    private SqlExpr where;

    public OqlUpdateStatement() {
    }

    public OqlExprObjectSource getObjectSource() {
        return objectSource;
    }

    public void setObjectSource(OqlExprObjectSource objectSource) {
        this.objectSource = objectSource;
        this.addChild(objectSource);
    }

    public List<OqlUpdateSetItem> getSetItems() {
        return setItems;
    }

    public void addSetItems(List<OqlUpdateSetItem> setItems) {
        for (OqlUpdateSetItem setItem : setItems) {
            this.addSetItem(setItem);
        }
    }

    public void addSetItem(OqlUpdateSetItem setItem) {
        this.setItems.add(setItem);
        this.addChild(setItem);
    }

    public SqlExpr getWhere() {
        return where;
    }

    public void setWhere(SqlExpr where) {
        this.where = where;
        this.addChild(where);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.objectSource.accept(visitor);
            this.nullSafeAcceptChildren(visitor, this.setItems);
            this.where.accept(visitor);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList<>();
        children.add(this.objectSource);
        children.addAll(this.setItems);
        if (this.where != null) {
            children.add(this.where);
        }

        return children;
    }

    @Override
    public OqlUpdateStatement cloneMe() {
        OqlUpdateStatement statement = new OqlUpdateStatement();
        statement.setObjectSource(this.objectSource.cloneMe());
        for (OqlUpdateSetItem setItem : this.setItems) {
            statement.addSetItem(setItem);
        }
        if (this.where != null) {
            statement.setWhere(this.where.cloneMe());
        }

        return statement;
    }
}
