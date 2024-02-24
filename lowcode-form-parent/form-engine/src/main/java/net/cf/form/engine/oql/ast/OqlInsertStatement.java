package net.cf.form.engine.oql.ast;

import net.cf.form.engine.oql.visitor.OqlAstVisitor;
import net.cf.form.repository.sql.ast.SqlObject;

import java.util.ArrayList;
import java.util.List;

public class OqlInsertStatement extends OqlInsertInto implements OqlStatement {

    public OqlInsertStatement(OqlInsertInto insertInto) {
        this.objectSource = insertInto.objectSource;
        super.addFields(insertInto.fields);
        super.addValuesList(insertInto.valuesList);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.objectSource.accept(visitor);
            this.nullSafeAcceptChild(visitor, this.fields);
            this.nullSafeAcceptChild(visitor, this.valuesList);
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
}
