package io.github.cloudstars.lowcode.object.repository.sql.parser;

import io.github.cloudstars.lowcode.object.repository.sql.FastSqlException;
import io.github.cloudstars.lowcode.object.repository.sql.ast.statement.SqlDeleteStatement;
import io.github.cloudstars.lowcode.object.repository.sql.ast.statement.SqlExprTableSource;
import io.github.cloudstars.lowcode.object.repository.sql.ast.statement.SqlTableSource;

public class SqlDeleteStatementParser extends SqlExprParser {

    public SqlDeleteStatementParser(Lexer lexer) {
        super(lexer);
    }

    public SqlDeleteStatementParser(String sql) {
        this(new Lexer(sql));
    }

    public SqlDeleteStatement statement() {
        this.accept(Token.DELETE);
        this.accept(Token.FROM);

        SqlDeleteStatement statement = new SqlDeleteStatement();
        SqlTableSource tableSource = this.parseTableSource();
        if (!(tableSource instanceof SqlExprTableSource)) {
            throw new FastSqlException("Delete SQL语句只允许操作标识符表名");
        }
        statement.setFrom((SqlExprTableSource) tableSource);
        if (this.lexer.token == Token.WHERE) {
            this.lexer.nextToken();
            statement.setWhere(this.expr());
        }

        return statement;
    }
}
