package net.cf.object.engine.oql.stmt;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.parser.XObjectResolver;

/**
 *
 * 抽象的OQL指令构建器，用于将SQL语句解析为OQL语句
 *
 * @author clouds
 * @param <O> OQL语句
 */
public abstract class AbstractOqStatementParser<O extends AbstractOqlStatement> extends AbstractOqlParser {

    /**
     * 待解析的OQL语句
     */
    protected final String oql;

    /**
     * 是否批量OQL语句
     */
    protected final boolean isBatch;

    /**
     * 解析后的本模型
     */
    protected XObject selfObject;

    public AbstractOqStatementParser(XObjectResolver resolver, String oql, boolean isBatch) {
        super(resolver);
        this.oql = oql;
        this.isBatch = isBatch;
    }

    /**
     * 解析OQL语句
     *
     * @return
     */
    public abstract O parse();

}
