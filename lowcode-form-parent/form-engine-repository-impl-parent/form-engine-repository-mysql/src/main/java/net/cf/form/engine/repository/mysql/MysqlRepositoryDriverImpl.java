package net.cf.form.engine.repository.mysql;

import net.cf.form.engine.repository.AbstractRepositoryDriver;
import net.cf.form.engine.repository.StatementExecuteInterceptor;
import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.mysql.data.MysqlDeleteImpl;
import net.cf.form.engine.repository.mysql.data.MysqlInsertImpl;
import net.cf.form.engine.repository.mysql.data.MysqlSelectImpl;
import net.cf.form.engine.repository.mysql.data.MysqlUpdateImpl;
import net.cf.form.engine.repository.mysql.util.SqlUtils;
import net.cf.form.engine.repository.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.repository.sql_bak.*;
import net.cf.form.engine.repository.sql_bak.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Deprecated
public class MysqlRepositoryDriverImpl extends AbstractRepositoryDriver {

    private MysqlInsertImpl insertImpl;

    private MysqlUpdateImpl updateImpl;

    private MysqlDeleteImpl deleteImpl;

    private MysqlSelectImpl selectImpl;

    /**
     * 对象解析器
     */
    protected DataObjectResolver resolver;

    public MysqlRepositoryDriverImpl(NamedParameterJdbcTemplate jdbcTemplate, DataObjectResolver resolver) {
        this(jdbcTemplate, resolver, null);
    }

    public MysqlRepositoryDriverImpl(NamedParameterJdbcTemplate jdbcTemplate, DataObjectResolver resolver, List<StatementExecuteInterceptor> interceptors) {
        super(interceptors);
        this.resolver = resolver;

        insertImpl = new MysqlInsertImpl(jdbcTemplate, resolver);
        updateImpl = new MysqlUpdateImpl(jdbcTemplate, resolver);
        deleteImpl = new MysqlDeleteImpl(jdbcTemplate, resolver);
        selectImpl = new MysqlSelectImpl(jdbcTemplate, resolver);
    }


    @Override
    public InsertSqlInfo parseInsertSqlInfo(OqlInsertStatement statement) {
        return SqlUtils.parseInsertStatement(statement, resolver, false);
    }

    @Override
    public Object[] insertSelf(InsertSqlInfo sqlInfo) {
        return this.insertSelf(sqlInfo, (Map<String, Object>) null);
    }

    @Override
    public Object[] insertSelf(InsertSqlInfo sqlInfo, Map<String, Object> values) {
        return insertImpl.insertSelf(sqlInfo, values);
    }

    @Override
    public Object[] insertSelf(InsertSqlInfo sqlInfo, List<Map<String, Object>> valuesList) {
        return insertImpl.insertSelf(sqlInfo, valuesList);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public UpdateSqlInfo parseUpdateSqlInfo(OqlUpdateStatement statement) {
        return SqlUtils.parseUpdateStatement(statement, resolver, false);
    }

    @Override
    public Map<Object, List<Object>> queryForUpdateMasterIdMap(UpdateDetailSqlInfo sqlInfo, Map<String, Object> values) {
        return updateImpl.queryForMasterIdMap(sqlInfo, values);
    }

    @Override
    public int updateSelf(UpdateSqlInfo sqlInfo) {
        return updateImpl.updateSelf(sqlInfo);
    }

    @Override
    public int updateSelf(UpdateSqlInfo sqlInfo, Map<String, Object> values) {
        return updateImpl.updateSelf(sqlInfo, values);
    }

    @Override
    public int[] batchUpdateSelf(UpdateSqlInfo sqlInfo, List<Map<String, Object>> valuesList) {
        return updateImpl.batchUpdateSelf(sqlInfo, valuesList);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<Object> queryForMasterIds(AbstractQueryableSqlInfo masterSqlInfo, Map<String, Object> paramMap) {
        return selectImpl.queryForMasterIds(masterSqlInfo, paramMap);
    }

    @Override
    public Map<Object, List<Object>> queryForMasterAndDetailIdMap(AbstractQueryableSqlInfo detailSqlInfo, List<Object> masterIds, Map<String, Object> paramMap) {
        return selectImpl.queryForMasterAndDetailIdMap(detailSqlInfo, masterIds, paramMap);
    }

    @Override
    public int deleteSelf(DeleteSqlInfo deleteSqlInfo) {
        return deleteImpl.deleteSelf(deleteSqlInfo);
    }

    @Override
    public int deleteSelf(DeleteSqlInfo deleteSqlInfo, Map<String, Object> paramMap) {
        return deleteImpl.deleteSelf(deleteSqlInfo, paramMap);
    }

    @Override
    public DeleteSqlInfo parseDeleteSqlInfo(OqlDeleteStatement statement) {
        return SqlUtils.parseDeleteStatement(statement, resolver, false);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected SelectSqlInfo parseSelectSqlInfo(OqlSelectStatement statement, Map<String, Object> paramMap) {
        return SqlUtils.parseSelectStatement(statement, resolver, paramMap != null);
    }

    @Override
    protected List<Map<String, Object>> select(SelectSqlInfo sqlInfo, Map<String, Object> paramMap) {
        return selectImpl.select(sqlInfo, paramMap);
    }

}
