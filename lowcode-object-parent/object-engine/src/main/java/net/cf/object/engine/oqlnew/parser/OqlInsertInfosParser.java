
package net.cf.object.engine.oqlnew.parser;

import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oqlnew.info.OqlInsertInfos;

import java.util.HashMap;
import java.util.Map;

/**
 * OQL插入语句解析器
 * <p>
 * 职责：用于将一条OQL插入语句解析成本表的插入与子表的插入
 */
public class OqlInsertInfosParser extends AbstractOqInfoParser<OqlInsertStatement, OqlInsertInfos> {

    /**
     * 模型关联的插入语句
     */
    private final Map<XObject, SqlInsertStatement> stmtMap = new HashMap<>();

    private final RepoExprBuilder sqlExprBuilder;

    private int nonFieldSelectItemIndex = 0;

    public OqlInsertInfosParser(OqlInsertStatement stmt, boolean isBatch) {
        super(stmt, isBatch);
        this.sqlExprBuilder = new RepoExprBuilder();
    }

    /**
     * 解析成OQL插入语句指令信息
     *
     * @return
     */
    public OqlInsertInfos parse() {
        return null;
    }
}
