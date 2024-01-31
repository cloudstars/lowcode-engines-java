package net.cf.form.engine.repository.mysql.statement.insert;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.InsertDetailSqlInfo;
import net.cf.form.engine.repository.sql_bak.InsertSqlInfo;
import net.cf.form.engine.repository.mysql.statement.AbstractSqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlObjectExpandExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlJsonArrayExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertInto;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertValues;
import net.cf.form.engine.repository.oql.ast.statement.OqlObjectSource;
import net.cf.form.engine.repository.oql.parser.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于生成 MyOql insert 语句的AST访问器
 *
 * @author clouds
 */
public final class InsertSqlAstVisitor extends AbstractSqlAstVisitor<InsertSqlInfo> {

    public InsertSqlAstVisitor(DataObjectResolver resolver) {
        this(resolver, false);
    }

    public InsertSqlAstVisitor(DataObjectResolver resolver, boolean parameterized) {
        super(resolver, parameterized);
    }

    @Override
    public boolean visit(OqlInsertInto x) {
        OqlObjectSource objectSource = x.getObjectSource();
        this.resolveObjectSource(objectSource);
        this.sqlInfo = new InsertSqlInfo(this.object);

        this.checkFieldValues(x);
        this.parseOqlInfo(x);

        return false;
    }

    /**
     * 检查字段的长度与输入数据的列的长度是否一致
     *
     * @param insert
     */
    private void checkFieldValues(OqlInsertInto insert) {
        // 遍历字段列表
        int fieldSize = insert.getFields().size();
        List<OqlInsertValues> valuesList = insert.getValuesList();
        int valuesSize = valuesList.size();
        for (int j = 0; j < valuesSize; j++) {
            if (valuesList.get(j).getValues().size() != fieldSize) {
                throw new RuntimeException("插入数据的列数量与字段列的数量不匹配");
            }
        }
    }

    /**
     * 解析SQL信息
     *
     * @param insert
     */
    private void parseOqlInfo(OqlInsertInto insert) {
        // 设置本对象的字段列表以及主键在OQL中的位置
        List<QqlExpr> fields = insert.getFields();
        List<QqlExpr> selfFields = new ArrayList<>();
        String primaryFieldName = object.getPrimaryField().getName();
        List<OqlInsertValues> valuesList = insert.getValuesList();
        int fieldSize = fields.size(), primaryFieldIndex = -1;
        for (int i = 0; i < fieldSize; i++) {
            QqlExpr field = fields.get(i);
            if (field instanceof OqlIdentifierExpr) {
                selfFields.add(field);
                if (primaryFieldIndex < 0) {
                    String fieldName = ((OqlIdentifierExpr) field).getName();
                    if (primaryFieldName.equalsIgnoreCase(fieldName)) {
                        primaryFieldIndex = i;
                        this.sqlInfo.setPrimaryFieldIndex(i);
                    }
                }
            } else if (field instanceof OqlObjectExpandExpr) {
                this.processDetailField((OqlObjectExpandExpr) field, i, valuesList);
            }
        }
        this.sqlInfo.addFields(selfFields);

        // 设置本对象的值列表
        List<OqlInsertValues> selfValuesList;
        if (sqlInfo.getDetailSqlInfoMap().isEmpty()) {
            selfValuesList = insert.getValuesList();
        } else {
            selfValuesList = this.collectSelfFieldValues(insert);
        }
        this.sqlInfo.addValuesList(selfValuesList);
    }

    /**
     * 收集OQL中本对象插入的值列表
     *
     * @param insert
     */
    private List<OqlInsertValues> collectSelfFieldValues(OqlInsertInto insert) {
        List<OqlInsertValues> valuesList = insert.getValuesList();
        int valuesSize = insert.getValuesList().size();
        List<OqlInsertValues> selfValuesList = new ArrayList<>();
        for (int i = 0; i < valuesSize; i++) {
            selfValuesList.add(new OqlInsertValues());
        }

        List<QqlExpr> fields = insert.getFields();
        int fieldSize = fields.size();
        for (int i = 0; i < fieldSize; i++) {
            QqlExpr field = fields.get(i);
            if (field instanceof OqlIdentifierExpr) {
                // 当每个values的当前位置的值，添加到新的本表selfValuesList的值中
                OqlInsertValues sourceValues, targetValues;
                for (int j = 0; j < valuesSize; j++) {
                    sourceValues = valuesList.get(j);
                    targetValues = selfValuesList.get(j);
                    targetValues.addValue(sourceValues.getValue(i));
                }
            }
        }

        return selfValuesList;
    }

    /**
     * 解析子对象字段
     *
     * @param objectExpandExpr
     * @param objectExpandExprIndex
     * @param valuesList
     */
    private void processDetailField(OqlObjectExpandExpr objectExpandExpr, int objectExpandExprIndex, List<OqlInsertValues> valuesList) {
        String detailFieldName = objectExpandExpr.getObjectFieldName();
        DataObject detailObject = this.resolveFieldObject(detailFieldName);

        // 设置子对象插入语句的基本信息
        InsertDetailSqlInfo detailSqlInfo = new InsertDetailSqlInfo(detailObject, this.sqlInfo);

        // 设置字段列表
        List<QqlExpr> detailFields = objectExpandExpr.getFields();
        detailSqlInfo.addFields(detailFields);

        // 设置主键在OQL语句中的位置
        String detailPrimaryFieldName = detailObject.getPrimaryField().getName();
        for (int i = 0, l = detailFields.size(); i < l; i++) {
            OqlIdentifierExpr detailField = (OqlIdentifierExpr) detailFields.get(i);
            if (detailField.getName().equalsIgnoreCase(detailPrimaryFieldName)) {
                detailSqlInfo.setPrimaryFieldIndex(i);
                break;
            }
        }

        // 设置值列表
        int valuesSize = valuesList.size();
        for (int i = 0; i < valuesSize; i++) {
            QqlExpr detailValue = valuesList.get(i).getValues().get(objectExpandExprIndex);
            if (detailValue instanceof OqlJsonArrayExpr) {
                detailSqlInfo.addValues((OqlJsonArrayExpr) detailValue);
            } else if (detailValue instanceof OqlMethodInvokeExpr) {
                detailSqlInfo.addValues((OqlMethodInvokeExpr) detailValue);
            } else {
                throw new ParserException("Unsupported detail insert values: " + detailValue);
            }
        }
        this.sqlInfo.addDetailSqlInfo(detailFieldName, detailSqlInfo);
    }

    @Override
    public InsertSqlInfo getSqlInfo() {
        return sqlInfo;
    }
}
