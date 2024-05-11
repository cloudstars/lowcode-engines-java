package net.cf.object.engine.oql.infos;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.object.engine.object.ObjectRefType;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.sql.SqlSelectCmd;
import net.cf.object.engine.sql.SqlSelectCmdBuilder;
import net.cf.object.engine.util.OqlUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OQL查询语句解析器
 * <p>
 * 职责：用于将一条OQL查询语句解析成本表的查询、子表的查询、相关表的查询
 */
public class OqlSelectInfosParser extends AbstractOqInfosParser<OqlSelectStatement, OqlSelectInfos> {

    /**
     * 查询参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 模型关联的查询语句
     */
    private final Map<XObject, OqlSelectStatement> objectSelectStmtMap = new HashMap<>();

    /**
     * 模型关联的别名，如select refField(f1, f2) as xxx，xxx就是refField对应模型的别名
     */
    private final Map<XObject, String> objectAliasMap = new HashMap<>();

    public OqlSelectInfosParser(OqlSelectStatement stmt, boolean isQueryList) {
        super(stmt, isQueryList);
        this.paramMap = null;
    }

    public OqlSelectInfosParser(OqlSelectStatement stmt, Map<String, Object> paramMap, boolean isQueryList) {
        super(stmt, isQueryList);
        this.paramMap = paramMap;
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

        // 获取本模型对应的OQL语句
        OqlSelectStatement masterStmt = this.getStmtByObject(this.masterObject);
        OqlSelect masterSelect = masterStmt.getSelect();

        // 解析查询字段
        List<OqlSelectItem> selectItems = select.getSelectItems();
        for (OqlSelectItem selectItem : selectItems) {
            SqlExpr selectItemExpr = selectItem.getExpr();
            String alias = selectItem.getAlias();
            if (selectItemExpr instanceof OqlObjectExpandExpr) {
                this.parseSelectObjectExpandExpr((OqlObjectExpandExpr) selectItemExpr, alias);
            } else {
                masterSelect.addSelectItem(selectItem);
            }
        }

        // 解析模型
        masterSelect.setFrom(select.getFrom());
        // 解析where条件
        masterSelect.setWhere(select.getWhere());
        // 解析分组
        masterSelect.setGroupBy(select.getGroupBy());
        // 解析排序
        masterSelect.setOrderBy(select.getOrderBy());
        // 解析限制行数
        masterSelect.setLimit(select.getLimit());

        return this.buildSelectInfos();
    }

    /**
     * 构建OQL查询信息
     */
    private OqlSelectInfos buildSelectInfos() {
        OqlSelectInfos selectInfos = new OqlSelectInfos();
        selectInfos.setResolvedMasterObject(this.masterObject);
        for (Map.Entry<XObject, OqlSelectStatement> entry : objectSelectStmtMap.entrySet()) {
            XObject thisObject = entry.getKey();
            OqlSelectStatement thisStmt = entry.getValue();
            if (thisObject == this.masterObject) {
                continue;
            }

            XObjectRefField objectRefField = this.masterObject.getObjectRefField(thisObject.getName());
            SqlExpr whereExpr;
            boolean refIsBatch = true;
            if (objectRefField.getRefType() == ObjectRefType.DETAIL) {
                // 当存在子表时，默认查询主表的主键（用于主表归集子表的数据）
                this.addFieldToStmt(this.masterObject.getPrimaryField());

                // 当存在子表时，默认查询子表的masterField字段（用于主表归集子表的数据）
                XObjectRefField detailMasterField = thisObject.getObjectRefField(masterObject.getName());
                this.addFieldToStmt(detailMasterField);

                if (this.isBatch) {
                    // where masterField in (#{masterFields})
                    whereExpr = OqlUtils.buildFieldInListVarRefExpr(detailMasterField);
                } else {
                    // where masterField = #{masterField}
                    whereExpr = OqlUtils.buildFieldEqualsVarRefExpr(detailMasterField);
                }
            } else {
                // 默认查询本表的lookupField字段
                this.addFieldToStmt(objectRefField);
                if (this.isBatch || objectRefField.isMultiRef()) {
                    // where primaryField in (#{primaryFields})
                    whereExpr = OqlUtils.buildFieldInListVarRefExpr(thisObject.getPrimaryField());
                } else {
                    refIsBatch = false;
                    // where primaryField = #{primaryField}
                    whereExpr = OqlUtils.buildFieldEqualsVarRefExpr(thisObject.getPrimaryField());
                }
            }
            thisStmt.getSelect().setWhere(whereExpr);

            SqlSelectCmdBuilder builder = new SqlSelectCmdBuilder(thisStmt, new HashMap<>(), refIsBatch);
            SqlSelectCmd selectCmd = builder.build();
            selectCmd.setObjectRefFieldName(objectRefField.getName());
            selectCmd.setObjectRefFieldAlias(this.objectAliasMap.get(thisObject));
            if (objectRefField.getRefType() == ObjectRefType.DETAIL) {
                selectInfos.addDetailSelectInfo(selectCmd);
            } else {
                selectInfos.addLookupSelectCmd(selectCmd);
            }
        }

        // 在构建相关表时会修改本表的查询字段，故最后再构建主表
        OqlSelectStatement masterStmt = this.getStmtByObject(this.masterObject);
        SqlSelectCmdBuilder builder = new SqlSelectCmdBuilder(masterStmt, this.paramMap, this.isBatch);
        SqlSelectCmd selectCmd = builder.build();
        selectInfos.setMasterSelectCmd(selectCmd);

        return selectInfos;
    }

