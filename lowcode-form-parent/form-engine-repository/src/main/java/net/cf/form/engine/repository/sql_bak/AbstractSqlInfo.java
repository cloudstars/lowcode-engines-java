package net.cf.form.engine.repository.sql_bak;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.oql.ast.statement.OqlStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象的SQL信息
 *
 * @author clouds
 */
@Deprecated
public abstract class AbstractSqlInfo<T extends OqlStatement, D extends AbstractSqlInfo> {

    /**
     * 对象
     */
    protected DataObject object;

    /**
     * 主键列所在的位置（从0开始）
     */
    private int primaryFieldIndex = -1;

    /**
     * OQL语句
     */
    private T statement;

    /**
     * 子对象的SQL信息
     */
    private final Map<String, D> detailSqlInfoMap = new HashMap<>();

    public AbstractSqlInfo(DataObject object) {
        this.object = object;
    }

    public DataObject getObject() {
        return object;
    }

    public T getStatement() {
        if (statement == null) {
            statement = buildStatement();
        }

        return statement;
    }

    /**
     * 构建一个Statement
     *
     * @return
     */
    public abstract T buildStatement();

    public int getPrimaryFieldIndex() {
        return primaryFieldIndex;
    }

    public void setPrimaryFieldIndex(int primaryFieldIndex) {
        this.primaryFieldIndex = primaryFieldIndex;
    }

    public Map<String, D> getDetailSqlInfoMap() {
        return detailSqlInfoMap;
    }

    public void addDetailSqlInfo(String detailFieldName, D detailSqlInfo) {
        this.detailSqlInfoMap.put(detailFieldName, detailSqlInfo);
    }

    public D getDetailSqlInfo(String detailFieldName) {
        return this.detailSqlInfoMap.get(detailFieldName);
    }
}
