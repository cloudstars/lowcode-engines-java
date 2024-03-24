package net.cf.object.engine.sqlbuilder.select;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.parser.XObjectResolver;
import net.cf.object.engine.oql.util.OqlUtils;
import net.cf.object.engine.sqlbuilder.SqlBuilderOqlAstVisitorAdaptor;

import java.util.ArrayList;
import java.util.Arrays;
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

            SelectItemInfo selectItemInfo;
            if (sqlExpr instanceof OqlObjectExpandExpr) { // 关联模型展开
                OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) sqlExpr;
                selectItemInfo = this.buildSelectRefObjectExpand(objectExpandExpr);
            } else if (sqlExpr instanceof OqlFieldExpandExpr) { // 字段展开
                OqlFieldExpandExpr fieldExpandExpr = (OqlFieldExpandExpr) sqlExpr;
                selectItemInfo = this.buildSelectFieldExpandExpr(fieldExpandExpr);
            } else {
                selectItemInfo = this.buildItemInfo(this.selfObject, sqlExpr);
            }
            this.builder.appendSelectItemInfo(selectItemInfo);
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
        SelectItemInfo parentSelectItemInfo = new SelectItemInfo();
        parentSelectItemInfo.setFieldName(field.getName());
        parentSelectItemInfo.setArray(field.isArray());
        parentSelectItemInfo.setColumnName(null);

        List<OqlPropertyExpr> propExprs = OqlUtils.defaultExpandFieldProperties(field);
        List<SelectItemInfo> subItemInfos = this.buildItemInfoList(object, propExprs);
        parentSelectItemInfo.setSubItemInfos(subItemInfos);

        return parentSelectItemInfo;
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
        SelectItemInfo parentSelectItemInfo = new SelectItemInfo();
        parentSelectItemInfo.setFieldName(objectRefField.getName());
        parentSelectItemInfo.setArray(objectRefField.isArray());
        parentSelectItemInfo.setColumnName(null);

        List<SelectItemInfo> subItemInfos;
        if (objectExpandExpr.isDefaultExpanded() || objectExpandExpr.isStarExpanded()) {
            subItemInfos = this.buildSelectObjectAllFields(refObject);
        } else {
            List<SqlExpr> refFields = objectExpandExpr.getFields();
            subItemInfos = this.buildItemInfoList(refObject, refFields);
        }
        parentSelectItemInfo.setSubItemInfos(subItemInfos);

        return parentSelectItemInfo;
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
        parentSelectItemInfo.setFieldName(field.getName());
        parentSelectItemInfo.setArray(field.isArray());

        if (expr.isDefaultExpanded() || expr.isStarExpanded()) {
            parentSelectItemInfo = this.buildSelectFieldAllProperties(field);
        } else {
            List<SqlExpr> properties = expr.getProperties();
            List<SelectItemInfo> subItemInfos = this.buildItemInfoList(object, properties);
            parentSelectItemInfo.setSubItemInfos(subItemInfos);
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
            if (expr instanceof OqlObjectExpandExpr) {
                OqlObjectExpandExpr expandExpr = (OqlObjectExpandExpr) expr;
                XObjectRefField objectRefField = expandExpr.getResolvedObjectRefField();
                XObject refObject = objectRefField.getOwner();
                SelectItemInfo parentSelectItemInfo = new SelectItemInfo();
                parentSelectItemInfo.setFieldName(expandExpr.getOwner().getName());
                parentSelectItemInfo.setArray(objectRefField.isArray());
                parentSelectItemInfo.setColumnName(null);
                List<SelectItemInfo> subItemInfos = this.buildItemInfoList(refObject, expandExpr.getFields());
                parentSelectItemInfo.setSubItemInfos(subItemInfos);
                itemInfos.add(parentSelectItemInfo);
            } else if (expr instanceof OqlFieldExpandExpr) {
                OqlFieldExpandExpr expandExpr = (OqlFieldExpandExpr) expr;
                XField expandField = expandExpr.getResolvedField();
                XObject expandObject = expandField.getOwner();
                SelectItemInfo parentSelectItemInfo = new SelectItemInfo();
                parentSelectItemInfo.setFieldName(expandExpr.getOwner().getName());
                parentSelectItemInfo.setArray(expandField.isArray());
                parentSelectItemInfo.setColumnName(null);
                List<SelectItemInfo> subItemInfos = this.buildItemInfoList(expandObject, expandExpr.getProperties());
                parentSelectItemInfo.setSubItemInfos(subItemInfos);
                itemInfos.add(parentSelectItemInfo);
            } else {
                SqlExpr exprX = this.buildSqlExpr(object, expr);
                SqlSelectItem sqlSelectItem = new SqlSelectItem(exprX);
                SelectItemInfo selectItemInfo = this.createSelectItemInfo(object, expr, exprX);
                selectItemInfo.setSelectItems(Arrays.asList(sqlSelectItem));
                itemInfos.add(selectItemInfo);
            }
        }
        return itemInfos;
    }

    /**
     * 构建简单的查询字段
     *
     * @param object
     * @param expr
     */
    private SelectItemInfo buildItemInfo(XObject object, SqlExpr expr) {
        SqlExpr exprX = this.buildSqlExpr(object, expr);
        SqlSelectItem sqlSelectItem = new SqlSelectItem(exprX);
        SelectItemInfo selectItemInfo = this.createSelectItemInfo(object, expr, exprX);
        selectItemInfo.setSelectItems(Arrays.asList(sqlSelectItem));
        return selectItemInfo;
    }

    /**
     * 生成SQL查询字段信息
     *
     * @param oqlExpr
     * @param sqlExpr
     */
    private SelectItemInfo createSelectItemInfo(XObject object, SqlExpr oqlExpr, SqlExpr sqlExpr) {
        // 经过OQL转换后，这两种类型已经不存在了
        assert (!(oqlExpr instanceof SqlIdentifierExpr && oqlExpr instanceof SqlPropertyExpr));

        SelectItemInfo info = new SelectItemInfo();
        if (oqlExpr instanceof OqlFieldExpr) {
            OqlFieldExpr fieldExpr = (OqlFieldExpr) oqlExpr;
            XField resolvedField = fieldExpr.getResolvedField();
            info.setFieldName(resolvedField.getName());
            info.setArray(resolvedField.isArray());
            if (object == selfObject) {
                info.setColumnName(resolvedField.getColumnName());
            } else {
                info.setColumnName(object.getTableName() + "." + resolvedField.getColumnName());
            }
        } else if (oqlExpr instanceof OqlPropertyExpr) {
            OqlPropertyExpr propExpr = (OqlPropertyExpr) oqlExpr;
            XProperty property = propExpr.getResolvedProperty();
            info.setFieldName(property.getName());
            info.setArray(property.isArray());
            if (object == selfObject) {
                info.setColumnName(property.getColumnName());
            } else {
                info.setColumnName(object.getTableName() + "." + property.getColumnName());
            }
        } else {
            info.setFieldName(oqlExpr.toString());
            info.setColumnName(sqlExpr.toString());
        }

        return info;
    }

}


