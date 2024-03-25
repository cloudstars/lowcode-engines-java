package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinConditionBuilder {

    private SqlBinaryOpExpr sqlExpr;

    private Map<String, Object> paramMap = null;

    private boolean enableVariable = false;

    public JoinConditionBuilder(SqlBinaryOpExpr sqlExpr, Map<String, Object> paramMap) {
        this.sqlExpr = sqlExpr;
        if (MongoUtils.isVariableEnable(paramMap)) {
            this.enableVariable = true;
            this.paramMap = paramMap;
        }
    }


    public Document buildExpr(JoinInfo joinInfo) {
        VisitContextInfo visitContextInfo = new VisitContextInfo();
        visitContextInfo.setJoinInfo(joinInfo);
        MongoExprAstVisitor visitor = new MongoExprAstVisitor(paramMap, visitContextInfo);

        Object object = visitor.visit(sqlExpr);
        if (!(object instanceof Document)) {
            throw new RuntimeException("error");
        }
        return (Document) object;
    }


    public Map<String, List<String>> getJoinParam() {
        List<SqlPropertyExpr> sqlPropertyCollection = new ArrayList<>();
        analyseCondition(sqlExpr, sqlPropertyCollection);

        Map<String, List<String>> joinParamMapping = new HashMap<>();
        for (SqlPropertyExpr sqlPropertyExpr : sqlPropertyCollection) {
            if (sqlPropertyExpr.getOwner() instanceof SqlIdentifierExpr) {
                String owner = ((SqlIdentifierExpr) sqlPropertyExpr.getOwner()).getName();
                MongoExprAstVisitor mongoExprAstVisitor = new MongoExprAstVisitor();
                String value = String.valueOf(mongoExprAstVisitor.visit(sqlPropertyExpr));
                if (!joinParamMapping.containsKey(owner)) {
                    joinParamMapping.put(owner, new ArrayList<>());
                }
                joinParamMapping.get(owner).add(value);
            }
        }
        return joinParamMapping;

    }

    public void analyseCondition(SqlExpr sqlExpr, List<SqlPropertyExpr> sqlPropertyCollection) {
        if (sqlExpr instanceof SqlBinaryOpExpr) {
            SqlBinaryOpExpr sqlBinaryOpExpr = (SqlBinaryOpExpr) sqlExpr;
            analyseCondition(sqlBinaryOpExpr.getLeft(), sqlPropertyCollection);
            analyseCondition(sqlBinaryOpExpr.getRight(), sqlPropertyCollection);
        } else if (sqlExpr instanceof SqlPropertyExpr) {
            sqlPropertyCollection.add((SqlPropertyExpr) sqlExpr);
        }
    }
}
