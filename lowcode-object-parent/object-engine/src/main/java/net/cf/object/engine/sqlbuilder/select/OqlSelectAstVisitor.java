package net.cf.object.engine.sqlbuilder.select;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.object.*;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.parser.XObjectResolver;
import net.cf.object.engine.oql.util.OqlUtils;
import net.cf.object.engine.sqlbuilder.SqlBuilderOqlAstVisitorAdaptor;

import java.util.ArrayList;
import java.util.List;

/**
 * OQL Select 语句 AST 访问器，用于输出SQL Select
 *
 * @author clouds
 */
public final class OqlSelectAstVisitor extends SqlBuilderOqlAstVisitorAdaptor {

    private final SqlSelectStatementBuilder builder;

    private final XObjectResolver resolver;

    public OqlSelectAstVisitor(SqlSelectStatementBuilder builder, XObjectResolver resolver) {
        this.builder = builder;
        this.resolver = resolver;
    }

    @Override
    public boolean visit(OqlSelect x) {
        OqlObjectSource from = x.getFrom();
        if (from instanceof OqlExprObjectSource) {
            this.selfObject = from.getResolvedObject();
        } else {
            throw new FastOqlException("暂不支持的OQL数据源类型：" + from.getClass().getName());
        }

        // 构建查询的表
        if (from instanceof OqlExprObjectSource) { // 标识符的模型源
            SqlExprTableSource fromTableSource = this.buildSqlTableSource((OqlExprObjectSource) from);
            this.builder.from(fromTableSource);
        } else if (from instanceof OqlSubQueryObjectSource) { // 子查询的模型源
        }

        // 构建查询列的列表
        List<SqlSelectItem> selectItems = x.getSelectItems();
        for (SqlSelectItem selectItem : selectItems) {
            SqlExpr sqlExpr = selectItem.getExpr();
            if (sqlExpr instanceof SqlAllColumnExpr) { // 本模型全部字段
                List<SelectItemInfo> selectItemInfos = this.buildSelectObjectAllFields(this.selfObject);
                for (SelectItemInfo selectItemInfo : selectItemInfos) {
                    this.builder.appendSelectItemInfo(selectItemInfo);
                }
                continue;
            }

            SelectItemInfo itemInfo;
            if (sqlExpr instanceof OqlObjectExpandExpr) { // 关联模型展开
                OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) sqlExpr;
                itemInfo = this.buildSelectRefObjectExpand(objectExpandExpr);
            } else if (sqlExpr instanceof OqlFieldExpandExpr) { // 字段展开
                OqlFieldExpandExpr fieldExpandExpr = (OqlFieldExpandExpr) sqlExpr;
                itemInfo = this.buildSelectFieldExpandExpr(fieldExpandExpr);
            } else {
                itemInfo = this.buildSimpleItemInfo(this.selfObject, sqlExpr);
            }
            this.builder.appendSelectItemInfo(itemInfo);
        }

        SqlExpr where = x.getWhere();
        if (where != null) {
            this.builder.where(this.buildSqlExpr(this.selfObject, where));
        }
        this.builder.groupBy(x.getGroupBy());
        this.builder.orderBy(x.getOrderBy());
        this.builder.limit(x.getLimit());

