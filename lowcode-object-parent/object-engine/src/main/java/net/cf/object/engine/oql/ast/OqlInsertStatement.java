package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class OqlInsertStatement extends OqlInsertInto implements OqlStatement {

    public OqlInsertStatement(OqlInsertInto insertInto) {
        this.objectSource = insertInto.objectSource;
        super.addFields(insertInto.fields);
        super.addValuesList(insertInto.valuesList);
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
}
