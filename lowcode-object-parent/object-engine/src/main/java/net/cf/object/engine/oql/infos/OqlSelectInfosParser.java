package net.cf.object.engine.oql.infos;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.object.*;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.cmd.OqlSelectInfo;
import net.cf.object.engine.oql.cmd.OqlSelectInfos;
import net.cf.object.engine.util.OqlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OQL查询语句解析器
 * <p>
 * 职责：用于将一条OQL查询语句解析成本表的查询、子表的查询、相关表的查询
 */
public class OqlSelectInfosParser extends AbstractOqInfoParser<OqlSelectStatement, OqlSelectInfos> {

    /**
     * 模型关联的查询语句
     */
    private final Map<XObject, SqlSelectStatement> stmtMap = new HashMap<>();

    /**
     * 模型的字段映射关系
     */
    private final Map<XObject, List<FieldMapping>> fieldMappingsMap = new HashMap<>();

    /**
     * 模型关联的别名，如select refField(f1, f2) as xxx，xxx就是refField对应模型的别名
     */
    private final Map<XObject, String> aliasMap = new HashMap<>();

    private final RepoExprBuilder sqlExprBuilder;

    private int nonFieldSelectItemIndex = 0;

    public OqlSelectInfosParser(OqlSelectStatement stmt, boolean isBatch) {
        super(stmt, isBatch);
        this.sqlExprBuilder = new RepoExprBuilder();
    }

    /**
     * 解析成OQL查询语句指令信息
     *
     * @return
     */
    public OqlSelectInfos parse() {
        // 解析当前语句的本模型
        OqlSelect select = this.stmt.getSelect();
        this.masterObject = select.getFrom().getResolvedObject();

        // 获取本模型对应的SQL语句
        SqlSelectStatement selfStmt = this.getStmtByObject(this.masterObject);
        SqlSelect selfSelect = selfStmt.getSelect();

        // 解析查询字段
        List<OqlSelectItem> selectItems = select.getSelectItems();
        this.parseSelectItems(this.masterObject, selectItems);

        // 解析where条件
        SqlExpr where = select.getWhere();
        if (where != null) {
            OqlWhereExprParser whereExprParser = new OqlWhereExprParser(this.masterObject);
            SqlExpr whereX = whereExprParser.parseExpr(where);
            selfSelect.setWhere(whereX);
        }
        // 解析分组
        SqlSelectGroupByClause groupByClause = select.getGroupBy();
        if (groupByClause != null) {
            SqlSelectGroupByClause groupByClauseX = this.parseGroupByClause(groupByClause);
            selfSelect.setGroupBy(groupByClauseX);
        }
        // 解析排序
        SqlOrderBy orderBy = select.getOrderBy();
        if (orderBy != null) {
            SqlOrderBy orderByX = this.parseOrderBy(orderBy);
            selfSelect.setOrderBy(orderByX);
        }
        // 解析限制行数
        SqlLimit limit = select.getLimit();
        if (limit != null) {
            selfSelect.setLimit(limit);
        }

        // 构建OQL查询语句
        OqlSelectInfos selectInfos = new OqlSelectInfos();
        selectInfos.setResolvedMasterObject(this.masterObject);
        for (Map.Entry<XObject, SqlSelectStatement> entry : stmtMap.entrySet()) {
            XObject thisObject = entry.getKey();
            OqlSelectInfo selectInfo = new OqlSelectInfo();
            selectInfo.setResolvedObject(thisObject);
            SqlSelectStatement stmt = this.stmtMap.get(thisObject);
            if (thisObject == this.masterObject) {
                selectInfos.setSelfSelectInfo(selectInfo);
            } else {
                XObjectRefField objectRefField = this.masterObject.getObjectRefField(thisObject.getName());
                selectInfo.setObjectRefFieldName(objectRefField.getName());
                selectInfo.setObjectRefFieldAlias(this.aliasMap.get(thisObject));
                SqlExpr whereExpr;
                if (objectRefField.getRefType() == ObjectRefType.DETAIL) {
                    selectInfos.addDetailSelectInfo(selectInfo);
                    if (stmt.getSelect().getSelectItems().size() == 0) {
                        selectInfo.setDetailFieldDirectQuery(true);
                        // 如果只查询了子表字段，默认会返回子表的ID数组
                        this.addFieldToStmt(thisObject.getPrimaryField(), null);
                    }
                    // 默认查询子表的masterField字段
                    XObjectRefField detailMasterField = thisObject.getObjectRefField(masterObject.getName());
                    this.addFieldToStmt(detailMasterField, null);
                    if (this.isBatch) {
                        // where masterField in (#{masterFields})
                        whereExpr = this.buildFieldInListVarRefExpr(detailMasterField);
                    } else {
                        // where masterField = #{masterField}
                        whereExpr = this.buildFieldEqualsVarRefExpr(detailMasterField);
                    }
                } else {
                    // 默认查询本表的lookupField字段
                    this.addFieldToStmt(objectRefField, null);
                    selectInfos.addLookupSelectInfo(selectInfo);
                    if (this.isBatch || objectRefField.isMultiRef()) {
                        // where primaryField in (#{primaryFields})
                        whereExpr = this.buildFieldInListVarRefExpr(thisObject.getPrimaryField());
                    } else {
                        // where primaryField = #{primaryField}
                        whereExpr = this.buildFieldEqualsVarRefExpr(thisObject.getPrimaryField());
                    }
                }
                stmt.getSelect().setWhere(whereExpr);
            }
            selectInfo.setStatement(stmt);
            selectInfo.setFieldMappings(this.fieldMappingsMap.get(thisObject));
        }

        return selectInfos;
    }

