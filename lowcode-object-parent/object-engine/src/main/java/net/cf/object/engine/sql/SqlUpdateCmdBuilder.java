
package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.ast.statement.SqlUpdateSetItem;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.object.ValueTypeImpl;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQL 更新指令构建器
 * <p>
 * 职责：用于将一条 OQL 更新语句构建成 SQL 更新指令
 * OQL语句示例：如：
 * update object set f1 = xx, f2 = yy, f3 = [多选不带属性], f4 = {单选带属性}, f5 = [多选带属性] where ...
 * update object set f1 = #{f1'}, f2 = #{f2'}, f3 = #{f3'}, f4 = #{f4'}, f5 = #{f5'} where ...
 */
public class SqlUpdateCmdBuilder extends AbstractSqlCmdBuilder<OqlUpdateStatement, SqlUpdateCmd> {

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 输入的参数（批量模式）
     */
    private final List<Map<String, Object>> paramMaps;

    /**
     * 模型关联的插入语句
     */
    private SqlUpdateStatement sqlStmt;

    /**
     * 字段映射表，当存在变量值时，需要做字段映射
     */
    private List<FieldMapping> fieldMappings;

    public SqlUpdateCmdBuilder(OqlUpdateStatement stmt, Map<String, Object> paramMap) {
        super(stmt, false);
        this.paramMap = paramMap;
        this.paramMaps = null;

        this.checkStmt(this.stmt);
    }

    public SqlUpdateCmdBuilder(OqlUpdateStatement stmt, List<Map<String, Object>> paramMaps) {
        super(stmt, true);
        this.paramMap = null;
        this.paramMaps = paramMaps;

        this.checkStmt(this.stmt);
    }

    /**
     * 校验 OQL 语句的合法性
     *
     * @param stmt
     */
    private void checkStmt(OqlUpdateStatement stmt) {
    }


