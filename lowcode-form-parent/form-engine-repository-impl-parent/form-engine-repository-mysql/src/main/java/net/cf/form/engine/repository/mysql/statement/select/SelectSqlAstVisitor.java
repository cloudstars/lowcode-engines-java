package net.cf.form.engine.repository.mysql.statement.select;

import net.cf.form.engine.repository.data.*;
import net.cf.form.engine.repository.mysql.statement.AbstractSqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlValuableExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlAllFieldExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlObjectExpandExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlPropertyExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelect;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectItem;
import net.cf.form.engine.repository.oql.parser.Token;
import net.cf.form.engine.repository.sql_bak.SelectDetailSqlInfo;
import net.cf.form.engine.repository.sql_bak.SelectSqlInfo;
import net.cf.form.engine.repository.data.*;

import java.util.ArrayList;
import java.util.List;

/**
 * MyOql select语句输出 AST访问器
 *
 * @author clouds
 */
public final class SelectSqlAstVisitor extends AbstractSqlAstVisitor<SelectSqlInfo> {

    public SelectSqlAstVisitor(DataObjectResolver resolver) {
        this(resolver, false);
    }

    public SelectSqlAstVisitor(DataObjectResolver resolver, boolean parameterized) {
        super(resolver, parameterized);
    }

    @Override
    public boolean visit(OqlSelect x) {
        OqlObjectSource objectSource = x.getFrom();
        this.resolveObjectSource(objectSource);

        this.sqlInfo = new SelectSqlInfo(this.object);
        List<OqlSelectItem> selectItems = x.getSelectItems();
        for (int i = 0, l = selectItems.size(); i < l; i++) {
            OqlSelectItem selectItem = selectItems.get(i);
            this.processSelectItem(selectItem);
        }
        this.sqlInfo.setWhereClause(x.getWhere());
        this.sqlInfo.setGroupBy(x.getGroupBy());
        this.sqlInfo.setOrderBy(x.getOrderBy());
        this.sqlInfo.setLimit(x.getLimit());

        return false;
    }

    /**
     * 处理SelectItem元数
     *
     * @param x
     * @return
     */
    public void processSelectItem(OqlSelectItem x) {
        QqlExpr expr = x.getExpr();
        String alias = x.getAlias();

        if (expr instanceof OqlAllFieldExpr) {
            this.addAllSelfFieldsToOqlInfo();
        } else if (expr instanceof OqlObjectExpandExpr) {
            String objectFieldName = ((OqlObjectExpandExpr) expr).getObjectFieldName();
            DataField objectField = this.object.getField(objectFieldName);
            if (objectField instanceof DetailDataField) {
                this.processDetailFieldExpand((OqlObjectExpandExpr) expr, alias);
            } else if (objectField instanceof MasterDataField) {
                this.processMasterFieldExpand((OqlObjectExpandExpr) expr, alias);
            } else if (objectField instanceof LookupDataField) {
                if (!objectField.getDataType().isMultiple()) {
                    this.processOne2OneLookupFieldExpand((OqlObjectExpandExpr) expr, alias);
                } else {
                    this.processOne2ManyLookupFieldExpand((OqlObjectExpandExpr) expr, alias);
                }
            }
        } else if (expr instanceof OqlPropertyExpr) {
            String objectFieldName = ((OqlPropertyExpr) expr).getOwner().getName();
            DataField objectField = this.object.getField(objectFieldName);
            String name = ((OqlPropertyExpr) expr).getName();
            if (Token.STAR.name.equalsIgnoreCase(name)) {
                if (objectField instanceof DetailDataField) {
                    this.addAllDetailFieldsToOqlInfo(objectFieldName, alias);
                } else if (objectField instanceof MasterDataField) {
                    this.addAllMasterFieldsToOqlInfo(objectFieldName, alias);
                } else if (objectField instanceof LookupDataField) {
                    if (!objectField.getDataType().isMultiple()) {
                        this.addAllLookupFieldsToOqlInfo(objectFieldName, alias);
                    } else {
                        this.addAllLookupFieldsToDetailOqlInfo(objectFieldName, alias);
                    }
                }
            } else {
                if (objectField instanceof DetailDataField) {
                    this.processDetailFieldExpandOne((OqlPropertyExpr) expr, alias);
                } else if (objectField instanceof MasterDataField) {
                    this.processMasterFieldExpandOne((OqlPropertyExpr) expr, alias);
                } else if (objectField instanceof LookupDataField) {
                    if (!objectField.getDataType().isMultiple()) {
                        this.processOne2OneLookupFieldExpandOne((OqlPropertyExpr) expr, alias);
                    } else {
                        this.processOne2ManyLookupFieldExpandOne((OqlPropertyExpr) expr, alias);
                    }
                }
            }
        } else {
            this.sqlInfo.addSelfSelectItem(x);
        }
    }

