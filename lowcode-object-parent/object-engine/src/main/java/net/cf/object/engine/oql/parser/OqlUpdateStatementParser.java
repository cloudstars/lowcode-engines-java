package net.cf.object.engine.oql.parser;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlUpdateSetItem;
import net.cf.form.repository.sql.parser.Lexer;
import net.cf.form.repository.sql.parser.Token;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;

import java.util.List;

public class OqlUpdateStatementParser extends OqlExprParser {

    private final XObject object;

    public OqlUpdateStatementParser(XObject object, Lexer lexer) {
        super(object, lexer);
        this.object = object;
    }

    public OqlUpdateStatementParser(XObject object, String oql) {
        this(object, new Lexer(oql));
    }

    public OqlUpdateStatement statement() {
        this.accept(Token.UPDATE);

        OqlUpdateStatement statement = new OqlUpdateStatement();
        statement.setObjectSource(this.parseExprObjectSource());
        this.parseSetItems(statement);
        if (this.lexer.token() == Token.WHERE) {
            this.lexer.nextToken();
            statement.setWhere(this.expr());
        }

        // 校正set左值的类型
        List<SqlUpdateSetItem> setItems = statement.getSetItems();
        for (SqlUpdateSetItem setItem : setItems) {
            SqlExpr setItemFieldX = setItem.getColumn();
            SqlExpr realSetItemFieldX = this.getRealExprByObject(setItemFieldX, object);
            if (realSetItemFieldX != setItemFieldX) {
                setItemFieldX = realSetItemFieldX;
            }
            setItem.setColumn(setItemFieldX);
        }

        return statement;
    }

    /**
     * 解析set字段
     *
     * @param statement
     */
    private void parseSetItems(OqlUpdateStatement statement) {
        this.accept(Token.SET);
        this.parseCommaSeperatedList(() -> {
            SqlUpdateSetItem setItem = new SqlUpdateSetItem();
            setItem.setColumn(this.primary());
            this.accept(Token.EQ);
            setItem.setValue(this.primary());
            statement.addSetItem(setItem);
        });
    }
}
