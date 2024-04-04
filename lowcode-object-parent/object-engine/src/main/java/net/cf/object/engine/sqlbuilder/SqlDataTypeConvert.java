package net.cf.object.engine.sqlbuilder;

import net.cf.form.repository.sql.ast.SqlDataType;
import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.XField;

/**
 * SQL数据格式转换器
 *
 * @author clouds 
 */
public final class SqlDataTypeConvert {

    private SqlDataTypeConvert() {
    }

    public static SqlDataType toSqlDataType(XField field) {
        if (field.isArray()) {
            return SqlDataType.OBJECT;
        }

        DataType dataType = field.getDataType();
        if (dataType == DataType.STRING) {
            return SqlDataType.CHAR;
        } else if (dataType == DataType.NUMBER) {
            Integer precision = field.getDataPrecision();
            if (precision != null && precision > 0) {
                return SqlDataType.DECIMAL;
            } else {
                Number maxValue = field.getMaxValue();
                if (maxValue != null) {
                    if (maxValue.longValue() > Integer.MAX_VALUE) {
                        return SqlDataType.LONG;
                    } else {
                        return SqlDataType.INTEGER;
                    }
                } else {
                    return SqlDataType.LONG;
                }
            }
        } else if (dataType == DataType.DATE) {
            return SqlDataType.DATE;
        } else if (dataType == DataType.TIME) {
            return SqlDataType.TIME;
        } else if (dataType == DataType.BOOLEAN) {
            return SqlDataType.BOOLEAN;
        } else {
            return SqlDataType.OBJECT;
        }
    }
}
