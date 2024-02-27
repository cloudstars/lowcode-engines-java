package net.cf.object.engine.oql.visitor;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlInsertInto;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;

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
        String objectName = ((SqlIdentifierExpr) x.getObjectSource().getExpr()).getName();
        XObject object = x.getObjectSource().getResolvedObject();

        return true;
    }

    @Override
    public boolean visit(SqlInsertStatement.ValuesClause x) {
        // 从value子句中解析字段

        return true;
    }


}
