package net.cf.object.engine.sqlbuilder.update;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlUpdateSetItem;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.object.ValueType;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.sqlbuilder.FieldItemInfo;
import net.cf.object.engine.sqlbuilder.SqlBuilderOqlAstVisitorAdaptor;
import net.cf.object.engine.util.OqlUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * MyOql select语句输出 AST访问器
 *
 * @author clouds
 */
public final class OqlUpdateAstVisitor extends SqlBuilderOqlAstVisitorAdaptor {

    private final SqlUpdateStatementBuilder builder;

    public OqlUpdateAstVisitor(SqlUpdateStatementBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(OqlUpdateStatement x) {
        this.selfObject = x.getObjectSource().getResolvedObject();
        this.buildTableSource(x.getObjectSource());
        this.buildSetItems(x.getSetItems());

        // 输出where条件
        SqlExpr where = x.getWhere();
        if (where != null) {
            this.builder.where(this.buildSqlExpr(this.selfObject, where));
        }

        return false;
    }

    /**
     * 访问数据源
     *
     * @param objectSource
     */
    private void buildTableSource(final OqlExprObjectSource objectSource) {
        SqlExprTableSource tableSource = new SqlExprTableSource();
        tableSource.setExpr(new SqlIdentifierExpr(objectSource.getResolvedObject().getTableName()));
        this.builder.tableSource(tableSource);
    }

    /**
     * 访问setItem列表
     *
     * @param setItems
     */
    private void buildSetItems(final List<OqlUpdateSetItem> setItems) {
        for (OqlUpdateSetItem setItem : setItems) {
            SqlExpr field = setItem.getField();
            SqlExpr value = setItem.getValue();
            if (field instanceof OqlFieldExpr) {
                XField resolvedField = ((OqlFieldExpr) field).getResolvedField();
                if (CollectionUtils.isEmpty(resolvedField.getProperties())) {
                    SqlUpdateSetItem sqlSetItem = new SqlUpdateSetItem();
                    sqlSetItem.setColumn(this.buildSqlExpr(this.selfObject, field));
                    sqlSetItem.setValue(this.buildSqlExpr(this.selfObject, setItem.getValue()));
                    this.builder.appendSetItem(sqlSetItem);
                    if (value instanceof SqlVariantRefExpr) {
                        String varName = ((SqlVariantRefExpr) value).getVarName();
                        this.builder.appendFieldMapping(new FieldMapping(varName, varName));
                    }
                } else {
                    this.processFieldValueExpand(resolvedField, value);
                }
            } else if (!(field instanceof OqlObjectExpandExpr)) {
                throw new FastOqlException("不支持的更新字段类型：" + field.getClass().getName());
            }
        }
    }

    /**
     * 展开set带子属性的字段
     *
     * @param resolvedField
     * @param updateValue
     */
    private void processFieldValueExpand(XField resolvedField, SqlExpr updateValue) {
        List<OqlPropertyExpr> properties = OqlUtils.defaultExpandFieldProperties(resolvedField);
        if (updateValue instanceof SqlJsonObjectExpr) {
            SqlJsonObjectExpr jsonObjectExpr = (SqlJsonObjectExpr) updateValue;
            Map<String, SqlExpr> items = jsonObjectExpr.getItems();
            for (OqlPropertyExpr property : properties) {
                SqlUpdateSetItem sqlSetItem = new SqlUpdateSetItem();
                sqlSetItem.setColumn(this.buildSqlExpr(this.selfObject, property));
                sqlSetItem.setValue(items.get(property.getName()));
                this.builder.appendSetItem(sqlSetItem);
            }
        } else if (updateValue instanceof SqlVariantRefExpr) {
            FieldItemInfo fieldItemInfo = new FieldItemInfo();
            FieldMapping fieldMapping = new FieldMapping(resolvedField.getName(), resolvedField.getColumnName());
            fieldMapping.setValueType(new ValueType(resolvedField.getDataType(), resolvedField.isArray()));
            fieldItemInfo.setFieldMapping(fieldMapping);

            SqlVariantRefExpr variantRefExpr = (SqlVariantRefExpr) updateValue;
            String varName = variantRefExpr.getVarName();
            for (OqlPropertyExpr property : properties) {
                if (property instanceof OqlPropertyExpr) {
                    XProperty resolvedProp = property.getResolvedProperty();
                    SqlUpdateSetItem sqlSetItem = new SqlUpdateSetItem();
                    SqlExpr updateItem = this.buildSqlExpr(this.selfObject, property);
                    sqlSetItem.setColumn(updateItem);
                    SqlVariantRefExpr propVarRefExpr = new SqlVariantRefExpr();
                    String propName = resolvedProp.getName();
                    propVarRefExpr.setVarName(varName + "." + propName);
                    sqlSetItem.setValue(propVarRefExpr);
                    this.builder.appendSetItem(sqlSetItem);

                    FieldMapping subFieldMapping = new FieldMapping(propName, propName);
                    fieldMapping.addSubField(subFieldMapping);
                } else {
                    throw new FastOqlException("OQL update语句的字段展开表达式中不支持字段属性之外的表达式");
                }
            }

            this.builder.appendFieldMapping(fieldMapping);
        } else {
            throw new FastOqlException("set子句中展开字段的设值必须是JSON或者变量引用，当前值：" + updateValue);
        }
    }

}
