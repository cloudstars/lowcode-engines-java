
package net.cf.object.engine.oqlnew.parser;

import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oqlnew.info.OqlUpdateInfos;

import java.util.HashMap;
import java.util.Map;

/**
 * OQL更新语句解析器
 * <p>
 * 职责：用于将一条OQL更新语句解析成本表的更新与子表的更新
 */
public class OqlUpdateInfosParser extends AbstractOqInfoParser<OqlUpdateStatement, OqlUpdateInfos> {

    /**
     * 模型关联的更新语句
     */
    private final Map<XObject, SqlUpdateStatement> stmtMap = new HashMap<>();

    private final RepoExprBuilder sqlExprBuilder;

    private int nonFieldSelectItemIndex = 0;

    public OqlUpdateInfosParser(OqlUpdateStatement stmt, boolean isBatch) {
        super(stmt, isBatch);
        this.sqlExprBuilder = new RepoExprBuilder();
    }

    /**
     * 解析成OQL更新语句指令信息
     *
     * @return
     */
    public OqlUpdateInfos parse() {
        return null;
    }
}