    /**
     * 添加一个字段到查询字段中
     *
     * @param field
     */
    private void addFieldToStmt(XField field) {
        XObject object = field.getOwner();
        OqlSelect select = this.getStmtByObject(object).getSelect();
        List<OqlSelectItem> selectItems = select.getSelectItems();
        for (OqlSelectItem selectItem : selectItems) {
            SqlExpr itemExpr = selectItem.getExpr();
            if (itemExpr instanceof OqlFieldExpr) {
                if (((OqlFieldExpr) itemExpr).getResolvedField().getName().equals(field.getName())) {
                    return;
                }
            }
        }

        select.addSelectItem(new OqlSelectItem(OqlUtils.buildFieldExpr(field)));
    }

    /**
     * 解析查询关联模型的字段，select lookupField.x, detailField.x, masterField.x from object
     *
     * @param objectExpandExpr
     * @param alias
     */
    private void parseSelectObjectExpandExpr(OqlObjectExpandExpr objectExpandExpr, String alias) {
        XObject refObject = objectExpandExpr.getResolvedRefObject();
        List<SqlExpr> expandFields = objectExpandExpr.getFields();
        OqlSelect select = this.getStmtByObject(refObject).getSelect();
        if (objectExpandExpr.isStarExpanded()) {
            select.addSelectItem(new OqlSelectItem(new SqlAllColumnExpr()));
        } else {
            for (SqlExpr expandField : expandFields) {
                OqlSelectItem refSelectItem;
                if (expandField instanceof SqlSelectItem) {
                    SqlSelectItem selectItem = (SqlSelectItem) expandField;
                    refSelectItem = new OqlSelectItem(selectItem.getExpr(), selectItem.getAlias());
                } else {
                    refSelectItem = new OqlSelectItem(expandField);
                }
                select.addSelectItem(refSelectItem);
            }
            if (alias != null) {
                this.objectAliasMap.put(refObject, alias);
            }
        }
    }

    /**
     * 根据模型获取它对应的查询语句
     *
     * @param object
     * @return
     */
    private OqlSelectStatement getStmtByObject(XObject object) {
        OqlSelectStatement stmt = this.objectSelectStmtMap.get(object);
        if (stmt == null) {
            OqlSelect select = new OqlSelect();
            select.setFrom(OqlUtils.buildObjectSource(object));
            stmt = new OqlSelectStatement(select);
            this.objectSelectStmtMap.put(object, stmt);
        }
        return stmt;
    }

}