    /**
     * 解析模型下的一组查询字段
     *
     * @param thisObject
     * @param selectItems
     */
    private void parseSelectItems(XObject thisObject, List<OqlSelectItem> selectItems) {
        for (OqlSelectItem selectItem : selectItems) {
            this.parseSelectItem(thisObject, selectItem);
        }
    }

    /**
     * 解析模型下的单个查询字段
     *
     * @param thisObject
     * @param selectItem
     */
    private void parseSelectItem(XObject thisObject, OqlSelectItem selectItem) {
        SqlExpr selectItemExpr = selectItem.getExpr();
        String alias = selectItem.getAlias();

        // OQL语句中不含这两个表达式，如果有的话，一定是程序处理出错了
        assert (!(selectItemExpr instanceof SqlIdentifierExpr));
        assert (!(selectItemExpr instanceof SqlPropertyExpr));

        if (selectItemExpr instanceof SqlAllColumnExpr) {
            this.addObjectAllFieldsToStmt(thisObject);
        } else if (selectItemExpr instanceof OqlFieldExpr) {
            this.parseSelectFieldExpr((OqlFieldExpr) selectItemExpr, alias);
        } else if (selectItemExpr instanceof OqlPropertyExpr) {
            throw new FastOqlException("查询字段中不允许出现字段属性!");
        } else if (selectItemExpr instanceof OqlObjectExpandExpr) {
            this.parseSelectObjectExpandExpr((OqlObjectExpandExpr) selectItemExpr, alias);
        } else {
            this.parseExpr(thisObject, selectItemExpr, alias);
        }
    }

    /**
     * 分析分组子句
     *
     * @param groupByClause
     * @return
     */
    private SqlSelectGroupByClause parseGroupByClause(SqlSelectGroupByClause groupByClause) {
        SqlSelectGroupByClause groupByClauseX = new SqlSelectGroupByClause();
        List<SqlExpr> groupByItems = groupByClause.getItems();
        for (SqlExpr groupByItem : groupByItems) {
            SqlExpr groupByItemX = this.sqlExprBuilder.buildSqlExpr(this.masterObject, groupByItem);
            groupByClauseX.addItem(groupByItemX);
        }

        return groupByClauseX;
    }

    /**
     * 解析orderBy
     *
     * @param orderBy
     * @return
     */
    private SqlOrderBy parseOrderBy(SqlOrderBy orderBy) {
        SqlOrderBy orderByX = new SqlOrderBy();
        List<SqlSelectOrderByItem> orderByItems = orderBy.getItems();
        for (SqlSelectOrderByItem orderByItem : orderByItems) {
            SqlSelectOrderByItem orderByItemX = new SqlSelectOrderByItem();
            orderByItemX.setExpr(this.sqlExprBuilder.buildSqlExpr(this.masterObject, orderByItem.getExpr()));
            orderByItemX.setAscending(orderByItem.isAscending());
            orderByX.addItem(orderByItemX);
        }

        return orderByX;
    }

    /**
     * 解析查询模型的字段，select field from object
     *
     * @param fieldExpr
     * @param alias
     */
    private void parseSelectFieldExpr(OqlFieldExpr fieldExpr, String alias) {
        XField field = fieldExpr.getResolvedField();
        this.addFieldToStmt(field, alias);
    }

