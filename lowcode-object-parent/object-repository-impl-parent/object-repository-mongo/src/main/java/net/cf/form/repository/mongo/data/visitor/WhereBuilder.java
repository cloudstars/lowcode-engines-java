package net.cf.form.repository.mongo.data.visitor;

import net.cf.form.repository.mongo.data.MongoUtils;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import org.bson.Document;

import java.util.Map;

public class WhereBuilder {

    private SqlExpr sqlExpr;

    private Map<String, Object> paramMap = null;

    private boolean enableVariable = false;

    private Map<String, String> existsAliasMap = null;

    public WhereBuilder(SqlExpr sqlExpr, Map<String, Object> paramMap) {
        this.sqlExpr = sqlExpr;
        if (MongoUtils.isVariableEnable(paramMap)) {
            this.enableVariable = true;
            this.paramMap = paramMap;
        }
    }

    public WhereBuilder(SqlExpr sqlExpr, Map<String, Object> paramMap, Map<String, String> existsAliasMap) {
        this.sqlExpr = sqlExpr;
        if (MongoUtils.isVariableEnable(paramMap)) {
            this.enableVariable = true;
            this.paramMap = paramMap;
        }
        if (existsAliasMap != null) {
            this.existsAliasMap = existsAliasMap;
        }
    }


    public Document build() {
        GlobalContext globalContext = new GlobalContext(paramMap, PositionEnum.WHERE);
        if (this.existsAliasMap != null) {
            globalContext.setExistAliasMap(this.existsAliasMap);
        }
        Object object = MongoExprVisitor.visit(sqlExpr, globalContext);

        if (!(object instanceof Document)) {
            throw new RuntimeException("error");
        }
        return (Document) object;
    }
}
