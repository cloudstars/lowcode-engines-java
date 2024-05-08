package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.SqlDataType;
import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XProperty;

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
        Integer precision = field.getDataPrecision();
        Number maxValue = field.getMaxValue();
        return SqlDataTypeConvert.toSqlDataType(dataType, precision, maxValue);
    }


    public static SqlDataType toSqlDataType(XProperty property) {
        if (property.isArray()) {
            return SqlDataType.OBJECT;
        }

        DataType dataType = property.getDataType();
        Integer precision = property.getDataPrecision();
        // TODO XField、XProperty需要抽一个抽象类
        Number maxValue = property.getMaxValue();
        return SqlDataTypeConvert.toSqlDataType(dataType, precision, maxValue);
    }

    private static SqlDataType toSqlDataType(DataType dataType, Integer precision, Number maxValue) {
        if (dataType == DataType.STRING) {
            return SqlDataType.CHAR;
        } else if (dataType == DataType.NUMBER) {
            if (precision != null && precision > 0) {
                return SqlDataType.DECIMAL;
            } else {
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
        } else if (dataType == DataType.BOOLEAN) {
            return SqlDataType.BOOLEAN;
        } else {
            return SqlDataType.OBJECT;
        }
    }
}