    /**
     * 解析查询关联模型的字段，select lookupField.x, detailField.x, masterField.x from object
     *
     * @param objectExpandExpr
     * @param alias
     */
    private void parseSelectObjectExpandExpr(OqlObjectExpandExpr objectExpandExpr, String alias) {
        XObject refObject = objectExpandExpr.getResolvedRefObject();
        List<OqlExpr> expandFields = objectExpandExpr.getFields();
        if (objectExpandExpr.isStarExpanded()) {
            this.addObjectAllFieldsToStmt(refObject);
        } else {
            // 通过getStmtByObject来生成引用表的SqlSelectStatement
            this.getStmtByObject(refObject);
            for (OqlExpr expandField : expandFields) {
                OqlSelectItem refSelectItem;
                if (expandField instanceof SqlSelectItem) {
                    SqlSelectItem selectItem = (SqlSelectItem) expandField;
                    refSelectItem = new OqlSelectItem(selectItem.getExpr(), selectItem.getAlias());
                } else {
                    refSelectItem = new OqlSelectItem(expandField);
                }
                this.parseSelectItem(refObject, refSelectItem);
            }
            if (alias != null) {
                this.aliasMap.put(refObject, alias);
            }
        }
    }

    /**
     * 解析普通的非字段类表达式
     *
     * @param thisObject
     * @param expr
     * @param alias
     */
    private void parseExpr(XObject thisObject, SqlExpr expr, String alias) {
        SqlExpr exprX = this.sqlExprBuilder.buildSqlExpr(thisObject, expr);
        SqlSelectStatement stmt = this.getStmtByObject(thisObject);
        List<FieldMapping> fieldMappings = this.getFieldMappingsByObject(thisObject);
        SqlSelect select = stmt.getSelect();
        String itemAlias = "_" + nonFieldSelectItemIndex++;
        select.addSelectItem(new SqlSelectItem(exprX, itemAlias));

        String columnName = OqlUtils.expr2String(expr);
        String name = alias != null ? alias : columnName;
        FieldMapping fieldMapping = new FieldMapping(name, itemAlias);
        fieldMappings.add(fieldMapping);
    }

    /**
     * 将一个模型的全部字段添加到模型对应的查询语句的字段中
     *
     * @param object
     */
    private void addObjectAllFieldsToStmt(XObject object) {
        List<XField> fields = object.getFields();
        for (XField field : fields) {
            if (field instanceof XObjectRefField && ((XObjectRefField) field).getRefType() == ObjectRefType.DETAIL) {
                continue;
            }
            this.addFieldToStmt(field, null);
        }
    }

    /**
     * 将一个模型的字段添加到模型对应的查询语句的字段中
     *
     * @param field
     * @param alias
     * @return
     */
    private void addFieldToStmt(XField field, String alias) {
        XObject thisObject = field.getOwner();
        SqlSelectStatement stmt = this.getStmtByObject(thisObject);
        List<FieldMapping> fieldMappings = this.getFieldMappingsByObject(thisObject);
        SqlSelect select = stmt.getSelect();

        String name = alias != null ? alias : field.getName();
        FieldMapping fieldMapping = new FieldMapping(name, field.getColumnName());
        fieldMapping.setValueType(new ValueTypeImpl(field.getDataType(), field.isArray()));
        fieldMappings.add(fieldMapping);

        List<XProperty> properties = field.getProperties();
        if (properties.size() > 0) {
            List<FieldMapping> subFieldMappings = new ArrayList<>();
            for (XProperty property : properties) {
                SqlIdentifierExpr identExpr = new SqlIdentifierExpr(property.getColumnName());
                SqlSelectItem selectItem = new SqlSelectItem(identExpr);
                select.addSelectItem(selectItem);
                FieldMapping subFieldMapping = new FieldMapping(property.getName(), property.getColumnName());
                subFieldMapping.setValueType(new ValueTypeImpl(property.getDataType(), property.isArray()));
                subFieldMappings.add(subFieldMapping);
            }
            fieldMapping.addSubFields(subFieldMappings);
        } else {
            SqlIdentifierExpr identExpr = new SqlIdentifierExpr(field.getColumnName());
            identExpr.setAutoGen(field.isAutoGen());
            SqlSelectItem selectItem = new SqlSelectItem(identExpr);
            select.addSelectItem(selectItem);
        }
    }

    /**
     * 根据模型获取它对应的查询语句
     *
     * @param object
     * @return
     */
    private SqlSelectStatement getStmtByObject(XObject object) {
        SqlSelectStatement stmt = this.stmtMap.get(object);
        if (stmt == null) {
            SqlSelect select = new SqlSelect();
            select.setFrom(new SqlExprTableSource(object.getTableName()));
            stmt = new SqlSelectStatement(select);
            this.stmtMap.put(object, stmt);
        }
        return stmt;
    }

    /**
     * 根据模型获取它对应的查询映射表
     *
     * @param object
     * @return
     */
    private List<FieldMapping> getFieldMappingsByObject(XObject object) {
        List<FieldMapping> fieldMappings = this.fieldMappingsMap.get(object);
        if (fieldMappings == null) {
            fieldMappings = new ArrayList<>();
            this.fieldMappingsMap.put(object, fieldMappings);
        }
        return fieldMappings;
    }

}
