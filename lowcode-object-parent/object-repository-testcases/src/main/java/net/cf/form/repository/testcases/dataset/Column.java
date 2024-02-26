//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.cf.form.repository.testcases.dataset;

import net.cf.form.repository.sql.ast.SqlDataType;

public class Column {
    private final String columnName;
    private final SqlDataType dataType;
    private final String defaultValue;
    private final boolean nullable;
    private final boolean auto;

    public Column(String columnName, SqlDataType dataType) {
        this(columnName, dataType, false);
    }

    public Column(String columnName, SqlDataType dataType, boolean nullable) {
        this(columnName, dataType, nullable, null);
    }


    public Column(String columnName, SqlDataType dataType, boolean nullable, String defaultValue) {
        this(columnName, dataType, nullable, defaultValue, false);
    }

    public Column(String columnName, SqlDataType dataType, boolean nullable, String defaultValue, boolean auto) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.nullable = nullable;
        this.defaultValue = defaultValue;
        this.auto = auto;
    }

    public boolean hasDefaultValue() {
        return this.defaultValue != null;
    }

    public String getColumnName() {
        return this.columnName;
    }


    public SqlDataType getDataType() {
        return dataType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public boolean isNullable() {
        return nullable;
    }

    public boolean isAuto() {
        return auto;
    }

    public String toString() {
        return "(" + this.columnName + ", " + this.dataType + ", " + this.nullable + ")";
    }
}
