package net.cf.form.engine.oql.visitor;

import net.cf.form.engine.object.XField;
import net.cf.form.engine.object.XObject;
import net.cf.form.engine.oql.FastOqlException;
import net.cf.form.engine.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.oql.ast.statement.OqlInsertInto;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;

import java.util.List;

public class InsertStatementCheckOqlAstVisitor implements OqlAstVisitor {

    public InsertStatementCheckOqlAstVisitor() {
    }

    @Override
    public boolean visit(OqlInsertInto x) {
        OqlExprObjectSource objectSource = x.getObjectSource();
        String objectName = ((SqlIdentifierExpr) objectSource.getExpr()).getName();
        XObject object = objectSource.getResolvedObject();
        if (object == null) {
            throw new FastOqlException("对象" + objectName + "不存在！");
        }

        // TODO 把解析出来的对象存放起来

        // 检查字段名称是否存在
        List<SqlExpr> fieldExprs = x.getFields();
        for (SqlExpr fieldExpr : fieldExprs) {
            if (fieldExpr instanceof SqlIdentifierExpr) {
                String fieldName = ((SqlIdentifierExpr) fieldExpr).getName();
                XField field = object.getField(fieldName);
                if (field == null) {
                    throw new FastOqlException("对象" + objectName + "的字段" + fieldName + "不存在！");
                }
                // TODO 把解析出来的XField字段存放起来
            }
        }

        // 检查插入的值与字段的数量是否匹配
        List<SqlInsertStatement.ValuesClause> valuesList = x.getValuesList();
        int fieldSize = fieldExprs.size();
        for (SqlInsertStatement.ValuesClause values : valuesList) {
            if (values.getValues().size() != fieldSize) {
                throw new FastOqlException("对象" + objectName + "插入的值的数量与字段的数量不匹配！");
            }
        }

        return false;
    }
}