    /**
     * 解析成OQL插入语句指令信息
     *
     * @return
     */
    @Override
    public SqlUpdateCmd build() {
        OqlExprObjectSource objectSource = this.stmt.getObjectSource();
        this.resolvedObject = objectSource.getResolvedObject();

        // 初始化 SQL 插入语句
        this.sqlStmt = new SqlUpdateStatement();
        this.sqlStmt.setTableSource(this.buildSqlTableSource(objectSource));

        // 构建 SQL 语名的列、值
        List<OqlUpdateSetItem> setItems = this.stmt.getSetItems();
        int setItemSize = setItems.size();
        for (int i = 0; i < setItemSize; i++) {
            OqlUpdateSetItem setItem = setItems.get(i);
            OqlExpr field = setItem.getField();
            SqlExpr value = setItem.getValue();
            assert (field instanceof OqlFieldExpr);

            XField resolvedField = ((OqlFieldExpr) field).getResolvedField();
            String fieldName = resolvedField.getName();
            List<XProperty> properties = resolvedField.getProperties();
            if (CollectionUtils.isEmpty(properties)) {
                SqlExpr sqlColumn = this.buildSqlExpr(resolvedField);
                SqlExpr sqlValue = this.buildSqlExpr(value);
                SqlUpdateSetItem sqlSetItem = new SqlUpdateSetItem();
                sqlSetItem.setColumn(sqlColumn);
                sqlSetItem.setValue(sqlValue);
                this.sqlStmt.addSetItem(sqlSetItem);
            } else {
                if (value instanceof SqlVariantRefExpr) { // field = #{field}
                    String varName = ((SqlVariantRefExpr) value).getVarName();
                    FieldMapping fieldMapping = new FieldMapping(varName);
                    fieldMapping.setValueType(new ValueTypeImpl(resolvedField.getDataType(), resolvedField.isArray()));
                    for (XProperty property : properties) {
                        SqlExpr sqlColumn = this.buildSqlExpr(property);
                        SqlExpr sqlValue = this.buildPropertyVarRefExpr(property, varName);
                        SqlUpdateSetItem sqlSetItem = new SqlUpdateSetItem();
                        sqlSetItem.setColumn(sqlColumn);
                        sqlSetItem.setValue(sqlValue);
                        this.sqlStmt.addSetItem(sqlSetItem);

                        // 添加字段映射，用于后续参数查找
                        // TODO 考虑直接在Build阶段，将值从入参中抽取出来，不需要后面的ParameterMapper过程了
                        FieldMapping subFieldMapping = new FieldMapping(property.getName());
                        subFieldMapping.setValueType(property);
                        fieldMapping.addSubField(subFieldMapping);
                    }
                    List<FieldMapping> fieldMappings = this.getFieldMappings();
                    fieldMappings.add(fieldMapping);
                } else if (value instanceof OqlFieldExpr) { // fieldA = fieldB
                    OqlFieldExpr fieldValue = (OqlFieldExpr) value;
                    List<XProperty> fieldValueProps = fieldValue.getResolvedField().getProperties();
                    if (CollectionUtils.isEmpty(fieldValueProps)) {
                        throw new FastOqlException("两个字段赋值时，带属性的字段的值也必须是一个带属性的字段");
                    }

                    int fieldPropSize = properties.size();
                    if (fieldValueProps.size() != fieldPropSize) {
                        throw new FastOqlException("两个字段赋值时，带属性的字段的值的属性个数必须等于被赋值的字段的属性个数");
                    }

                    for (int j = 0; j < fieldPropSize; j++) {
                        SqlExpr sqlColumn = this.buildSqlExpr(properties.get(j));
                        SqlExpr sqlValue = this.buildSqlExpr(fieldValueProps.get(j));
                        SqlUpdateSetItem sqlSetItem = new SqlUpdateSetItem();
                        sqlSetItem.setColumn(sqlColumn);
                        sqlSetItem.setValue(sqlValue);
                        this.sqlStmt.addSetItem(sqlSetItem);
                    }
                } else { // field = [] 或者 field = {}
                    if (!resolvedField.isArray() && !(value instanceof SqlJsonObjectExpr)) {
                        throw new FastOqlException("字段" + fieldName + "的值必须是一个JSON对象");
                    }

                    if (resolvedField.isArray() && !(value instanceof SqlJsonArrayExpr)) {
                        throw new FastOqlException("字段" + fieldName + "的值必须是一个JSON对象数组");
                    }

                    for (XProperty property : properties) {
                        SqlExpr sqlColumn = this.buildSqlExpr(property);
                        SqlExpr sqlValue = this.extractPropertyValue(value, property);
                        SqlUpdateSetItem sqlSetItem = new SqlUpdateSetItem();
                        sqlSetItem.setColumn(sqlColumn);
                        sqlSetItem.setValue(sqlValue);
                        this.sqlStmt.addSetItem(sqlSetItem);
                    }
                }
            }
        }

        SqlExpr where = this.stmt.getWhere();
        if (where != null) {
            SqlWhereBuilder whereExprParser = new SqlWhereBuilder(this.resolvedObject);
            SqlExpr sqlWhere = whereExprParser.parseExpr(where);
            this.sqlStmt.setWhere(sqlWhere);
        }

        // 构建 SQL 更新指令
        SqlUpdateCmd updateCmd = this.buildSqlUpdateCmd();
        return updateCmd;
    }

    /**
     * 构建 SQL 更新指令
     *
     * @return
     */
    private SqlUpdateCmd buildSqlUpdateCmd() {
        SqlUpdateCmd updateCmd = new SqlUpdateCmd();
        updateCmd.setResolvedObject(this.resolvedObject);
        updateCmd.setStatement(this.sqlStmt);
        updateCmd.setBatch(this.isBatch);
        updateCmd.setParamMap(this.paramMap);
        updateCmd.setParamMaps(this.paramMaps);
        updateCmd.setFieldMappings(this.fieldMappings);
        return updateCmd;
    }

    /**
     * 获取字段映射列表
     *
     * @return
     */
    private List<FieldMapping> getFieldMappings() {
        if (this.fieldMappings == null) {
            this.fieldMappings = new ArrayList<>();
        }

        return this.fieldMappings;
    }

}
