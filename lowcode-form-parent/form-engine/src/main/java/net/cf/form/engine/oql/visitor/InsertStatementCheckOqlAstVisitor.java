package net.cf.form.engine.oql.visitor;

import net.cf.commons.lang.BusinessException;
import net.cf.form.engine.object.XField;
import net.cf.form.engine.object.XObject;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.oql.ast.statement.OqlInsertInto;
import net.cf.form.engine.oql.ast.statement.OqlInsertValues;

import java.util.List;

public class InsertStatementCheckOqlAstVisitor implements OqlAstVisitor {

    public InsertStatementCheckOqlAstVisitor() {
    }

    @Override
    public boolean visit(OqlInsertInto x) {
        OqlExprObjectSource objectSource = x.getObjectSource();
        String objectName = ((OqlIdentifierExpr) objectSource.getExpr()).getName();
        XObject object = objectSource.getResolvedObject();
        if (object == null) {
            throw new BusinessException("对象" + objectName + "不存在！");
        }

        // TODO 把解析出来的对象存放起来

        // 检查字段名称是否存在
        List<OqlExpr> fieldExprs = x.getFields();
        for (OqlExpr fieldExpr : fieldExprs) {
            if (fieldExpr instanceof OqlIdentifierExpr) {
                String fieldName = ((OqlIdentifierExpr) fieldExpr).getName();
                XField field = object.getField(fieldName);
                if (field == null) {
                    throw new BusinessException("对象" + objectName + "的字段" + fieldName + "不存在！");
                }
                // TODO 把解析出来的XField字段存放起来
            }
        }

        // 检查插入的值与字段的数量是否匹配
        List<OqlInsertValues> valuesList = x.getValuesList();
        int fieldSize = fieldExprs.size();
        for (OqlInsertValues values : valuesList) {
            if (values.getValues().size() != fieldSize) {
                throw new BusinessException("对象" + objectName + "插入的值的数量与字段的数量不匹配！");
            }
        }

        return false;
    }
}
