package net.cf.form.engine.repository.mongo;

import net.cf.form.engine.repository.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.repository.RepositoryDriver;

import java.util.List;
import java.util.Map;


/**
 * 基于Mongo的驱动实现类
 *
 * @author clouds
 */
@Deprecated
public class MongoRepositoryDriverImpl implements RepositoryDriver {

    @Override
    public int insert(OqlInsertStatement statement) {
        return 0;
    }

    @Override
    public int insert(OqlInsertStatement statement, Map<String, Object> values) {
        return 0;
    }

    @Override
    public int[] batchInsert(OqlInsertStatement statement, List<Map<String, Object>> valuesList) {
        return new int[0];
    }

    @Override
    public int update(OqlUpdateStatement statement) {
        return 0;
    }

    @Override
    public int update(OqlUpdateStatement statement, Map<String, Object> values) {
        return 0;
    }

    @Override
    public int[] batchUpdate(OqlUpdateStatement statement, List<Map<String, Object>> paramMapList) {
        return new int[0];
    }

    @Override
    public int delete(OqlDeleteStatement statement) {
        return 0;
    }

    @Override
    public int delete(OqlDeleteStatement statement, Map<String, Object> values) {
        return 0;
    }

    @Override
    public int delete(OqlDeleteStatement statement, List<Map<String, Object>> paramMapList) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> select(OqlSelectStatement statement) {
        return null;
    }

    @Override
    public List<Map<String, Object>> select(OqlSelectStatement statement, Map<String, Object> values) {
        return null;
    }
}
