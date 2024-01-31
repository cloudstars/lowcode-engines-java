package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.OqlObject;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.Arrays;
import java.util.List;

public class OqlInsertStatement extends OqlStatementImpl implements OqlStatement {

    private final OqlInsertInto insertInto;

    public OqlInsertStatement(OqlInsertInto insertInto) {
        this.insertInto = insertInto;
        this.addChild(insertInto);
    }

    public OqlInsertInto getInsertInto() {
        return insertInto;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.acceptChild(visitor, this.insertInto);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<OqlObject> getChildren() {
        return Arrays.asList(this.insertInto);
    }
}
