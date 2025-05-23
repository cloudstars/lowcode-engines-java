package net.cf.object.engine.oql.ast;


import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class OqlInsertInto extends AbstractOqlStatementImpl implements SqlReplaceable {

    protected OqlExprObjectSource objectSource;

    protected final List<OqlExpr> fields = new ArrayList();

    protected final List<SqlInsertStatement.ValuesClause> valuesList = new ArrayList();

    protected OqlSelect query;

    public OqlInsertInto() {
    }

    public OqlExprObjectSource getObjectSource() {
        return objectSource;
    }

    public void setObjectSource(OqlExprObjectSource objectSource) {
        this.objectSource = objectSource;
        this.addChild(objectSource);
    }

    public List<OqlExpr> getFields() {
        return fields;
    }

    public void addFields(List<OqlExpr> fields) {
        for (OqlExpr field : fields) {
            this.addField(field);
        }
    }

    public void addField(OqlExpr field) {
        this.fields.add(field);
        this.addChild(field);
    }

    public void addField(int index, OqlExpr field) {
        this.fields.add(index, field);
        this.addChild(field);
    }

    public List<SqlInsertStatement.ValuesClause> getValuesList() {
        return valuesList;
    }

    public void addValues(SqlInsertStatement.ValuesClause values) {
        this.valuesList.add(values);
        this.addChild(values);
    }

    public void addValuesList(List<SqlInsertStatement.ValuesClause> valuesList) {
        this.valuesList.addAll(valuesList);
    }

    public OqlSelect getQuery() {
        return query;
    }

    public void setQuery(OqlSelect query) {
        this.query = query;
    }

    @Override
    public void accept(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.objectSource.accept(visitor);
            this.nullSafeAcceptChildren(visitor, this.fields);
            this.nullSafeAcceptChildren(visitor, this.valuesList);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList();
        children.add(this.objectSource);
        children.addAll(this.fields);
        children.addAll(this.valuesList);

        return children;
    }

    @Override
    public OqlInsertInto cloneMe() {
        OqlInsertInto x = new OqlInsertInto();
        x.setObjectSource(this.objectSource.cloneMe());
        for (OqlExpr field : this.fields) {
            x.fields.add(field.cloneMe());
        }
        for (SqlInsertStatement.ValuesClause valuesItem : this.valuesList) {
            x.valuesList.add(valuesItem.cloneMe());
        }

        return x;
    }


    @Override
    public boolean replace(SqlExpr source, SqlExpr target) {
        return false;
    }
}