    /**
     * 将本对象下的全部本表字段添加到SQL信息中
     */
    private void addAllSelfFieldsToOqlInfo() {
        List<OqlSelectItem> selfSelectItems = this.collectObjectSelfSelectAllItems(this.object);
        this.sqlInfo.addSelfSelectItems(selfSelectItems);
    }

    /**
     * 将子对象下的全部字段添加到SQL信息中
     */
    private void addAllDetailFieldsToOqlInfo(String detailFieldName, String alias) {
        DataObject detailObject = this.resolveFieldObject(detailFieldName);
        List<OqlSelectItem> detailSelectItems = collectObjectSelfSelectAllItems(detailObject);
        OqlListExpr listExpr = new OqlListExpr();
        for (OqlSelectItem detailSelectItem : detailSelectItems) {
            listExpr.addItem(detailSelectItem);
        }
        this.sqlInfo.addDetailSelectItems(detailFieldName, detailObject, listExpr, alias);
    }

    /**
     * 将本对象下的全部本表字段添加到SQL信息中
     */
    private void addAllMasterFieldsToOqlInfo(String masterFieldName, String alias) {
        DataObject masterObject = this.resolveFieldObject(masterFieldName);
        List<OqlSelectItem> masterSelectItems = collectObjectSelfSelectAllItems(masterObject);
        OqlListExpr listExpr = new OqlListExpr();
        for (OqlSelectItem masterSelectItem : masterSelectItems) {
            listExpr.addItem(masterSelectItem);
        }
        this.sqlInfo.addMasterSelectItems(masterFieldName, masterObject, listExpr, alias);
    }

    /**
     * 将本对象下的全部本表字段添加到SQL信息中
     */
    private void addAllLookupFieldsToOqlInfo(String lookupFieldName, String alias) {
        DataObject lookupObject = this.resolveFieldObject(lookupFieldName);
        List<OqlSelectItem> lookupSelectItems = collectObjectSelfSelectAllItems(lookupObject);
        OqlListExpr listExpr = new OqlListExpr();
        for (OqlSelectItem lookupSelectItem : lookupSelectItems) {
            listExpr.addItem(lookupSelectItem);
        }
        this.sqlInfo.addLookupSelectItems(lookupFieldName, lookupObject, listExpr, alias);
    }

    /**
     * 将本对象下的全部本表字段添加到主查询的SQL信息中
     */
    private void addAllLookupFieldsToDetailOqlInfo(String lookupFieldName, String alias) {
        DataObject lookupObject = this.resolveFieldObject(lookupFieldName);
        List<OqlSelectItem> lookupSelectItems = collectObjectSelfSelectAllItems(lookupObject);
        OqlListExpr listExpr = new OqlListExpr();
        for (OqlSelectItem lookupSelectItem : lookupSelectItems) {
            listExpr.addItem(lookupSelectItem);
        }

        SelectDetailSqlInfo detailSqlInfo = new SelectDetailSqlInfo(lookupObject, this.sqlInfo);
        detailSqlInfo.setAlias(alias);
        detailSqlInfo.addSelfSelectItems(lookupSelectItems);
        this.sqlInfo.addDetailSqlInfo(lookupFieldName, detailSqlInfo);
    }


    /**
     * 收集一个对象的全部字段
     *
     * @param object
     * @return
     */
    private List<OqlSelectItem> collectObjectSelfSelectAllItems(DataObject object) {
        List<DataField> fields = object.getFields();
        List<OqlSelectItem> selectItems = new ArrayList<>();
        for (DataField field : fields) {
            if (!(field instanceof DetailDataField)) {
                OqlSelectItem selectItem = new OqlSelectItem();
                selectItem.setExpr(new OqlIdentifierExpr(field.getName()));
                // selectItem.setAlias(field.getName());
                selectItems.add(selectItem);
            }
        }

        return selectItems;
    }


    /**
     * 收集一个对象的全部字段
     *
     * @param objectExpandExpr
     * @return
     */
    private List<OqlSelectItem> collectObjectSelectExpandItems(OqlObjectExpandExpr objectExpandExpr) {
        String objectFieldName = objectExpandExpr.getObjectFieldName();
        DataObject object = this.resolveFieldObject(objectFieldName);
        List<QqlExpr> fieldExprs = objectExpandExpr.getFields();
        List<OqlSelectItem> selectItems = new ArrayList<>();
        for (QqlExpr fieldExpr : fieldExprs) {
            OqlSelectItem selectItem = newSelectItem(object, fieldExpr);
            selectItems.add(selectItem);
        }

        return selectItems;
    }

