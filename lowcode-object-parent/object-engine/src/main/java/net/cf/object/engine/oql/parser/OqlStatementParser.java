package net.cf.object.engine.oql.parser;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.*;

import java.util.ArrayList;
import java.util.List;

/**
 * OQL语句解析器
 *
 * @author clouds
 */
public class OqlStatementParser extends OqlExprParser {

    private final XObject object;

    public OqlStatementParser(XObject object, String oql) {
        super(object, oql);
        this.object = object;
    }

    /**
     * 解析 OQL 语句
     *
     * @return
     */
    public List<OqlStatement> parseStatementList() {
        List<OqlStatement> statementList = new ArrayList();
        boolean semi = false;
        OqlStatement stmt = null;
        while (true) {
            semi = false;
            switch (this.lexer.token()) {
                case EOF:
                    return statementList;
                case SEMI:
                    this.lexer.nextToken();
                    if (stmt != null) {
                        stmt.setAfterSemi(true);
                    }

                    semi = true;
                    break;
                case SELECT:
                    stmt = parseSelectStatement();
                    statementList.add(stmt);
                    break;
                case INSERT:
                    stmt = parseInsertStatement();
                    statementList.add(stmt);
                    break;
                case UPDATE:
                    stmt = parseUpdateStatement();
                    statementList.add(stmt);
                    break;
                case DELETE:
                    stmt = parseDeleteStatement();
                    statementList.add(stmt);
                    break;
                default:
                    printError(this.lexer.token());
                    break;
            }
        }
    }

    /**
     * 解析 Select 语句
     *
     * @return
     */
    private OqlSelectStatement parseSelectStatement() {
        OqlSelectParser selectParser = new OqlSelectParser(object, this.lexer);
        OqlSelect select = selectParser.oqlSelect();
        return new OqlSelectStatement(select);
    }

    /**
     * 解析 Insert 语句
     *
     * @return
     */
    private OqlInsertStatement parseInsertStatement() {
        OqlInsertIntoParser insertParser = new OqlInsertIntoParser(object, this.lexer);
        OqlInsertInto insert = insertParser.insertInto();
        return new OqlInsertStatement(insert);
    }

    /**
     * 解析 Update 语句
     *
     * @return
     */
    private OqlUpdateStatement parseUpdateStatement() {
        OqlUpdateStatementParser parser = new OqlUpdateStatementParser(object, this.lexer);
        return parser.statement();
    }

    /**
     * 解析 Delete 语句
     *
     * @return
     */
    private OqlDeleteStatement parseDeleteStatement() {
        OqlDeleteStatementParser parser = new OqlDeleteStatementParser(object, this.lexer);
        return parser.statement();
    }
}
