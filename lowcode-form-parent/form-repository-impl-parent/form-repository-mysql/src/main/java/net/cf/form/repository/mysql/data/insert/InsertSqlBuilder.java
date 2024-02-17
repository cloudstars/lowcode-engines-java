package net.cf.form.repository.mysql.data.insert;

public class InsertSqlBuilder {

    private final StringBuilder builder = new StringBuilder();

    public InsertSqlBuilder appendColumn() {
        return this;
    }

    public String build() {
        return this.builder.toString();
    }
}
