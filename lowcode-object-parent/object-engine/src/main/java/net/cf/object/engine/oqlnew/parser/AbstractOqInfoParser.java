package net.cf.object.engine.oqlnew.parser;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlStatement;
import net.cf.object.engine.oqlnew.info.AbstractOqlInfos;

/**
 *
 * 抽象的OQL指令构建器，用于将OQL语句解析为OQL执行指令信息
 *
 * @author clouds
 * @param <O> OQL语句
 * @param <I> OQL信息
 */
public abstract class AbstractOqInfoParser<O extends OqlStatement, I extends AbstractOqlInfos> extends AbstractOqlParser {

    /**
     * 待解析的OQL语句
     */
    protected final O stmt;

    /**
     * 是否批量OQL语句
     */
    protected final boolean isBatch;

    /**
     * 解析后的本模型
     */
    protected XObject selfObject;

    public AbstractOqInfoParser(O stmt, boolean isBatch) {
        this.stmt = stmt;
        this.isBatch = isBatch;
    }

    /**
     * 解析OQL语句为OQL信息
     *
     * @return
     */
    public abstract I parse();

}
