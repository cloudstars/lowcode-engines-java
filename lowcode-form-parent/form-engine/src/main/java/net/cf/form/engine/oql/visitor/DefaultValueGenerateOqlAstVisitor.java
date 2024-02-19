package net.cf.form.engine.oql.visitor;

import net.cf.form.engine.object.XObject;
import net.cf.form.engine.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.oql.ast.statement.OqlInsertInto;
import net.cf.form.engine.oql.ast.statement.OqlInsertValues;

import java.util.Map;

public class DefaultValueGenerateOqlAstVisitor implements OqlAstVisitor {

    private Map<String, Object> dataMap;

    public DefaultValueGenerateOqlAstVisitor() {
    }

    public DefaultValueGenerateOqlAstVisitor(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public boolean visit(OqlInsertInto x) {
        String objectName = ((OqlIdentifierExpr) x.getObjectSource().getExpr()).getName();
        XObject object = x.getObjectSource().getResolvedObject();

        return true;
    }

    @Override
    public boolean visit(OqlInsertValues x) {
        // 从value子句中解析字段

        return true;
    }


}
