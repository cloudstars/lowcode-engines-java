package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.visitor.OqlAstVisitor;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlUpdateSetItem;

import java.util.ArrayList;
import java.util.List;

public class OqlUpdateStatement extends AbstractOqlStatementImpl implements OqlStatement {

    protected OqlObjectSource objectSource;

    protected final List<SqlUpdateSetItem> setItems = new ArrayList<>();

    /**
     * 查询条件
     */
    private SqlExpr where;

    public OqlUpdateStatement() {
    }

    public OqlObjectSource getObjectSource() {
        return objectSource;
    }

    public void setObjectSource(OqlObjectSource objectSource) {
        this.objectSource = objectSource;
        this.addChild(objectSource);
    }

    public List<SqlUpdateSetItem> getSetItems() {
        return setItems;
    }

    public void addSetItems(List<SqlUpdateSetItem> setItems) {
        for (SqlUpdateSetItem setItem : setItems) {
            this.addSetItem(setItem);
        }
    }

    public void addSetItem(SqlUpdateSetItem setItem) {
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
            this.nullSafeAcceptChild(visitor, this.setItems);
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
        for (SqlUpdateSetItem setItem : this.setItems) {
            statement.addSetItem(setItem);
        }
        if (this.where != null) {
            statement.setWhere(this.where.cloneMe());
        }

        return statement;
    }
}
