
package net.cf.object.engine.oqlnew.sql;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertInto;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.object.ValueTypeImpl;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.OqlExpr;
import net.cf.object.engine.oql.ast.OqlFieldExpr;
import net.cf.object.engine.oql.ast.OqlInsertInto;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQL 插入指令构建器
 * <p>
 * 职责：用于将一条 OQL 插入语句解析成 SQL 插入指令
 * 单个values的OQL语句示例：如：
 *   insert into f1, f2, f3, f4, f5, ... into object values (f1, f2, [多选不带属性], {单选带属性}, [多选带属性], ...)
 *   insert into f1, f2, f3, f4, f5, ... into object values (#{f1}, #{f2}, #{f3}, {单选带属性}, [多选带属性], ...)
 * 多个values的OQL语句允许有多个values：如：insert into f1, f2, f3, f4, f5, ... into object values 上述两个case的组合
 */
public class SqlInsertCmdBuilder extends AbstractSqlCmdBuilder<OqlInsertStatement, SqlInsertCmd> {

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
    private SqlInsertStatement sqlStmt;

    /**
     * 字段映射表，当存在变量值时，需要做字段映射
     */
    private List<FieldMapping> fieldMappings;

    public SqlInsertCmdBuilder(OqlInsertStatement stmt, Map<String, Object> paramMap) {
        super(stmt, false);
        this.paramMap = paramMap;
        this.paramMaps = null;

        this.checkStmt(this.stmt);
    }

    public SqlInsertCmdBuilder(OqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        super(stmt, true);
        this.paramMap = null;
        this.paramMaps = paramMaps;

        this.checkStmt(this.stmt);
    }

    /**
     * 解析成OQL插入语句指令信息
     *
     * @return
     */
    @Override
    public SqlInsertCmd build() {
        OqlInsertInto insertInto = this.stmt;
        this.resolvedObject = insertInto.getObjectSource().getResolvedObject();

        // 初始化 SQL 插入语句
        this.initSqlStmt();

        // 构建 SQL 语名的列、值
        List<OqlExpr> fields = insertInto.getFields();
        int fieldSize = fields.size();
        for (int i = 0; i < fieldSize; i++) {
            OqlExpr field = fields.get(i);
            assert (field instanceof OqlFieldExpr);
            XField resolvedField = ((OqlFieldExpr) field).getResolvedField();
            this.buildColumn(resolvedField);
            this.buildValues(resolvedField, i);
        }

        // 构建 SQL 插入指令
        SqlInsertCmd insertCmd = this.buildSqlInsertCmd();
        return insertCmd;
    }

    /**
     * 初始化 SQL 语句
     */
    private void initSqlStmt() {
        OqlInsertInto insertInto = this.stmt;
        List<SqlInsertStatement.ValuesClause> valuesList = insertInto.getValuesList();
        int valuesSize = valuesList.size();
        SqlInsertInto sqlInsertInto = new SqlInsertInto();
        sqlInsertInto.setTableSource(this.buildSqlTableSource(insertInto.getObjectSource()));
        for (int i = 0; i < valuesSize; i++) {
            sqlInsertInto.addValues(new SqlInsertStatement.ValuesClause());
        }
        this.sqlStmt = new SqlInsertStatement(sqlInsertInto);
        XField primaryField = this.resolvedObject.getPrimaryField();
        if (primaryField.isAutoGen()) {
            this.sqlStmt.setAutoGenColumn(primaryField.getColumnName());
        }
    }


    /**
     * 校验 OQL 语句的合法性
     *
     * @param stmt
     */
    private void checkStmt(OqlInsertStatement stmt) {
        OqlInsertInto insertInto = stmt;
        int fieldSize = insertInto.getFields().size();
        List<SqlInsertStatement.ValuesClause> valuesList = insertInto.getValuesList();
        int valuesSize = valuesList.size();
        for (int i = 0; i < valuesSize; i++) {
            if (valuesList.get(i).getValues().size() != fieldSize) {
                throw new FastOqlException("插入的值的个数与插入的字段的个数不匹配");
            }
        }
    }

    /**
     * 构建SQL语句的字段对应的列
     *
     * @param resolvedField
     */
    private void buildColumn(XField resolvedField) {
        SqlInsertInto sqlInsertInto = this.sqlStmt;
        List<XProperty> properties = resolvedField.getProperties();
        boolean hasProps = !CollectionUtils.isEmpty(properties);
        if (!hasProps) {
            sqlInsertInto.addColumn(this.buildSqlExpr(resolvedField));
        } else {
            for (XProperty property : properties) {
                sqlInsertInto.addColumn(this.buildSqlExpr(property));
            }
        }
    }

    /**
     * 构建 SQL 语句第i个字段的对应列的值
     *
     * @param resolvedField
     * @param fieldIndex
     */
    private void buildValues(XField resolvedField, int fieldIndex) {
        List<XProperty> properties = resolvedField.getProperties();
        boolean hasProps = !CollectionUtils.isEmpty(properties);
        List<SqlInsertStatement.ValuesClause> valuesList = this.stmt.getValuesList();
        List<SqlInsertStatement.ValuesClause> sqlValuesList = this.sqlStmt.getValuesList();
        int valuesSize = valuesList.size();
        for (int j = 0; j < valuesSize; j++) {
            SqlExpr valueExpr = valuesList.get(j).getValue(fieldIndex);
            SqlInsertStatement.ValuesClause sqlValues = sqlValuesList.get(j);
            if (valueExpr instanceof SqlVariantRefExpr) {
                String varName = ((SqlVariantRefExpr) valueExpr).getVarName();
                FieldMapping fieldMapping = new FieldMapping(varName, varName);
                fieldMapping.setValueType(new ValueTypeImpl(resolvedField.getDataType(), resolvedField.isArray()));

                if (!hasProps) {
                    sqlValues.addValue(valueExpr);
                } else {
                    for (XProperty property : properties) {
                        SqlExpr sqlValueExpr = this.buildPropertyVarRefExpr(property, varName);
                        sqlValues.addValue(sqlValueExpr);
                        String propName = property.getName();
                        FieldMapping subFieldMapping = new FieldMapping(propName, propName);
                        subFieldMapping.setValueType(new ValueTypeImpl(property.getDataType(), property.isArray()));
                        fieldMapping.addSubField(subFieldMapping);
                    }
                }
                this.getFieldMappings().add(fieldMapping);
            } else {
                if (!hasProps) {
                    sqlValues.addValue(valueExpr);
                } else {
                    for (XProperty property : properties) {
                        SqlExpr sqlValueExpr = this.extractPropertyValue(valueExpr, property);
                        sqlValues.addValue(sqlValueExpr);
                    }
                }
            }
        }
    }

    /**
     * 构建SQL插入指令
     *
     * @return
     */
    private SqlInsertCmd buildSqlInsertCmd() {
        SqlInsertCmd insertCmd = new SqlInsertCmd();
        insertCmd.setResolvedObject(this.resolvedObject);
        insertCmd.setStatement(this.sqlStmt);
        insertCmd.setBatch(this.isBatch);
        insertCmd.setParamMap(this.paramMap);
        insertCmd.setParamMaps(this.paramMaps);
        insertCmd.setFieldMappings(this.fieldMappings);
        return insertCmd;
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