        return false;
    }

    /**
     * 构建查询本模型的全部字段（select *）
     */
    private List<SelectItemInfo> buildSelectObjectAllFields(XObject object) {
        List<SqlExpr> fieldExprs = OqlUtils.defaultExpandObjectFields(object);
        List<SelectItemInfo> selectItemInfos = this.buildItemInfoList(object, fieldExprs);
        return selectItemInfos;
    }


    /**
     * 构建查询字段的全部属性
     *
     * @param field
     * @return
     */
    private SelectItemInfo buildSelectFieldAllProperties(XField field) {
        XObject object = field.getOwner();

        // 构建字段的查询信息（展开无字段信息）
        SelectItemInfo parentItemInfo = new SelectItemInfo();
        FieldMapping parentFieldMapping = new FieldMapping(field.getName());
        parentFieldMapping.setValueType(new ValueType(field.getDataType(), field.isArray()));
        parentItemInfo.setFieldMapping(parentFieldMapping);

        List<OqlPropertyExpr> propExprs = OqlUtils.defaultExpandFieldProperties(field);
        List<SelectItemInfo> subItemInfos = this.buildItemInfoList(object, propExprs);
        parentItemInfo.addSubItemInfos(subItemInfos);

        return parentItemInfo;
    }

    /**
     * 构建关联查询
     *
     * @param objectExpandExpr
     */
    private SelectItemInfo buildSelectRefObjectExpand(OqlObjectExpandExpr objectExpandExpr) {
        XObjectRefField objectRefField = objectExpandExpr.getResolvedObjectRefField();
        String refObjectName = objectRefField.getRefObjectName();
        XObject refObject = this.resolver.resolve(refObjectName);
        assert (!objectRefField.getRefObjectName().equals(this.selfObject.getName()));

        // 添加关联表
        this.builder.refObject(objectRefField, refObject);

        // 构建模型的查询信息（展开无字段信息）
        SelectItemInfo parentItemInfo = new SelectItemInfo();
        FieldMapping parentFieldMapping = new FieldMapping(objectRefField.getName());
        parentFieldMapping.setValueType(new ValueType(objectRefField.getDataType(), objectRefField.isArray()));
        parentItemInfo.setFieldMapping(parentFieldMapping);

        List<SelectItemInfo> subItemInfos;
        if (objectExpandExpr.isDefaultExpanded() || objectExpandExpr.isStarExpanded()) {
            subItemInfos = this.buildSelectObjectAllFields(refObject);
        } else {
            List<SqlExpr> refFields = objectExpandExpr.getFields();
            subItemInfos = this.buildItemInfoList(refObject, refFields);
        }
        parentItemInfo.addSubItemInfos(subItemInfos);

        return parentItemInfo;
    }

    /**
     * 构建字段展开的查询字段
     *
     * @param expr
     */
    private SelectItemInfo buildSelectFieldExpandExpr(OqlFieldExpandExpr expr) {
        XField field = expr.getResolvedField();
        XObject object = field.getOwner();

        // 构建字段的查询信息（展开无字段信息）
        SelectItemInfo parentSelectItemInfo = new SelectItemInfo();
        FieldMapping parentFieldMapping = new FieldMapping(field.getName(), field.getDataType(), field.isArray());
        parentSelectItemInfo.setFieldMapping(parentFieldMapping);

        if (expr.isDefaultExpanded() || expr.isStarExpanded()) {
            parentSelectItemInfo = this.buildSelectFieldAllProperties(field);
        } else {
            List<SqlExpr> properties = expr.getProperties();
            List<SelectItemInfo> subItemInfos = this.buildItemInfoList(object, properties);
            parentSelectItemInfo.addSubItemInfos(subItemInfos);
        }

        return parentSelectItemInfo;
    }

    /**
     * 根据一个模型的SQL表达式列表构建它的查询信息
     *
     * @param object
     * @param exprs
     * @return
     */
    private <T extends SqlExpr> List<SelectItemInfo> buildItemInfoList(XObject object, List<T> exprs) {
        List<SelectItemInfo> itemInfos = new ArrayList<>();
        for (T expr : exprs) {
            SelectItemInfo itemInfo;
            if (expr instanceof OqlObjectExpandExpr) {
                OqlObjectExpandExpr expandExpr = (OqlObjectExpandExpr) expr;
                XObjectRefField objectRefField = expandExpr.getResolvedObjectRefField();
                XObject refObject = objectRefField.getOwner();
                itemInfo = this.expand(refObject, objectRefField, expandExpr.getFields());
            } else if (expr instanceof OqlFieldExpandExpr) {
                OqlFieldExpandExpr expandExpr = (OqlFieldExpandExpr) expr;
                XField expandField = expandExpr.getResolvedField();
                XObject expandObject = expandField.getOwner();
                itemInfo = this.expand(expandObject, expandField, expandExpr.getProperties());
            } else {
                itemInfo = this.buildSimpleItemInfo(object, expr);
            }
            itemInfos.add(itemInfo);
        }

        return itemInfos;
    }

    /**
     * 展开模型或展开字段
     *
     * @param expandObject
     * @param expandField
     * @param expandExprs
     * @return
     */
    private SelectItemInfo expand(XObject expandObject, XField expandField, List<SqlExpr> expandExprs) {
        SelectItemInfo parentItemInfo = new SelectItemInfo();
        FieldMapping parentFieldMapping = new FieldMapping(expandField.getOwner().getName());
        parentFieldMapping.setValueType(new ValueType(expandField.getDataType(), expandField.isArray()));
        parentItemInfo.setFieldMapping(parentFieldMapping);
        List<SelectItemInfo> subItemInfos = this.buildItemInfoList(expandObject, expandExprs);
        parentItemInfo.addSubItemInfos(subItemInfos);

        return parentItemInfo;
    }

    /**
     * 构建简单的查询字段
     *
     * @param object
     * @param expr
     */
    private SelectItemInfo buildSimpleItemInfo(XObject object, SqlExpr expr) {
        SqlExpr exprX = this.buildSqlExpr(object, expr);
        SelectItemInfo itemInfo = new SelectItemInfo();
        FieldMapping fieldMapping = this.createFieldMapping(object, expr, exprX);
        itemInfo.setFieldMapping(fieldMapping);
        SqlSelectItem sqlSelectItem = new SqlSelectItem(exprX);
        itemInfo.addSelectItem(sqlSelectItem);
        return itemInfo;
    }

    /**
     * 生成字段印射信息
     *
     * @param object
     * @param oqlExpr
     * @param sqlExpr
     */
    private FieldMapping createFieldMapping(XObject object, SqlExpr oqlExpr, SqlExpr sqlExpr) {
        // 经过OQL转换后，这两种类型已经不存在了
        assert (!(oqlExpr instanceof SqlIdentifierExpr && oqlExpr instanceof SqlPropertyExpr));

        FieldMapping fieldMapping;
        if (oqlExpr instanceof OqlFieldExpr) {
            OqlFieldExpr fieldExpr = (OqlFieldExpr) oqlExpr;
            XField resolvedField = fieldExpr.getResolvedField();
            // TODO 生成列名可以优化，把前面生成SqlIdentifierExp、SqlPropertyExpr的结果保存下来
            String fieldName = this.getFieldName(fieldExpr);
            String columnName = resolvedField.getColumnName();
            if (object != selfObject) {
                columnName = object.getTableName() + "." + columnName;
            }
            fieldMapping = new FieldMapping(fieldName, columnName, resolvedField.getDataType(), resolvedField.isArray());
        } else if (oqlExpr instanceof OqlPropertyExpr) {
            OqlPropertyExpr propExpr = (OqlPropertyExpr) oqlExpr;
            XProperty property = propExpr.getResolvedProperty();
            String fieldName = this.getPropertyName(propExpr);
            String columnName = property.getColumnName();
            if (object != selfObject) {
                columnName = object.getTableName() + "." + columnName;
            }
            fieldMapping = new FieldMapping(fieldName, columnName, property.getDataType(), property.isArray());
        } else {
            String fieldName = OqlUtils.expr2String(oqlExpr);
            String columnName = sqlExpr.toString();
            // TODO 这里没有数据类型，可以考虑SqlExpr定义getDataType()接口
            fieldMapping = new FieldMapping(fieldName, columnName, null, false);
        }

        return fieldMapping;
    }

    /**
     * 获取字段的名称
     *
     * @param fieldExpr
     * @return
     */
    private String getFieldName(OqlFieldExpr fieldExpr) {
        SqlIdentifierExpr owner = fieldExpr.getOwner();
        if (owner != null) {
            return owner.getName() + "." + fieldExpr.getName();
        } else {
            return fieldExpr.getName();
        }
    }

    /**
     * 获取属性的名称
     *
     * @param propExpr
     * @return
     */
    private String getPropertyName(OqlPropertyExpr propExpr) {
        OqlFieldExpr owner = propExpr.getOwner();
        if (owner != null) {
            return owner.getName() + "." + propExpr.getName();
        } else {
            return propExpr.getName();
        }
    }

}


