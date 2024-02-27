package net.cf.form.repository.mysql;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.mysql.data.insert.InsertSqlAstVisitor;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class MySQLObjectRepositoryImpl implements ObjectRepository {

    private final Logger logger = LoggerFactory.getLogger(MySQLObjectRepositoryImpl.class);

    @Resource
    private JdbcTemplate template;

    public MySQLObjectRepositoryImpl() {
    }

    @Override
    public int insert(SqlInsertStatement statement) {
        StringBuilder builder = new StringBuilder();
        InsertSqlAstVisitor visitor = new InsertSqlAstVisitor(builder);
        statement.accept(visitor);
        String sql = builder.toString();
        int effectedRows = this.template.update(sql);
        if (effectedRows < 1) {
            // throw new SQLEXCEPTION("数据插入失败！");
        }

        logger.info("数据插入成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int insert(SqlInsertStatement statement, Map<String, Object> paramMap) {
        StringBuilder builder = new StringBuilder();
        InsertSqlAstVisitor visitor = new InsertSqlAstVisitor(builder);
        statement.accept(visitor);
        String sql = builder.toString();
        // TODO 判断是否存在自增主键
        int effectedRows = this.template.update(sql, paramMap);
        return effectedRows;
    }

    @Override
    public int[] batchInsert(SqlInsertStatement statement, List<Map<String, Object>> paramMapList) {
        return new int[0];
    }

    @Override
    public int update(SqlUpdateStatement statement) {
        return 0;
    }

    @Override
    public int update(SqlUpdateStatement statement, Map<String, Object> paramMap) {
        return 0;
    }

    @Override
    public int[] batchUpdate(SqlUpdateStatement statement, List<Map<String, Object>> paramMapList) {
        return new int[0];
    }

    @Override
    public int delete(SqlDeleteStatement statement) {
        return 0;
    }

    @Override
    public int delete(SqlDeleteStatement statement, Map<String, Object> paramMap) {
        return 0;
    }

    @Override
    public int[] batchDelete(SqlDeleteStatement statement, List<Map<String, Object>> paramMapList) {
        return new int[0];
    }

    @Override
    public Map<String, Object> selectOne(SqlSelectStatement statement) {
        return null;
    }

    @Override
    public Map<String, Object> selectOne(SqlSelectStatement statement, Map<String, Object> paramMap) {
        return null;
    }



    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement, Map<String, Object> paramMap) {
        return null;
    }
}