    /**
     * 处理子表展开字段
     *
     * @param objectExpandExpr
     * @param alias
     */
    private void processDetailFieldExpand(OqlObjectExpandExpr objectExpandExpr, String alias) {
        String objectFieldName = objectExpandExpr.getObjectFieldName();
        DataObject detailObject = this.resolveFieldObject(objectFieldName);
        List<QqlExpr> detailFields = objectExpandExpr.getFields();
        OqlListExpr listExpr = new OqlListExpr();
        for (QqlExpr detailField : detailFields) {
            OqlSelectItem selectItem;
            if (detailField instanceof OqlIdentifierExpr) {
                selectItem = newSelectItem(detailObject, (OqlIdentifierExpr) detailField);
            } else if (detailField instanceof QqlValuableExpr) {
                selectItem = newSelectItem(detailObject, (QqlValuableExpr) detailField);
            } else {
                throw new RuntimeException("unsupported detail field:" + detailField);
            }
            listExpr.addItem(selectItem);
        }
        this.sqlInfo.addDetailSelectItems(objectFieldName, detailObject, listExpr, alias);
    }

    /**
     * 处理子表展开字段
     *
     * @param objectExpandExpr
     * @param alias
     */
    private void processMasterFieldExpand(OqlObjectExpandExpr objectExpandExpr, String alias) {
        String objectFieldName = objectExpandExpr.getObjectFieldName();
        DataObject masterObject = this.resolveFieldObject(objectFieldName);
        List<QqlExpr> masterFields = objectExpandExpr.getFields();
        OqlListExpr listExpr = new OqlListExpr();
        for (QqlExpr masterField : masterFields) {
            OqlSelectItem selectItem;
            if (masterField instanceof OqlIdentifierExpr) {
                selectItem = newSelectItem(masterObject, (OqlIdentifierExpr) masterField);
            } else if (masterField instanceof QqlValuableExpr) {
                selectItem = newSelectItem((QqlValuableExpr) masterField);
            } else {
                throw new RuntimeException("unsupported master field:" + masterField);
            }
            listExpr.addItem(selectItem);
        }
        this.sqlInfo.addMasterSelectItems(objectFieldName, masterObject, listExpr, alias);
    }

    /**
     * 处理相关表（一对一）展开字段
     *
     * @param objectExpandExpr
     * @param alias
     */
    private void processOne2OneLookupFieldExpand(OqlObjectExpandExpr objectExpandExpr, String alias) {
        String objectFieldName = objectExpandExpr.getObjectFieldName();
        DataObject lookupObject = this.resolveFieldObject(objectFieldName);
        List<QqlExpr> lookupFields = objectExpandExpr.getFields();
        OqlListExpr listExpr = new OqlListExpr();
        for (QqlExpr lookupField : lookupFields) {
            OqlSelectItem selectItem = newSelectItem(lookupObject, lookupField);
            listExpr.addItem(selectItem);
        }
        this.sqlInfo.addLookupSelectItems(objectFieldName, lookupObject, listExpr, alias);
    }

    /**
     * 处理相关表（一对多）展开字段
     *
     * @param objectExpandExpr
     * @param alias
     */
    private void processOne2ManyLookupFieldExpand(OqlObjectExpandExpr objectExpandExpr, String alias) {
        String objectFieldName = objectExpandExpr.getObjectFieldName();
        DataObject lookupObject = this.resolveFieldObject(objectFieldName);

        // 当成子查询处理
        SelectDetailSqlInfo detailSqlInfo = this.sqlInfo.getDetailSqlInfo(objectFieldName);
        if (detailSqlInfo == null) {
            detailSqlInfo = new SelectDetailSqlInfo(lookupObject, this.sqlInfo);
            this.sqlInfo.addDetailSqlInfo(objectFieldName, detailSqlInfo);
        }

        List<OqlSelectItem> selectItems = this.collectObjectSelectExpandItems(objectExpandExpr);
        detailSqlInfo.addSelfSelectItems(selectItems);
    }


