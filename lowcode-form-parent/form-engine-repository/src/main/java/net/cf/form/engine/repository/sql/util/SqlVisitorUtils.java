package net.cf.form.engine.repository.sql.util;

import net.cf.form.engine.repository.sql.visitor.SqlAstOutputVisitor;

public final class SqlVisitorUtils {

    private SqlVisitorUtils() {
    }

    /**
     * 创建一个输出访问器
     *
     * @param out
     * @return
     */
    public static SqlAstOutputVisitor createAstOutputVisitor(Appendable out) {
        return new SqlAstOutputVisitor(out);
    }


}
