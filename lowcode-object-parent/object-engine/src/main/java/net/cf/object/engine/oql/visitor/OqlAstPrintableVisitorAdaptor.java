package net.cf.object.engine.oql.visitor;

import net.cf.object.engine.oql.ast.OqlObjectExpandExpr;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.PrintableVisitorAdaptor;

/**
 * AST访问适配器
 *
 * @author clouds
 */
public class OqlAstPrintableVisitorAdaptor extends PrintableVisitorAdaptor implements OqlAstPrintableVisitor {

    public OqlAstPrintableVisitorAdaptor(Appendable appender) {
        super(appender);
    }

    /**
     * 打印一个表达式
     *
     * @param x
     */
    @Override
    protected final void printExpr(SqlExpr x) {
        Class<?> clazz = x.getClass();
        if (clazz == OqlObjectExpandExpr.class) {
            this.visit((OqlObjectExpandExpr) x);
        } else {
            super.printExpr(x);
        }
    }

}
