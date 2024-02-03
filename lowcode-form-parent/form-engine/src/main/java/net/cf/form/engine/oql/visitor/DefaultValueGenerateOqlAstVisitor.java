package net.cf.form.engine.oql.visitor;

import net.cf.form.engine.XObjectResolver;
import net.cf.form.engine.object.XObject;
import net.cf.form.engine.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.oql.ast.statement.OqlInsertInto;
import net.cf.form.engine.oql.ast.statement.OqlInsertValues;

import java.util.Map;

public class DefaultValueGenerateOqlAstVisitor implements OqlAstVisitor {

    private XObjectResolver resolver;

    private Map<String, Object> dataMap;

    public DefaultValueGenerateOqlAstVisitor(XObjectResolver resolver) {
        this.resolver = resolver;
    }

    public DefaultValueGenerateOqlAstVisitor(XObjectResolver resolver, Map<String, Object> dataMap) {
        this.resolver = resolver;
        this.dataMap = dataMap;
    }

    @Override
    public boolean visit(OqlInsertInto x) {
        String objectName = ((OqlIdentifierExpr) x.getObjectSource().getExpr()).getName();
        XObject object = this.resolver.resolveObject(objectName);

        return true;
    }

    @Override
    public boolean visit(OqlInsertValues x) {
        // 从value子句中解析字段

        return true;
    }


}