    /**
     * 处理子表展开字段（单个，如detail.xx）
     *
     * @param propertyExpr
     * @param alias
     */
    private void processDetailFieldExpandOne(OqlPropertyExpr propertyExpr, String alias) {
        String objectFieldName = propertyExpr.getOwner().getName();
        DataObject detailObject = this.resolveFieldObject(objectFieldName);
        DataField detailField = detailObject.getField(propertyExpr.getName());
        assert (detailField != null);

        OqlListExpr listExpr = new OqlListExpr();
        listExpr.addItem(new OqlSelectItem(new OqlIdentifierExpr(detailField.getColumnName()), detailField.getName()));
        this.sqlInfo.addDetailSelectItems(objectFieldName, detailObject, listExpr, alias);
    }

    /**
     * 处理主表展开字段（单个，如master.xx）
     *
     * @param propertyExpr
     * @param alias
     */
    private void processMasterFieldExpandOne(OqlPropertyExpr propertyExpr, String alias) {
        String objectFieldName = propertyExpr.getOwner().getName();
        DataObject masterObject = this.resolveFieldObject(objectFieldName);
        DataField masterField = masterObject.getField(propertyExpr.getName());
        assert (masterField != null);

        OqlListExpr listExpr = new OqlListExpr();
        listExpr.addItem(new OqlSelectItem(new OqlIdentifierExpr(masterField.getColumnName()), masterField.getName()));
        this.sqlInfo.addMasterSelectItems(objectFieldName, masterObject, listExpr, alias);
    }

    /**
     * 处理关联对象（一对一）展开字段（单个，如master.xx）
     *
     * @param propertyExpr
     * @param alias
     */
    private void processOne2OneLookupFieldExpandOne(OqlPropertyExpr propertyExpr, String alias) {
        String objectFieldName = propertyExpr.getOwner().getName();
        DataObject lookupObject = this.resolveFieldObject(objectFieldName);
        DataField lookupField = lookupObject.getField(propertyExpr.getName());
        assert (lookupField != null);

        OqlListExpr listExpr = new OqlListExpr();
        listExpr.addItem(new OqlSelectItem(new OqlIdentifierExpr(lookupField.getColumnName()), lookupField.getName()));
        this.sqlInfo.addLookupSelectItems(objectFieldName, lookupObject, listExpr, alias);
    }

    /**
     * 处理关联对象（一对多）展开字段（单个，如master.xx）
     *
     * @param propertyExpr
     * @param alias
     */
    private void processOne2ManyLookupFieldExpandOne(OqlPropertyExpr propertyExpr, String alias) {
        String objectFieldName = propertyExpr.getOwner().getName();
        DataObject lookupObject = this.resolveFieldObject(objectFieldName);
        String propertyName = propertyExpr.getName();
        DataField lookupField = lookupObject.getField(propertyName);
        assert (lookupField != null);

        // 当成子查询处理
        SelectDetailSqlInfo detailSqlInfo = this.sqlInfo.getDetailSqlInfo(objectFieldName);
        if (detailSqlInfo == null) {
            detailSqlInfo = new SelectDetailSqlInfo(lookupObject, this.sqlInfo);
            this.sqlInfo.addDetailSqlInfo(objectFieldName, detailSqlInfo);
        }

        detailSqlInfo.addSelfSelectItem(newSelectItem(lookupObject, new OqlIdentifierExpr(propertyName)));
    }

    /**
     * 根据OQL表达式构建一个SelectItem
     *
     * @param object
     * @param expr
     * @return
     */
    private OqlSelectItem newSelectItem(DataObject object, QqlExpr expr) {
        OqlSelectItem selectItem;
        if (expr instanceof OqlIdentifierExpr) {
            selectItem = newSelectItem(object, (OqlIdentifierExpr) expr);
        } else if (expr instanceof QqlValuableExpr) {
            selectItem = newSelectItem((QqlValuableExpr) expr);
        } else {
            throw new RuntimeException("unsupported select field:" + expr);
        }

        return selectItem;
    }

    /**
     * 根据对象的表达式构建一个SelectItem
     *
     * @param object
     * @param expr
     * @return
     */
    private OqlSelectItem newSelectItem(DataObject object, OqlIdentifierExpr expr) {
        DataField field = object.getField(expr.getName());
        assert (field != null);

        OqlSelectItem selectItem = new OqlSelectItem();
        selectItem.setExpr(new OqlIdentifierExpr(field.getColumnName()));
        selectItem.setAlias(field.getName());
        return selectItem;
    }

    /**
     * 根据一个有值的表达式构建一个SelectItem
     *
     * @param expr
     * @return
     */
    private OqlSelectItem newSelectItem(QqlValuableExpr expr) {
        OqlSelectItem selectItem = new OqlSelectItem();
        selectItem.setExpr(expr);
        selectItem.setAlias(expr.getValue().toString());
        return selectItem;
    }
}
