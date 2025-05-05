package io.github.cloudstars.lowcode.object.repository.sql.util;

import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstOutputVisitor;

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
