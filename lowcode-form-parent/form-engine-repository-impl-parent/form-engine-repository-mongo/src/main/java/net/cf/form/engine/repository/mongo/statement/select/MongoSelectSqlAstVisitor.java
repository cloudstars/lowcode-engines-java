package net.cf.form.engine.repository.mongo.statement.select;

import net.cf.form.engine.repository.data.*;
import net.cf.form.engine.repository.mongo.statement.*;
import net.cf.form.engine.repository.data.*;
import net.cf.form.engine.repository.mongo.statement.*;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlAllFieldExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlPropertyExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.QqlNameExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectItem;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.repository.oql.parser.Token;
import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class MongoSelectSqlAstVisitor extends AbstractOqlAstVisitor implements OqlAstVisitor {

    private Printer printer = new Printer();


    // 产出的收集器
    private MongoSelectInfo sqlInfo;

    /**
     * 查询对象的子对象
     */
    private List<DataObject> detailObjects = new ArrayList<>();



    public MongoSelectSqlAstVisitor(DataObjectResolver resolver) {
        super(resolver);
    }

    @Override
    public boolean visit(OqlSelectStatement x) {
        OqlObjectSource objectSource = x.getSelect().getFrom();
        if (objectSource instanceof OqlExprObjectSource) {
            String objectName = ((OqlExprObjectSource) objectSource).getExpr().getName();
            DataObject resolvedDataObject = resolver.resolveObject(objectName);
            assert (resolvedDataObject != null);
            this.resolvedDataObject = resolvedDataObject;
            ObjectContextHolder.addObject(this.resolvedDataObject);
            this.sqlInfo = new MongoSelectInfo(this.resolvedDataObject);;
        }
        QqlExpr where = x.getSelect().getWhere();
        if (where != null) {
            OqlExprAstVisitor oqlExprAstVisitor = new OqlExprAstVisitor(this.resolvedDataObject, where, this.sqlInfo);
            oqlExprAstVisitor.visit();
        }

        return true;
    }



    public MongoSelectCommand getMongoAggregation() {
        MongoSelectAggregationBuilder mongoSelectAggregationBuilder = new MongoSelectAggregationBuilder(sqlInfo);
        return mongoSelectAggregationBuilder.buildAggregation();
    }

//
//    @Override
//    public boolean visit(OqlNumberExpr x) {
//        this.newPrinter.append(new CommonVisitValue(x.getValue(), ValueType.DECIMAL));
//        return false;
//    }

//    @Override
//    public boolean visit(OqlSelect x) {
//        List<OqlSelectItem> selectItems = x.getSelectItems();
//        this.resolveSelectItemsObject(selectItems);
//        for (OqlSelectItem oqlSelectItem: selectItems) {
//            this.visit((OqlSelectItem) oqlSelectItem);
//        }
//        return false;
//    }

    private void resolveSelectItemsObject(List<OqlSelectItem> selectItems) {
        for (OqlSelectItem selectItem : selectItems) {
            QqlExpr itemExpr = selectItem.getExpr();
            if (itemExpr instanceof OqlIdentifierExpr) {
                OqlIdentifierExpr identExpr = (OqlIdentifierExpr) itemExpr;
                identExpr.setResolvedOwnerObject(this.resolvedDataObject);
            } else if (itemExpr instanceof OqlPropertyExpr) {
                OqlPropertyExpr propExpr = (OqlPropertyExpr) itemExpr;
                QqlExpr owner = propExpr.getOwner();
                if (owner instanceof OqlIdentifierExpr) {
                    String ownerName = ((OqlIdentifierExpr) owner).getName();
                    // 如果是本对象的一个字段的话，则忽略
                    if (this.resolvedDataObject.getField(ownerName) != null) {
                        propExpr.setResolvedOwnerObject(this.resolvedDataObject);
                    } else {
                        DataObject roo = resolver.resolveObject(ownerName);
                        assert (roo != null);
                        propExpr.setResolvedOwnerObject(roo);
                        // 如果是子对象，添加到子对象列表
                        if (roo.isMasterObject(this.resolvedDataObject)) {
                            this.detailObjects.add(roo);
                        }
                    }
                }
            }
        }
    }



//
//    @Override
//    public boolean visit(OqlSelectItem x) {
//        if (x.getExpr() instanceof OqlAllFieldExpr) {
//            this.visit((OqlAllFieldExpr) x.getExpr());
//        } else {
//            // todo
//            MongoSelectInfo.SelectItem selectItem = new MongoSelectInfo.SelectItem();
//            // 添加到集合
//            selectItem.setParam(this.newPrinter.print());
//            if (x.getAlias() != null) {
//                selectItem.setAlias(((TextVisitValue) this.newPrinter.print()).getValue());
//            }
//            this.sqlInfo.getParams().add(selectItem);
//        }
//        return false;
//    }



    @Override
    public boolean visit(OqlObjectSource x) {

        String tableName = null;
        QqlExpr flashback = x.getFlashback();
        if (flashback instanceof OqlIdentifierExpr) {
            String objectName = ((OqlIdentifierExpr) flashback).getName();
            DataObject dataObject = resolver.resolveObject(objectName);
            tableName = dataObject.getTableName();
        }

        if (x.getAlias() != null) {
            tableName = x.getAlias();
        }

        if (this.detailObjects.size() > 0) {
            DataField primaryField = this.resolvedDataObject.getPrimaryField();
            for (DataObject detailObject : this.detailObjects) {
                String primaryColumnName = primaryField.getColumnName();
                String detailTableName = detailObject.getTableName();
                String detailMasterFieldColumnName = detailObject.getMasterField().getColumnName();
                Object[] params = new Object[]{tableName, primaryColumnName, detailTableName, detailMasterFieldColumnName};
//                this.print(joinFormat.format(params));
            }
        }

        return false;
    }


    @Override
    public boolean visit(OqlAllFieldExpr x) {
        this.addAllDataObjectFieldsToSqlInfo(this.resolvedDataObject, this.sqlInfo);
        return false;
    }

    /**
     * 将数据对象下的全部字段（不含子对象字段）添加到SQL信息中
     *
     * @param dataObject
     * @param sqlInfo
     */
    private void addAllDataObjectFieldsToSqlInfo(DataObject dataObject, MongoSelectInfo sqlInfo){
        List<DataField> fields = dataObject.getFields();
        for (DataField field : fields) {
            if (!(field instanceof DetailDataField)) {
                MongoSelectInfo.SelectItem selectItem = new MongoSelectInfo.SelectItem();
                selectItem.setParam(new TextVisitValue(field.getName()));
                selectItem.setAlias(field.getName());
                sqlInfo.getParams().add(selectItem);
            }
        }
    }



    @Override
    public boolean visit(OqlPropertyExpr x) {
        QqlNameExpr owner = x.getOwner();
        String ownerName = owner.getName();
        DataField field = this.resolvedDataObject.getField(ownerName);
        if (field instanceof DetailDataField) {
            DetailDataField detailField = (DetailDataField) field;
            String detailObjectName = detailField.getDetailObjectName();
            DataObject detailObject = this.sqlInfo.resolveDetailObject(detailObjectName);

            if (detailObject == null) {
                detailObject = this.resolver.resolveObject(detailObjectName);
                ObjectContextHolder.addObject(detailObject);
                assert (detailObject != null);
                this.sqlInfo.addDetailObject(detailObject);
            }

            // 子表下的属性
            if (Token.STAR.name.equals(x.getName())) {
                MongoSelectInfo detailSqlInfo = new MongoSelectInfo(detailObject);
                this.addAllDataObjectFieldsToSqlInfo(detailObject, detailSqlInfo);
                this.sqlInfo.addDetailSqlInfo(detailSqlInfo);
            }
        } else if (field instanceof MasterDataField) {
            MasterDataField masterField = (MasterDataField) field;
            String masterObjectName = masterField.getMasterObjectName();
            DataObject masterObject = this.sqlInfo.getMasterObject();
            if (masterObject == null) {
                masterObject = this.resolver.resolveObject(masterObjectName);
                ObjectContextHolder.addObject(masterObject);
                assert (masterObject != null);
                this.sqlInfo.setMasterObject(masterObject);
            }

            // 主表下的属性
            if (Token.STAR.name.equals(x.getName())) {
                MongoSelectInfo masterSqlInfo = new MongoSelectInfo(masterObject);
                this.addAllDataObjectFieldsToSqlInfo(masterObject, masterSqlInfo);
                this.sqlInfo.setMasterSqlInfo(masterSqlInfo);
            }
        } else if (field instanceof LookupDataField) {
            LookupDataField lookupField = (LookupDataField) field;
            String lookupObjectName = lookupField.getLookupObjectName();
            DataObject lookupObject = this.sqlInfo.resolveLookupObject(lookupObjectName);
            if (lookupObject == null) {
                lookupObject = this.resolver.resolveObject(lookupObjectName);
                assert (lookupField != null);
                this.sqlInfo.addLookupObject(lookupObject);
            }

            // 相关表下的属性
            if (Token.STAR.name.equals(x.getName())) {
                MongoSelectInfo lookupSqlInfo = new MongoSelectInfo(lookupObject);
                this.addAllDataObjectFieldsToSqlInfo(lookupObject, lookupSqlInfo);
                if (lookupField.getDataType().isMultiple()) {
                    // 添加查询条件 相关表的ID字段 ${lookupPrimaryColumnName} in :${lookupPrimaryFieldName}
                    DataField lookupObjectPrimaryField = lookupSqlInfo.getDataObject().getPrimaryField();
                    String lookupObjectPrimaryColumnName = lookupObjectPrimaryField.getColumnName();
                    String lookupFieldName = lookupField.getName();
                    // todo
//                    lookupSqlInfo.appendWhere(" ").appendWhere(lookupObjectPrimaryColumnName)
//                            .appendWhere(" in (:").appendWhere(lookupFieldName).appendWhere(")");
                    this.sqlInfo.addIndividualLookupSqlInfo(lookupField, lookupSqlInfo);
                } else {
                    this.sqlInfo.addLookupSqlInfo(lookupField, lookupSqlInfo);
                }
            }
        }

        return false;
    }





//    @Override
//    public boolean visit(OqlBinaryOpExpr x) {
//
//        if (x.getOperator() == OqlBinaryOperator.And || x.getOperator() == OqlBinaryOperator.Or) {
//            visitExpr(x.getLeft());
//        } else {
//            visitExpr(x.getLeft());
//            VisitValue visitValue = this.printer.print();
//            Object left = null;
//            if (visitValue instanceof TextVisitValue) {
//                left = ((TextVisitValue)visitValue).getValue();
//            } else {
//                left = visitValue.getValue();
//            }
//
//            visitExpr(x.getRight());
//            VisitValue value = this.printer.print();
//
//            //todo 解析where
//            Document document = new Document(getMongoOpeartor(x.getOperator()), Arrays.asList(left, value.getValue()));
//            this.sqlInfo.setWhere(new Document("$expr", document));
//        }
//
//
//        return false;
//    }
//
//    private String getMongoOpeartor(OqlBinaryOperator opeartor) {
//
//        switch (opeartor) {
//            case Equal:
//                return "$eq";
//            case GreaterThan:
//                return "$gt";
//            default:
//                throw new RuntimeException();
//        }
//    }
//
//
//    @Override
//    public boolean visit(OqlCharExpr x) {
//        this.printer.append(new CommonVisitValue(x.getValue(), ValueType.TEXT));
//        return false;
//    }
//
//    @Override
//    public boolean visit(OqlIntegerExpr x) {
//        this.printer.append(new CommonVisitValue(x.getValue(), ValueType.LONG));
//        return false;
//    }
//
//    @Override
//    public boolean visit(OqlIdentifierExpr x) {
//        this.printer.append(new TextVisitValue(x.getName()));
//        return false;
//    }
//
//    /**
//     * 打印一个表达式
//     *
//     * @param x
//     */
//    private void visitExpr(OqlExpr x) {
//        Class<?> clazz = x.getClass();
//        if (clazz == OqlCharExpr.class) {
//            this.visit((OqlCharExpr) x);
//        } else if (clazz == OqlBooleanExpr.class) {
//            this.visit((OqlBooleanExpr) x);
//        } else if (clazz == OqlIntegerExpr.class) {
//            this.visit((OqlIntegerExpr) x);
//        } else if (clazz == OqlNumberExpr.class) {
//            this.visit((OqlNumberExpr) x);
//        } else if (clazz == OqlNullExpr.class) {
//            this.visit((OqlNullExpr) x);
//        } else if (clazz == OqlJsonObjectExpr.class) {
//            this.visit((OqlJsonObjectExpr) x);
//        } else if (clazz == OqlJsonArrayExpr.class) {
//            this.visit((OqlJsonArrayExpr) x);
//        } else if (clazz == OqlIdentifierExpr.class) {
//            this.visit((OqlIdentifierExpr) x);
//        } else if (clazz == OqlPropertyExpr.class) {
//            this.visit((OqlPropertyExpr) x);
//        } else if (clazz == OqlNotExpr.class) {
//            this.visit((OqlNotExpr) x);
//        } else if (clazz == OqlBinaryOpExpr.class) {
//            this.visit((OqlBinaryOpExpr) x);
//        } else if (clazz == OqlMethodInvokeExpr.class) {
//            this.visit((OqlMethodInvokeExpr) x);
//        } else {
//            x.accept(this);
//        }
//    }

}


