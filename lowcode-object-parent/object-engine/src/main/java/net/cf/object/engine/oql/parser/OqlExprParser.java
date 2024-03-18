package net.cf.object.engine.oql.parser;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlMethodInvokeExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.parser.Lexer;
import net.cf.form.repository.sql.parser.SqlExprParser;
import net.cf.form.repository.sql.parser.Token;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlFieldExpandExpr;
import net.cf.object.engine.oql.ast.OqlPropertyExpr;

import java.util.List;

/**
 * OQL 表达式解析器
 *
 * @author clouds
 */
public class OqlExprParser extends SqlExprParser {

    public OqlExprParser(String oql) {
        super(oql);
    }

    public OqlExprParser(Lexer lexer) {
        super(lexer);
    }

    protected OqlExprObjectSource parseExprObjectSource() {
        String objectName = this.lexer.stringVal();
        OqlExprObjectSource objectSource = new OqlExprObjectSource(objectName);
        this.lexer.nextToken();
        if (this.lexer.token() == Token.AS) {
            this.lexer.nextToken();
            String alias = this.lexer.stringVal();
            objectSource.setAlias(alias);
            this.lexer.nextToken();
        }

        return objectSource;
    }

    /**
     * 根据模型信息确定更准确的表达式
     *
     * @param expr
     * @param object
     * @return
     */
    protected SqlExpr getRealExprByObject(SqlExpr expr, XObject object) {
        SqlExpr targetExpr = expr;
        if (expr instanceof SqlMethodInvokeExpr) {
            SqlMethodInvokeExpr methodInvokeExpr = (SqlMethodInvokeExpr) expr;
            String methodName = methodInvokeExpr.getMethodName();
            XField field = object.getField(methodName);
            if (field != null) {
                OqlFieldExpandExpr fieldExpandExpr = new OqlFieldExpandExpr(field);
                List<SqlExpr> args = methodInvokeExpr.getArguments();
                for (SqlExpr arg : args) {
                    String propName = ((SqlIdentifierExpr) arg).getName();
                    SqlIdentifierExpr propertyExpr = new SqlIdentifierExpr(propName);
                    propertyExpr.setResolvedColumn(field.getProperty(propName).getColumnName());
                    propertyExpr.setResolvedOwnerTable(object.getTableName());
                    fieldExpandExpr.addProperty(propertyExpr);
                }
                targetExpr = fieldExpandExpr;
            }
        } else if (expr instanceof SqlIdentifierExpr) {
            SqlIdentifierExpr identifierExpr = (SqlIdentifierExpr) expr;
            String idName = identifierExpr.getName();
            XField field = object.getField(idName);
            if (field != null) {
                List<XProperty> properties = field.getProperties();
                if (properties != null && properties.size() > 0) {
                    OqlFieldExpandExpr fieldExpandExpr = new OqlFieldExpandExpr(field);
                    for (XProperty property : properties) {
                        SqlIdentifierExpr propertyExpr = new SqlIdentifierExpr(property.getName());
                        propertyExpr.setResolvedColumn(property.getColumnName());
                        propertyExpr.setResolvedOwnerTable(object.getTableName());
                        fieldExpandExpr.addProperty(propertyExpr);
                    }
                    targetExpr = fieldExpandExpr;
                }
            }
        } else if (expr instanceof SqlPropertyExpr) {
            SqlPropertyExpr sqlPropertyExpr = (SqlPropertyExpr) expr;
            SqlExpr owner = sqlPropertyExpr.getOwner();
            if (owner instanceof SqlIdentifierExpr) {
                // 判断owner是否是一个字段，并且属性存在
                String ownerName = ((SqlIdentifierExpr) owner).getName();
                String propertyName = sqlPropertyExpr.getName();
                XField ownerField = object.getField(ownerName);
                if (ownerField != null) {
                    XProperty property = ownerField.getProperty(propertyName);
                    if (property != null) {
                        OqlPropertyExpr fieldPropertyExpr = new OqlPropertyExpr(ownerField, propertyName);
                        fieldPropertyExpr.setResolvedColumn(property.getColumnName());
                        fieldPropertyExpr.setResolvedOwnerTable(object.getTableName());
                        targetExpr = fieldPropertyExpr;
                    }
                }
            }
        }

        return targetExpr;
    }

}
