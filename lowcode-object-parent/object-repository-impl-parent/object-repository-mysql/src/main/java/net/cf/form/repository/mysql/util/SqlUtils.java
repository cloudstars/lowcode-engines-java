package net.cf.form.repository.mysql.util;

import com.alibaba.fastjson.JSON;
import net.cf.form.repository.mysql.jdbc.AdvancedMapSqlParameterSource;
import net.cf.form.repository.mysql.visitor.MySqlAstOutputVisitor;
import net.cf.form.repository.sql.ast.SqlDataType;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateSetItem;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * SQL工具类
 *
 * @author clouds
 */
public final class SqlUtils {

    private SqlUtils() {
    }

    /**
     * 将SQL语句转为SQL文本
     *
     * @param stmt
     * @return
     */
    public static String toSqlText(SqlStatement stmt) {
        StringBuilder builder = new StringBuilder();
        MySqlAstOutputVisitor visitor = new MySqlAstOutputVisitor(builder);
        stmt.accept(visitor);
        return builder.toString();
    }

    /**
     * 根据字段的数据类型生成JdbcTemplate插入时的参数格式(批量)
     *
     * @param stmt
     * @param paramMaps
     * @return
     */
    public static SqlParameterSource[] convertInsertParamMaps(SqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        int size = paramMaps.size();
        SqlParameterSource[] parameterSources = new AdvancedMapSqlParameterSource[size];
        for (int i = 0; i < size; i++) {
            parameterSources[i] = SqlUtils.convertInsertParamMap(stmt, paramMaps.get(i));
        }

        return parameterSources;
    }

    /**
     * 根据字段的数据类型生成JdbcTemplate插入时的参数格式
     *
     * @param stmt
     * @param paramMap
     * @return
     */
    public static SqlParameterSource convertInsertParamMap(SqlInsertStatement stmt, Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }

        MapSqlParameterSource paramSource = new AdvancedMapSqlParameterSource();
        List<SqlInsertStatement.ValuesClause> valuesList = stmt.getValuesList();
        List<SqlExpr> columns = stmt.getColumns();
        int columnSize = columns.size();
        for (SqlInsertStatement.ValuesClause values : valuesList) {
            List<SqlExpr> valuesExpr = values.getValues();
            for (int i = 0; i < columnSize; i++) {
                SqlExpr column = columns.get(i);
                SqlExpr valueExpr = valuesExpr.get(i);
                if (valueExpr instanceof SqlVariantRefExpr) {
                    SqlVariantRefExpr varRefExpr = (SqlVariantRefExpr) valueExpr;
                    SqlUtils.processVariableRefExpr(varRefExpr, column, paramMap, paramSource);
                }
            }
        }

        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String paramName = entry.getKey();
            if (!paramSource.hasValue(paramName)) {
                paramSource.addValue(paramName, paramMap.get(paramName));
            }
        }

        return paramSource;
    }


    /**
     * 根据字段的数据类型生成JdbcTemplate更新时的参数格式(批量)
     *
     * @param stmt
     * @param paramMaps
     * @return
     */
    public static SqlParameterSource[] convertUpdateParamMaps(SqlUpdateStatement stmt, List<Map<String, Object>> paramMaps) {
        int size = paramMaps.size();
        SqlParameterSource[] parameterSources = new AdvancedMapSqlParameterSource[size];
        for (int i = 0; i < size; i++) {
            parameterSources[i] = SqlUtils.convertUpdateParamMap(stmt, paramMaps.get(i));
        }

        return parameterSources;
    }

    /**
     * 根据字段的数据类型生成JdbcTemplate更新时的参数格式
     *
     * @param stmt
     * @param paramMap
     * @return
     */
    public static SqlParameterSource convertUpdateParamMap(SqlUpdateStatement stmt, Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }

        MapSqlParameterSource paramSource = new AdvancedMapSqlParameterSource();
        List<SqlUpdateSetItem> setItems = stmt.getSetItems();
        int itemSize = setItems.size();
        for (int i = 0; i < itemSize; i++) {
            SqlUpdateSetItem setItem = setItems.get(i);
            SqlExpr column = setItem.getColumn();
            SqlExpr valueExpr = setItem.getValue();
            if (valueExpr instanceof SqlVariantRefExpr) {
                SqlVariantRefExpr varRefExpr = (SqlVariantRefExpr) valueExpr;
                SqlUtils.processVariableRefExpr(varRefExpr, column, paramMap, paramSource);
            }
        }

        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String paramName = entry.getKey();
            if (!paramSource.hasValue(paramName)) {
                paramSource.addValue(paramName, paramMap.get(paramName));
            }
        }

        return paramSource;
    }

    /**
     * 将 List<Map> 参数转换为JDBC-TEMPLATE批量更新的参数格式
     *
     * @param paramMaps
     * @return SQL参数
     */
    public static SqlParameterSource[] convertParamMaps(List<Map<String, Object>> paramMaps) {
        int paramSize = paramMaps.size();
        SqlParameterSource[] paramSources = new AdvancedMapSqlParameterSource[paramSize];
        for (int i = 0; i < paramSize; i++) {
            Map<String, Object> paramMap = paramMaps.get(i);
            paramSources[i] = new AdvancedMapSqlParameterSource(paramMap);
        }

        return paramSources;
    }

    /**
     * 处理变量引用节点
     *
     * @param varRefExpr
     * @param columnExpr
     * @param paramMap
     * @param paramSource
     */
    private static void processVariableRefExpr(SqlVariantRefExpr varRefExpr, SqlExpr columnExpr, Map<String, Object> paramMap, MapSqlParameterSource paramSource) {
        String varName = varRefExpr.getVarName();
        if (paramMap.containsKey(varName)) {
            SqlDataType sqlDataType = columnExpr.computeSqlDataType();
            if (sqlDataType == SqlDataType.OBJECT) {
                // 插入时，将对象转为字符串存会
                Object paramValue = paramMap.get(varName);
                if (paramValue != null) {
                    if (paramValue instanceof String) {
                        paramSource.addValue(varName, paramValue, Types.CHAR);
                    } else {
                        paramSource.addValue(varName, JSON.toJSONString(paramValue), Types.CHAR);
                    }
                } else {
                    paramSource.addValue(varName, null, Types.CHAR);
                }
            } else {
                paramSource.addValue(varName, paramMap.get(varName), sqlDataType.toJdbcType());
            }
        }
    }
}
