package net.cf.object.engine.oql.stmt;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.form.repository.sql.parser.Token;
import net.cf.form.repository.sql.util.SqlStatementUtils;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.object.*;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.parser.XObjectResolver;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OQL查询语句解析器
 * <p>
 * 职责：用于将一条OQL语句解析成本表的插入与子表的插入
 */
public class OqlSelectStatementParser extends AbstractOqStatementParser<OqlSelectStatement> {

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

    public OqlSelectStatementParser(XObjectResolver resolver, String oql) {
        super(resolver, oql, false);
    }

    public OqlSelectStatementParser(XObjectResolver resolver, String oql, boolean isBatch) {
        super(resolver, oql, isBatch);
    }

    /**
     * 解析成OQL查询语句
     *
     * @return
     */
    public OqlSelectStatement parse() {
        // 先将OQL解析为SQL，之后再转换为OQL的本表查询、子表查询、相关表查询
        SqlSelectStatement sqlStmt = SqlStatementUtils.parseSingleSelectStatement(oql);

        // 解析当前语句的本模型
        SqlSelect select = sqlStmt.getSelect();
        SqlExprTableSource tableSource = (SqlExprTableSource) select.getFrom();
        String objectName = tableSource.getName().getName();
        this.selfObject = this.resolveObject(objectName);

        // 获取本模型对应的SQL语句
        SqlSelectStatement selfStmt = this.getStmtByObject(this.selfObject);
        SqlSelect selfSelect = selfStmt.getSelect();

        // 解析查询字段
        List<SqlSelectItem> selectItems = select.getSelectItems();
        this.parseSelectItems(this.selfObject, selectItems);

        // 解析where条件
        SqlExpr where = select.getWhere();
        if (where != null) {
            OqlWhereExprParser whereExprParser = new OqlWhereExprParser(this.resolver, this.selfObject);
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
        OqlSelectStatement oqlStmt = new OqlSelectStatement();
        oqlStmt.setResolvedSelfObject(this.selfObject);
        for (Map.Entry<XObject, SqlSelectStatement> entry : stmtMap.entrySet()) {
            XObject thisObject = entry.getKey();
            OqlSelectInfo selectInfo = new OqlSelectInfo();
            selectInfo.setResolvedObject(thisObject);
            SqlSelectStatement stmt = this.stmtMap.get(thisObject);
            if (thisObject == this.selfObject) {
                oqlStmt.setSelfSelectInfo(selectInfo);
            } else {
                XObjectRefField objectRefField = this.selfObject.getObjectRefField(thisObject.getName());
                selectInfo.setObjectRefFieldName(objectRefField.getName());
                selectInfo.setObjectRefFieldAlias(this.aliasMap.get(thisObject));
                SqlExpr whereExpr;
                if (objectRefField.getRefType() == ObjectRefType.DETAIL) {
                    oqlStmt.addDetailSelectInfo(selectInfo);
                    if (stmt.getSelect().getSelectItems().size() == 0) {
                        selectInfo.setDetailFieldDirectQuery(true);
                        // 如果只查询了子表字段，默认会返回子表的ID数组
                        this.addFieldToStmt(thisObject.getPrimaryField(), null);
                    }
                    // 默认查询子表的masterField字段
                    XObjectRefField detailMasterField = thisObject.getObjectRefField(selfObject.getName());
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
                    oqlStmt.addLookupSelectInfo(selectInfo);
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

        return oqlStmt;
    }

    /**
     * 解析模型下的一组查询字段
     *
     * @param thisObject
     * @param selectItems
     */
    private void parseSelectItems(XObject thisObject, List<SqlSelectItem> selectItems) {
        for (SqlSelectItem selectItem : selectItems) {
            this.parseSelectItem(thisObject, selectItem);
        }
    }

    /**
     * 解析模型下的单个查询字段
     *
     * @param thisObject
     * @param selectItem
     */
    private void parseSelectItem(XObject thisObject, SqlSelectItem selectItem) {
        SqlExpr selectItemExpr = selectItem.getExpr();
        String alias = selectItem.getAlias();
        if (selectItemExpr instanceof SqlAllColumnExpr) {
            this.addObjectAllFieldsToStmt(thisObject);
        } else if (selectItemExpr instanceof SqlIdentifierExpr) {
            this.parseSelectIdentifierExpr(thisObject, (SqlIdentifierExpr) selectItemExpr, alias);
        } else if (selectItemExpr instanceof SqlPropertyExpr) {
            this.parseSelectPropertyField(thisObject, (SqlPropertyExpr) selectItemExpr, alias);
        } else if (selectItemExpr instanceof SqlAggregateExpr) {
            this.parseAggregateExpr(thisObject, (SqlAggregateExpr) selectItemExpr, alias);
        } else if (selectItemExpr instanceof SqlMethodInvokeExpr) {
            this.parseSelectMethodInvokeExpr(thisObject, (SqlMethodInvokeExpr) selectItemExpr, alias);
        } else {
            this.addNonFieldSelectItemToStmt(thisObject, selectItemExpr, alias);
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
            SqlExpr groupByItemX = this.toRepoSelfIdentifierExpr(groupByItem, "分组");
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
            orderByItemX.setExpr(this.toRepoSelfIdentifierExpr(orderByItem.getExpr(), "排序"));
            orderByItemX.setAscending(orderByItem.isAscending());
            orderByX.addItem(orderByItemX);
        }

        return orderByX;
    }

    /**
     * 解析一个表达式为驱动层本表的一个标识符字段
     *
     * @param expr
     * @return
     */
    private SqlExpr toRepoSelfIdentifierExpr(final SqlExpr expr, final String descr) {
        SqlExpr exprX;
        if (!(expr instanceof SqlIdentifierExpr)) {
            throw new FastOqlException(descr + "字段必须是标识符！");
        }

        String fielName = ((SqlIdentifierExpr) expr).getName();
        XField resolvedField = this.resolveField(selfObject, fielName);
        if (CollectionUtils.isEmpty(resolvedField.getProperties())) {
            exprX = this.toRepoSelfExpr(resolvedField);
        } else {
            XProperty primaryProperty = resolvedField.getPrimaryProperty();
            if (primaryProperty != null) {
                exprX = this.toRepoSelfExpr(primaryProperty);
            } else {
                throw new FastOqlException(descr + "字段必须是不带属性的字段或带有主属性字段！");
            }
        }

        return exprX;
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

    /**
     * 解析查询模型的字段，select field from object
     *
     * @param thisObject
     * @param identExpr
     * @param alias
     */
    private void parseSelectIdentifierExpr(XObject thisObject, SqlIdentifierExpr identExpr, String alias) {
        String fieldName = identExpr.getName();
        XField field = this.resolveField(thisObject, fieldName);
        this.addFieldToStmt(field, alias);
    }

    /**
     * 解析查询关联模型的字段，select lookupField.x, detailField.x, masterField.x from object
     *
     * @param thisObject
     * @param propExpr
     * @param alias
     */
    private void parseSelectPropertyField(XObject thisObject, SqlPropertyExpr propExpr, String alias) {
        String objectRefFieldName = propExpr.getOwner().getName();
        XObjectRefField objectRefField = this.resolveObjectRefField(thisObject, objectRefFieldName);
        String refObjectName = objectRefField.getRefObjectName();
        XObject refObject = this.resolveObject(refObjectName);
        String fieldName = propExpr.getName();
        String propName = propExpr.getName();
        if (Token.STAR.name.equals(propName)) {
            this.addObjectAllFieldsToStmt(refObject);
        } else {
            XField field = this.resolveField(refObject, fieldName);
            this.addFieldToStmt(field, alias);
        }
    }

    /**
     * 解析聚合函数的字段，select count(*)、max(field1) from object
     *
     * @param thisObject
     * @param aggregateExpr
     * @param alias
     */
    private void parseAggregateExpr(XObject thisObject, SqlAggregateExpr aggregateExpr, String alias) {
        // 方法调用，TODO 开发态控制模型的名称不能与聚合函数名称相同
        SqlAggregateExpr sqlAggregateExpr = new SqlAggregateExpr();
        this.parseMethodInvoke(thisObject, aggregateExpr, alias, sqlAggregateExpr);
    }

    /**
     * 解析方法调用的字段，select ltrim(f), lookupField(f1, 1 as x, max(f2)) as xxx from object
     *
     * @param thisObject
     * @param methodInvokeExpr
     * @param alias
     */
    private void parseSelectMethodInvokeExpr(XObject thisObject, SqlMethodInvokeExpr methodInvokeExpr, String alias) {
        String methodName = methodInvokeExpr.getMethodName();
        List<SqlExpr> args = methodInvokeExpr.getArguments();
        if (METHOD_NAMES.contains(methodName)) {
            // 方法调用，TODO 开发态控制模型的名称不能与方法名称相同
            SqlMethodInvokeExpr sqlMethodInvokeExpr = new SqlMethodInvokeExpr();
            this.parseMethodInvoke(thisObject, methodInvokeExpr, alias, sqlMethodInvokeExpr);
        } else {
            // 其它模型引用
            XObjectRefField objectRefField = this.resolveObjectRefField(selfObject, methodName);
            XObject resolvedObject = this.resolveObject(objectRefField.getRefObjectName());
            for (SqlExpr arg : args) {
                SqlSelectItem refSelectItem;
                if (arg instanceof SqlSelectItem) {
                    refSelectItem = (SqlSelectItem) arg;
                } else {
                    refSelectItem = new SqlSelectItem(arg);
                }
                this.parseSelectItem(resolvedObject, refSelectItem);
            }
            if (alias != null) {
                this.aliasMap.put(resolvedObject, alias);
            }
        }
    }

    /**
     * 解析一个方法调用字段，并添加到模型对应的SQL语句中
     *
     * @param thisObject
     * @param methodInvokeExpr
     * @param alias
     * @param sqlMethodInvokeExpr
     */
    private void parseMethodInvoke(XObject thisObject, SqlMethodInvokeExpr methodInvokeExpr, String alias, SqlMethodInvokeExpr sqlMethodInvokeExpr) {
        String methodName = methodInvokeExpr.getMethodName();
        List<SqlExpr> args = methodInvokeExpr.getArguments();
        sqlMethodInvokeExpr.setMethodName(methodName);
        for (SqlExpr arg : args) {
            if (arg instanceof SqlIdentifierExpr) {
                SqlIdentifierExpr identExpr = this.toRepoSqlIdentifierExpr(thisObject, (SqlIdentifierExpr) arg);
                sqlMethodInvokeExpr.addArgument(identExpr);
            } else {
                sqlMethodInvokeExpr.addArgument(arg);
            }
        }

        String sqlAlias = alias == null ? methodInvokeExpr.toString() : alias;
        this.addNonFieldSelectItemToStmt(thisObject, sqlMethodInvokeExpr, sqlAlias);
    }

    /**
     * 将一个模型的全部字段添加到模型对应的查询语句的字段中
     *
     * @param object
     */
    private void addObjectAllFieldsToStmt(XObject object) {
        List<XField> fields = object.getFields();
        for (XField field : fields) {
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
        if (field instanceof XObjectRefField && ((XObjectRefField) field).getRefType() == ObjectRefType.DETAIL) {
            // 特殊情况处理，只查询子表字段未展开时，默认返回子表的ID
            String detailObjectName = ((XObjectRefField) field).getRefObjectName();
            XObject detailObject = this.resolveObject(detailObjectName);
            this.getStmtByObject(detailObject);
            if (alias != null) {
                this.aliasMap.put(detailObject, alias);
            }
            return;
        }

        XObject thisObject = field.getOwner();
        SqlSelectStatement stmt = this.getStmtByObject(thisObject);
        List<FieldMapping> fieldMappings = this.getFieldMappingsByObject(thisObject);
        SqlSelect select = stmt.getSelect();

        String name = alias != null ? alias : field.getName();
        FieldMapping fieldMapping = new FieldMapping(name, field.getColumnName());
        fieldMapping.setValueType(new ValueType(field.getDataType(), field.isArray()));
        fieldMappings.add(fieldMapping);

        List<XProperty> properties = field.getProperties();
        if (properties.size() > 0) {
            List<FieldMapping> subFieldMappings = new ArrayList<>();
            for (XProperty property : properties) {
                SqlIdentifierExpr identExpr = new SqlIdentifierExpr(property.getColumnName());
                SqlSelectItem selectItem = new SqlSelectItem(identExpr);
                select.addSelectItem(selectItem);
                FieldMapping subFieldMapping = new FieldMapping(property.getName(), property.getColumnName());
                subFieldMapping.setValueType(new ValueType(property.getDataType(), property.isArray()));
                subFieldMappings.add(subFieldMapping);
            }
            fieldMapping.addSubFields(subFieldMappings);
        } else {
            SqlIdentifierExpr identExpr = new SqlIdentifierExpr(field.getColumnName());
            SqlSelectItem selectItem = new SqlSelectItem(identExpr);
            select.addSelectItem(selectItem);
        }
    }

    /**
     * 添加一个非字段类型时查询字段到模型对应的查询语句的字段中，如：常量、方法调用等
     *
     * @param thisObject
     * @param selectItemExpr
     * @param alias
     */
    private void addNonFieldSelectItemToStmt(XObject thisObject, SqlExpr selectItemExpr, String alias) {
        SqlSelectStatement stmt = this.getStmtByObject(thisObject);
        List<FieldMapping> fieldMappings = this.getFieldMappingsByObject(thisObject);
        SqlSelect select = stmt.getSelect();
        select.addSelectItem(new SqlSelectItem(selectItemExpr));

        String columnName = selectItemExpr.toString();
        String name = alias != null ? alias : columnName;
        FieldMapping fieldMapping = new FieldMapping(name, columnName);
        fieldMappings.add(fieldMapping);
    }

    /**
     * 将标识符转换为驱动层的标识符
     *
     * @param identExpr
     */
    private SqlIdentifierExpr toRepoSqlIdentifierExpr(XObject thisObject, SqlIdentifierExpr identExpr) {
        String fieldName = identExpr.getName();
        XField field = this.resolveField(thisObject, fieldName);
        return new SqlIdentifierExpr(field.getColumnName());
    }
}
