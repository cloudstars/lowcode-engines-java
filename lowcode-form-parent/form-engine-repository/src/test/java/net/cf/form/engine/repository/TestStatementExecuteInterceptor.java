package net.cf.form.engine.repository;

import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlCharExpr;
import net.cf.form.engine.repository.oql.ast.statement.*;
import net.cf.form.engine.repository.testcases.statement.util.DateUtils;
import net.cf.form.engine.repository.oql.ast.statement.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * OQL语句执行拦截器
 *
 * @author clouds
 */
public class TestStatementExecuteInterceptor implements StatementExecuteInterceptor {

    private final String ENTP_KEY = "enterpriseKey";
    private final String CREATED_BY_KEY = "createdBy";
    private final String CREATED_AT_KEY = "createdAt";
    private final String MODIFIED_BY_KEY = "modifiedBy";
    private final String MODIFIED_AT_KEY = "modifiedAt";

    private final String ENTERPRISE = "CMB";
    private final String USER = "ADMIN";

    @Override
    public void beforeInsert(OqlInsertStatement statement) {
        addCommonInsertInto(statement);
    }

    @Override
    public void beforeInsert(OqlInsertStatement statement, Map<String, Object> paramMap) {
        addCommonInsertInto(statement);
        addCommonInsertValues(paramMap, new Date());
    }

    @Override
    public void beforeInsert(OqlInsertStatement statement, List<Map<String, Object>> paramMaps) {
        addCommonInsertInto(statement);
        Date now = new Date();
        for (Map<String, Object> paramMap : paramMaps) {
            addCommonInsertValues(paramMap, now);
        }
    }

    /**
     * 添加插入时的通用数据
     *
     * @param paramMap
     */
    private void addCommonInsertValues(Map<String, Object> paramMap, Date now) {
        if (paramMap != null) {
            paramMap.put(ENTP_KEY, ENTERPRISE);
            paramMap.put(CREATED_BY_KEY, USER);
            paramMap.put(CREATED_AT_KEY, now);
        }
    }

    private void addCommonInsertInto(OqlInsertStatement statement) {
        OqlInsertInto insertInto = statement.getInsertInto();
        insertInto.addField(new OqlIdentifierExpr(ENTP_KEY));
        insertInto.addField(new OqlIdentifierExpr(CREATED_BY_KEY));
        insertInto.addField(new OqlIdentifierExpr(CREATED_AT_KEY));

        for (OqlInsertValues insertValues : insertInto.getValuesList()) {
            insertValues.addValue(new OqlCharExpr(ENTERPRISE));
            insertValues.addValue(new OqlCharExpr(USER));
            insertValues.addValue(new OqlCharExpr(DateUtils.formatDateTime(new Date())));
        }
    }

    @Override
    public void beforeUpdate(OqlUpdateStatement statement) {
        addCommonUpdateSetItems(statement);
    }

    @Override
    public void beforeUpdate(OqlUpdateStatement statement, Map<String, Object> paramMap) {
        addCommonUpdateSetItems(statement);
        addCommonUpdateValues(paramMap, new Date());
    }

    @Override
    public void beforeUpdate(OqlUpdateStatement statement, List<Map<String, Object>> paramMapList) {
        addCommonUpdateSetItems(statement);
        Date now = new Date();
        for (Map<String, Object> paramMap : paramMapList) {
            addCommonUpdateValues(paramMap, now);
        }
    }

    /**
     * 添加更新时的通用字段
     *
     * @param statement
     */
    private void addCommonUpdateSetItems(OqlUpdateStatement statement) {
        List<OqlUpdateSetItem> setItems = statement.getSetItems();
        setItems.add(new OqlUpdateSetItem(new OqlIdentifierExpr(MODIFIED_BY_KEY), new OqlCharExpr(USER)));
        setItems.add(new OqlUpdateSetItem(new OqlIdentifierExpr(MODIFIED_AT_KEY), new OqlCharExpr(DateUtils.formatDateTime(new Date()))));
    }

    /**
     * 添加更新时的通用数据
     *
     * @param paramMap
     */
    private void addCommonUpdateValues(Map<String, Object> paramMap, Date now) {
        if (paramMap != null) {
            paramMap.put(MODIFIED_BY_KEY, USER);
            paramMap.put(MODIFIED_AT_KEY, now);
        }
    }


    @Override
    public void beforeDelete(OqlDeleteStatement statement) {

    }

    @Override
    public void beforeDelete(OqlDeleteStatement statement, Map<String, Object> paramMap) {

    }
}
