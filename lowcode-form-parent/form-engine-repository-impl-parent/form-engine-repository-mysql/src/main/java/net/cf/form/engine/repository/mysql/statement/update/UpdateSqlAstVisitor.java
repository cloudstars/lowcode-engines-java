package net.cf.form.engine.repository.mysql.statement.update;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.UpdateDetailSqlInfo;
import net.cf.form.engine.repository.sql_bak.UpdateSqlInfo;
import net.cf.form.engine.repository.mysql.statement.AbstractSqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlObjectExpandExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlJsonArrayExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateSetItem;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.repository.oql.parser.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于生成 MySql update 语句的AST访问器
 *
 * @author clouds
 */
public final class UpdateSqlAstVisitor extends AbstractSqlAstVisitor<UpdateSqlInfo> {

    public UpdateSqlAstVisitor(DataObjectResolver resolver) {
        this(resolver, false);
    }

    public UpdateSqlAstVisitor(DataObjectResolver resolver, boolean parameterized) {
        super(resolver, parameterized);
    }

    @Override
    public boolean visit(OqlUpdateStatement x) {
        OqlObjectSource objectSource = x.getObjectSource();
        this.resolveObjectSource(objectSource);
        this.sqlInfo = new UpdateSqlInfo(this.object);

        this.checkFieldValues(x);
        this.parseSqlInfo(x);

        return false;
    }

    /**
     * 检查子表字段的长度与输入数据的列的长度是否一致
     *
     * @param statement
     */
    private void checkFieldValues(OqlUpdateStatement statement) {

    }

    /**
     * 解析SQL信息
     *
     * @param statement
     */
    private void parseSqlInfo(OqlUpdateStatement statement) {
        List<OqlUpdateSetItem> selfSetItems = new ArrayList<>();
        String primaryFieldName = this.object.getPrimaryField().getName();
        List<OqlUpdateSetItem> setItems = statement.getSetItems();
        int itemSize = setItems.size(), primaryFieldIndex = -1;
        for (int i = 0; i < itemSize; i++) {
            OqlUpdateSetItem setItem = setItems.get(i);
            QqlExpr field = setItem.getField();
            if (field instanceof OqlIdentifierExpr) {
                selfSetItems.add(setItem);
                if (primaryFieldIndex < 0) {
                    String fieldName = ((OqlIdentifierExpr) field).getName();
                    if (primaryFieldName.equalsIgnoreCase(fieldName)) {
                        primaryFieldIndex = i;
                        this.sqlInfo.setPrimaryFieldIndex(primaryFieldIndex);
                    }
                }
            } else if (field instanceof OqlObjectExpandExpr) {
                this.processDetailFiled((OqlObjectExpandExpr) field, setItem.getValue());
            }
        }

        this.sqlInfo.addSetItems(selfSetItems);
        this.sqlInfo.setWhereClause(statement.getWhereClause());
    }

    /**
     * 解析子对象字段
     *
     * @param objectExpandExpr
     */
    private void processDetailFiled(OqlObjectExpandExpr objectExpandExpr, QqlExpr detailValues) {
        String detailFieldName = objectExpandExpr.getObjectFieldName();
        DataObject detailObject = this.resolveFieldObject(detailFieldName);

        // 设置子对象更新语句的基本信息
        UpdateDetailSqlInfo detailSqlInfo = new UpdateDetailSqlInfo(detailObject, this.sqlInfo);

        List<QqlExpr> detailFields = objectExpandExpr.getFields();
        OqlListExpr detailListFields = new OqlListExpr(detailFields);
        if (detailValues instanceof OqlJsonArrayExpr) {
            detailSqlInfo.addFieldsValues(detailListFields, (OqlJsonArrayExpr) detailValues);
        } else if (detailValues instanceof OqlMethodInvokeExpr) {
            detailSqlInfo.addFieldsValues(detailListFields, (OqlMethodInvokeExpr) detailValues);
        } else {
            throw new ParserException("Unsupported detail update values: " + detailValues);
        }

        // 设置主键在OQL语句中的位置以及更新值列表
        String detailPrimaryFieldName = detailObject.getPrimaryField().getName();
        for (int i = 0, l = detailFields.size(); i < l; i++) {
            OqlIdentifierExpr detailField = (OqlIdentifierExpr) detailFields.get(i);
            if (detailField.getName().equalsIgnoreCase(detailPrimaryFieldName)) {
                detailSqlInfo.setPrimaryFieldIndex(i);
                break;
            }
        }

        this.sqlInfo.addDetailSqlInfo(detailFieldName, detailSqlInfo);
    }

    @Override
    public UpdateSqlInfo getSqlInfo() {
        return sqlInfo;
    }
}
