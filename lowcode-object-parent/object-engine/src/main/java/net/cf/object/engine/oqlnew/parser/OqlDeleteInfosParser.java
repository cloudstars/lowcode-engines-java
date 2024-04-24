
package net.cf.object.engine.oqlnew.parser;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oqlnew.info.OqlDeleteInfos;

import java.util.HashMap;
import java.util.Map;

/**
 * OQL删除语句解析器
 * <p>
 * 职责：用于将一条OQL删除语句解析成本表的删除与子表的删除
 */
public class OqlDeleteInfosParser extends AbstractOqInfoParser<OqlDeleteStatement, OqlDeleteInfos> {

    /**
     * 模型关联的删除语句
     */
    private final Map<XObject, OqlDeleteStatement> stmtMap = new HashMap<>();

    private final RepoExprBuilder sqlExprBuilder;

    private int nonFieldSelectItemIndex = 0;

    public OqlDeleteInfosParser(OqlDeleteStatement stmt, boolean isBatch) {
        super(stmt, isBatch);
        this.sqlExprBuilder = new RepoExprBuilder();
    }

    /**
     * 解析成OQL删除语句指令信息
     *
     * @return
     */
    public OqlDeleteInfos parse() {
        return null;
    }
}
