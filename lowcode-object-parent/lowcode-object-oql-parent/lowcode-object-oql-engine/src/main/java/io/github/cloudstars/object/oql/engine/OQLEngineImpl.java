package io.github.cloudstars.object.oql.engine;

import io.github.cloudstars.lowcode.object.repository.ObjectRepository;
import io.github.cloudstars.lowcode.object.repository.sql.ast.statement.SqlInsertStatement;

import java.util.Map;

public class OQLEngineImpl implements OQLEngine {

    private ObjectRepository repository;

    @Override
    public int insert(String oql) {
        SqlInsertStatement stmt = null;
        int effectedRows = this.repository.insert(stmt);
        return effectedRows;
    }

    @Override
    public int insert(String oql, Map<String, Object> paramMap) {
        return 0;
    }

    @Override
    public int update(String oql) {
        return 0;
    }

    @Override
    public int update(String oql, Map<String, Object> paramMap) {
        return 0;
    }
}
