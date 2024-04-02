package net.cf.object.engine.oql.check;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.parser.Token;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;

import java.util.List;

/**
 * 插入语句的合法性校验器
 *
 * @author clouds
 */
public class InsertStatementChecker extends AbstractStatementChecker {

    public InsertStatementChecker() {
    }

    @Override
    public boolean visit(OqlInsertInto x) {
        List<OqlExpr> fields = x.getFields();
        List<SqlInsertStatement.ValuesClause> valuesList = x.getValuesList();

        // 检查插入的值与字段的数量是否匹配
        int fieldSize = fields.size();
        int valuesSize = valuesList.size();
        for (int i = 0; i < valuesSize; i++) {
            SqlInsertStatement.ValuesClause values = valuesList.get(i);
            if (values.getValues().size() != fieldSize) {
                if (valuesSize == 1) {
                    throw new FastOqlException("OQL语句中插中数据的个数与字段的个数不匹配！");
                } else {
                    throw new FastOqlException("OQL语句中插入的第" + (i + 1) + "值的数据的个数与字段的个数不匹配！");
                }
            }
        }

        // 校验字段的合法性
        for (SqlExpr field : fields) {
            if (field instanceof SqlAllColumnExpr) {
                throw new FastOqlException("OQL插入语句中不允许出现*号");
            } else if (field instanceof SqlPropertyExpr) {
                String propName = ((SqlPropertyExpr) field).getName();
                if (!Token.STAR.getName().equals(propName)) {
                    throw new FastOqlException("OQL插入语句中不允许出现*号");
                }
            } else if (field instanceof OqlObjectExpandExpr) {
                OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) field;
                List<OqlExpr> expandedFields = objectExpandExpr.getFields();
                for (OqlExpr expandedField : expandedFields) {
                    if (expandedField instanceof OqlFieldExpr) {
                        //throw new FastOqlException("OQL插入语句的模型展开表达式" + objectExpandExpr + "中的字段，只允许出现字段的属性！");
                    }
                }

            } else if (field instanceof OqlFieldExpandExpr) {
                // TODO
            } else if (field instanceof OqlFieldExpr) {
                // TODO
            } else if (field instanceof OqlPropertyExpr) {
                // TODO
            } else {
                throw new FastOqlException("OQL插入的字段必须是模型、字段、属性，不允许出现常量、方法调用等表达式");
            }
        }

        return false;
    }
}
