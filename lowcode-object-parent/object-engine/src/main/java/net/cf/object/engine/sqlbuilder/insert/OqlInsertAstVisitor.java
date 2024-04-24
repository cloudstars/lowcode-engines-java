package net.cf.object.engine.sqlbuilder.insert;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
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
public final class OqlInsertAstVisitor extends SqlBuilderOqlAstVisitorAdaptor {

    private final SqlInsertStatementBuilder builder;

    public OqlInsertAstVisitor(SqlInsertStatementBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(OqlInsertStatement x) {
        this.selfObject = x.getObjectSource().getResolvedObject();

        XField primaryField = this.selfObject.getPrimaryField();
        if (primaryField != null && primaryField.isAutoGen()) {
            this.builder.setAutoGenName(primaryField.getColumnName());
        }

        // 构建表源
        SqlExprTableSource tableSource = this.buildSqlTableSource(x.getObjectSource());
        this.builder.tableSource(tableSource);

        // 构建插入的列
        this.buildInsertColumns(x);

        // 输出插入列的列表
        this.buildInsertValuesList(x);

        return false;
    }

    /**
     * 构建插入列的列表
     *
     * @param x
     */
    private void buildInsertColumns(OqlInsertStatement x) {
        List<OqlExpr> fields = x.getFields();
        for (OqlExpr field : fields) {
            if (field instanceof OqlFieldExpr) {
                XField resolvedField = ((OqlFieldExpr) field).getResolvedField();
                if (CollectionUtils.isEmpty(resolvedField.getProperties())) {
                    SqlExpr exprX = this.buildSqlExpr(this.selfObject, field);
                    builder.appendColumn(exprX);
                } else {
                    this.processFieldExpand(resolvedField);
                }
            } else if (!(field instanceof OqlObjectExpandExpr)) {
                // OqlObjectExpandExpr 会作为独立SQL语句插入，所以在此忽略即可
                throw new FastOqlException("不支持的插入字段类型：" + field.getClass().getName());
            }
        }
    }

    /**
     * 处理字段的属性
     *
     * @param resolvedField
     */
    private void processFieldExpand(XField resolvedField) {
        List<? extends SqlExpr> properties = OqlUtils.defaultExpandFieldProperties(resolvedField);
        for (SqlExpr property : properties) {
            SqlExpr exprX = this.buildSqlExpr(this.selfObject, property);
            this.builder.appendColumn(exprX);
        }
    }

    /**
     * 构建插入值的列表
     *
     * @param x
     */
    private void buildInsertValuesList(OqlInsertStatement x) {
        List<OqlExpr> insertFields = x.getFields();
        List<SqlInsertStatement.ValuesClause> valuesClauses = x.getValuesList();
        for (SqlInsertStatement.ValuesClause valuesClause : valuesClauses) {
            SqlInsertStatement.ValuesClause sqlValuesClause = this.toSqlValuesClause(valuesClause, insertFields);
            this.builder.appendInsertValues(sqlValuesClause);
        }
    }

    /**
     * 转换成SQL层的插入值子句
     *
     * @param valuesClause
     * @param insertFields
     * @return
     */
    private SqlInsertStatement.ValuesClause toSqlValuesClause(SqlInsertStatement.ValuesClause valuesClause, List<OqlExpr> insertFields) {
        SqlInsertStatement.ValuesClause valuesClauseX = new SqlInsertStatement.ValuesClause();
        List<SqlExpr> values = valuesClause.getValues();
        // 判断插入的列是否有被展开，如果列有展开，那么值也要被展开
        int size = insertFields.size();
        this.builder.getFieldMappings();
        for (int i = 0; i < size; i++) {
            SqlExpr insertField = insertFields.get(i);
            SqlExpr insertValue = values.get(i);
            if (insertField instanceof OqlFieldExpr) {
                XField resolvedField = ((OqlFieldExpr) insertField).getResolvedField();
                if (CollectionUtils.isEmpty(resolvedField.getProperties())) {
                    valuesClauseX.addValue(this.buildSqlExpr(this.selfObject, insertValue));
                } else {
                    this.processFieldValueExpand(resolvedField, insertValue, valuesClauseX);
                }
            } else if (!(insertField instanceof OqlObjectExpandExpr)) {
                throw new FastOqlException("不支持的插入字段类型：" + insertField.getClass().getName());
            }
        }

        return valuesClauseX;
    }


    /**
     * 处理字段展开
     *
     * @param resolvedField
     * @param insertValue
     * @param valuesClause
     */
    private void processFieldValueExpand(XField resolvedField, SqlExpr insertValue, SqlInsertStatement.ValuesClause valuesClause) {
        List<OqlPropertyExpr> properties = OqlUtils.defaultExpandFieldProperties(resolvedField);
        if (insertValue instanceof SqlJsonObjectExpr) {
            SqlJsonObjectExpr jsonObjectExpr = (SqlJsonObjectExpr) insertValue;
            Map<String, SqlExpr> items = jsonObjectExpr.getItems();
            for (OqlPropertyExpr property : properties) {
                SqlExpr itemValue = items.get(property.getName());;
                /*if (property instanceof OqlPropertyExpr) {
                    itemValue = items.get(property.getName());
                } else {
                    itemValue = this.buildSqlExpr(resolvedObject, property);
                }*/
                valuesClause.addValue(itemValue);
            }
        } else if (insertValue instanceof SqlVariantRefExpr) {
            FieldItemInfo fieldItemInfo = new FieldItemInfo();
            FieldMapping fieldMapping = new FieldMapping(resolvedField.getName(), resolvedField.getColumnName());
            fieldMapping.setValueType(new ValueType(resolvedField.getDataType(), resolvedField.isArray()));
            fieldItemInfo.setFieldMapping(fieldMapping);

            SqlVariantRefExpr variantRefExpr = (SqlVariantRefExpr) insertValue;
            String varName = variantRefExpr.getVarName();
            for (OqlPropertyExpr property : properties) {
                //if (property instanceof OqlPropertyExpr) {// 按属性展开
                    XProperty resolvedProp = property.getResolvedProperty();
                    String propName = resolvedProp.getName();
                    SqlVariantRefExpr propVarRefExpr = new SqlVariantRefExpr();
                    propVarRefExpr.setVarName(varName + "." + propName);
                    valuesClause.addValue(propVarRefExpr);

                    FieldMapping subFieldMapping = new FieldMapping(propName, propName);
                    fieldMapping.addSubField(subFieldMapping);
                /*} else {
                    SqlExpr itemValue = this.buildSqlExpr(resolvedObject, property);
                    valuesClause.addValue(itemValue);
                }*/
            }

            this.builder.appendFieldMapping(fieldMapping);
        } else {
            valuesClause.addValue(this.buildSqlExpr(this.selfObject, insertValue));
        }
    }

}