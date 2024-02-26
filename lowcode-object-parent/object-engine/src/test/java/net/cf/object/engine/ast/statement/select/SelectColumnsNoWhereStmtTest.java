package net.cf.object.engine.ast.statement.select;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.object.engine.ast.statement.AbstractOqlTest;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectSource;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SelectColumnsNoWhereStmtTest extends AbstractOqlTest {

    public SelectColumnsNoWhereStmtTest() {
        super("oql/select/SelectColumnsNoWhere.json");
    }

    @Test
    public void testSelectTravelList() {
        String oql = this.oqlMap.get("SelectTravelList");
        assert (oql != null);

        OqlSelectStatement stmt = OqlUtils.parseSingleSelectStatement(oql);
        assert (stmt != null);
        assert (oql.equals(stmt.toString()));

        OqlObjectSource objectSource = stmt.getSelect().getFrom();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && "Travel".equals(((SqlIdentifierExpr) osExpr).getName()));
    }
}
