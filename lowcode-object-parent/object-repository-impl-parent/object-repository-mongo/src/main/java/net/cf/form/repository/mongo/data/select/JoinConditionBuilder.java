package net.cf.form.repository.mongo.data.select;

import net.cf.form.repository.mongo.data.visitor.GlobalContext;
import net.cf.form.repository.mongo.data.visitor.MongoExprVisitor;
import net.cf.form.repository.mongo.data.visitor.PositionEnum;
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

    public static Document buildExpr(SqlBinaryOpExpr sqlExpr, JoinInfo joinInfo, Map<String, Object> paramMap) {
        GlobalContext globalContext = new GlobalContext(PositionEnum.JOIN);
        globalContext.setDataMap(paramMap);
        globalContext.setJoinInfo(joinInfo);
        Object object = MongoExprVisitor.visit(sqlExpr, globalContext);
        if (!(object instanceof Document)) {
            throw new RuntimeException("error");
        }
        return (Document) object;
    }


    public static Map<String, List<String>> getJoinParam(SqlBinaryOpExpr sqlExpr) {
        List<SqlPropertyExpr> sqlPropertyCollection = new ArrayList<>();
        analyseCondition(sqlExpr, sqlPropertyCollection);

        Map<String, List<String>> joinParamMapping = new HashMap<>();
        for (SqlPropertyExpr sqlPropertyExpr : sqlPropertyCollection) {
            if (sqlPropertyExpr.getOwner() instanceof SqlIdentifierExpr) {
                String owner = ((SqlIdentifierExpr) sqlPropertyExpr.getOwner()).getName();
                String value = String.valueOf(MongoExprVisitor.visit(sqlPropertyExpr, new GlobalContext(PositionEnum.JOIN)));
                if (!joinParamMapping.containsKey(owner)) {
                    joinParamMapping.put(owner, new ArrayList<>());
                }
                joinParamMapping.get(owner).add(value);
            }
        }
        return joinParamMapping;

    }

    private static void analyseCondition(SqlExpr sqlExpr, List<SqlPropertyExpr> sqlPropertyCollection) {
        if (sqlExpr instanceof SqlBinaryOpExpr) {
            SqlBinaryOpExpr sqlBinaryOpExpr = (SqlBinaryOpExpr) sqlExpr;
            analyseCondition(sqlBinaryOpExpr.getLeft(), sqlPropertyCollection);
            analyseCondition(sqlBinaryOpExpr.getRight(), sqlPropertyCollection);
        } else if (sqlExpr instanceof SqlPropertyExpr) {
            sqlPropertyCollection.add((SqlPropertyExpr) sqlExpr);
        }
    }
}
