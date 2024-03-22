package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import org.bson.Document;

import java.util.Map;

public class WhereBuilder {

    private SqlExpr sqlExpr;

    private Map<String, Object> paramMap = null;

    private boolean enableVariable = false;

    public WhereBuilder(SqlExpr sqlExpr) {
        this.sqlExpr = sqlExpr;
    }

    public WhereBuilder(SqlExpr sqlExpr, Map<String, Object> paramMap) {
        this.sqlExpr = sqlExpr;
        if (MongoUtils.isVariableEnable(paramMap)) {
            this.enableVariable = true;
            this.paramMap = paramMap;
        }
    }


    public Document build() {
        MongoExprAstVisitor visitor;
        if (enableVariable) {
            visitor = new MongoExprAstVisitor(sqlExpr, paramMap);
        } else {
            visitor = new MongoExprAstVisitor(sqlExpr);
        }

        Object object = visitor.visit();
        if (!(object instanceof Document)) {
            throw new RuntimeException("error");
        }
        return (Document) object;
    }
}
