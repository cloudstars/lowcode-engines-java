package net.cf.form.engine.repository.mongo.statement.insert;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.data.value.DataType;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlValuableExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlVariantRefExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlBooleanExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlIntegerExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlJsonObjectExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlTextLiteralExpr;
import net.cf.form.engine.repository.oql.ast.statement.*;
import net.cf.form.engine.repository.oql.visitor.OqlAstVisitorAdaptor;
import net.cf.form.engine.repository.oql.ast.statement.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于生成 MySql insert 语句的AST访问器
 *
 * @author clouds
 */
public final class MongoInsertSqlAstVisitor extends OqlAstVisitorAdaptor {

    private DataObjectResolver resolver;

    /**
     * 当前插入的对象
     */
    protected DataObject resolvedDataObject;

    /**
     * 主表的插入语句
     */
    private MongoInsertSqlInfo sqlInfo = null;

    /**
     * 子表的插入语句
     */
    private final List<MongoInsertSqlInfo> detailSqlInfos = new ArrayList<>();

    public MongoInsertSqlAstVisitor(DataObjectResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * 获取主表的插入语句
     *
     * @return
     */
    public MongoInsertCommand getSql() {
        MongoInsertBuilder builder = new MongoInsertBuilder(sqlInfo);
        return builder.buildSql();
    }

    /**
     * 获取子表的插入语句
     *
     * @return
     */
    public List<MongoInsertCommand> getDetailSqls() {
        List<MongoInsertCommand> sqls = new ArrayList<>();
        for (MongoInsertSqlInfo detailSqlInfo : this.detailSqlInfos) {
            MongoInsertBuilder builder = new MongoInsertBuilder(detailSqlInfo);
            sqls.add(builder.buildSql());
        }

        return sqls;
    }

    @Override
    public boolean visit(OqlInsertStatement x) {
        return true;
    }

    @Override
    public boolean visit(OqlInsertInto x) {
        // 设置数据源
        OqlObjectSource objectSource = x.getObjectSource();
        if (objectSource instanceof OqlExprObjectSource) {
            String objectName = ((OqlExprObjectSource) objectSource).getExpr().getName();
            DataObject resolvedDataObject = resolver.resolveObject(objectName);
            assert (resolvedDataObject != null);
            this.resolvedDataObject = resolvedDataObject;
            this.sqlInfo = new MongoInsertSqlInfo(this.resolvedDataObject);;
        }

        // 遍历字段列表
        List<QqlExpr> fields = x.getFields();
        for (QqlExpr field : fields) {
            if (field instanceof OqlIdentifierExpr) {
                OqlIdentifierExpr identField = (OqlIdentifierExpr) field;
                identField.setResolvedOwnerObject(this.resolvedDataObject);
                identField.setResolvedField(this.resolvedDataObject.getField(identField.getName()));
                MongoColumnDefinition column = new MongoColumnDefinition();
                column.setName(identField.getResolvedField().getColumnName());
                sqlInfo.addColumn(column);
            } else if (field instanceof OqlMethodInvokeExpr) {
                OqlMethodInvokeExpr methodInvokeExpr = (OqlMethodInvokeExpr) field;
                String objectName = methodInvokeExpr.getMethodName();
                DataObject resolvedDetailDataObject = resolver.resolveObject(objectName);
                assert (resolvedDetailDataObject != null);

                // 处理子表
            }
        }

        // 遍历值列表
        List<OqlInsertValues> insertValuesList = x.getValuesList();
        for (OqlInsertValues insertValues : insertValuesList) {
            MongoColumnValues columnValues = new MongoColumnValues();
            List<QqlExpr> values = insertValues.getValues();
            for (QqlExpr value : values) {
                MongoColumnValue columnValue = new MongoColumnValue();
                // todo 字段数据为列表，暂时不支持解析
                columnValue.setValueType(getExprValueType(value));
                if (value instanceof OqlVariantRefExpr) {
                    String varName = ((OqlVariantRefExpr) value).getName();
                    columnValue.setValue(":" + varName.substring(2, varName.length() - 1));
                } else {
                    columnValue.setValue(analyseOqlExpr(value));
                }
//                else if (value instanceof OqlValuableExpr) {
//                    columnValue.setValue(((OqlValuableExpr) value).getValue());
//                } else if (value instanceof OqlJsonObjectExpr) {
//                    columnValue.setValue(analyseJsonObject((OqlJsonObjectExpr) value));
//                }
                columnValues.addValue(columnValue);
            }
            sqlInfo.addValues(columnValues);
        }

        return false;
    }


    /**
     * 解析值
     * @param qqlExpr
     * @return
     */
    private Object analyseOqlExpr(QqlExpr qqlExpr) {
        if (qqlExpr instanceof OqlJsonObjectExpr) {
            return analyseJsonObject((OqlJsonObjectExpr) qqlExpr);
        } else if (qqlExpr instanceof OqlListExpr) {
            return analyseList((OqlListExpr) qqlExpr);
        } else if (qqlExpr instanceof QqlValuableExpr) {
            return ((QqlValuableExpr) qqlExpr).getValue();
        }
        return null;

    }

    private Object analyseJsonObject(OqlJsonObjectExpr value) {
        Map<String, Object> res = new HashMap<>();

        for (Map.Entry<String, QqlExpr> entry : value.getItems().entrySet()) {
            QqlExpr qqlExpr = entry.getValue();
            res.put(entry.getKey(), analyseOqlExpr(qqlExpr));
        }
        return res;
    }


    private Object analyseList(OqlListExpr expr) {
        List<Object> res = new ArrayList<>();
        for (QqlExpr qqlExpr : expr.getItems()) {
            res.add(analyseOqlExpr(qqlExpr));
        }
        return res;
    }

    /**
     * 根据OQL表达式类型获取驱动层的值类型
     *
     * @param expr
     * @return
     */
    private DataType getExprValueType(QqlExpr expr) {
        DataType valueType = null;
        if (expr instanceof OqlBooleanExpr) {
            valueType = DataType.BOOLEAN;
        } else if (expr instanceof OqlTextLiteralExpr) {
            valueType = DataType.TEXT;
        } else if (expr instanceof OqlIntegerExpr) {
            valueType = DataType.INTEGER;
        } else if (expr instanceof OqlJsonObjectExpr) {
            valueType = DataType.OBJECT;
        }

        return valueType;
    }
}
