package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;

import java.util.Arrays;
import java.util.List;

public enum MongoOperator {


    EQUAL("$eq", SqlBinaryOperator.EQUALITY),
    NOT_EQUAL("$ne", SqlBinaryOperator.NOT_EQUAL),
    LESS_THAN("$lt", SqlBinaryOperator.LESS_THAN),
    LESS_THAN_OR_EQUAL("$lte", SqlBinaryOperator.LESS_THAN_OR_EQUAL),
    GREATER_THAN("$gt", SqlBinaryOperator.GREATER_THAN),
    GREATER_THAN_OR_EQUAL("$gte", SqlBinaryOperator.GREATER_THAN_OR_EQUAL),
    IN("$in", SqlBinaryOperator.IN),



    AND("$and", SqlBinaryOperator.BOOLEAN_AND),
    OR("$or", SqlBinaryOperator.BOOLEAN_OR)




    ;
    private String expr;

    private SqlBinaryOperator sqlBinaryOperator;


    public String getExpr() {
        return this.expr;
    }

    public SqlBinaryOperator getSqlBinaryOperator() {
        return this.sqlBinaryOperator;
    }

    MongoOperator(String expr, SqlBinaryOperator sqlBinaryOperator) {
        this.expr = expr;
        this.sqlBinaryOperator = sqlBinaryOperator;
    }

    public static MongoOperator match(SqlBinaryOperator sqlBinaryOperator) {
        for (MongoOperator mongoOperator : MongoOperator.values()) {
            if (mongoOperator.getSqlBinaryOperator().equals(sqlBinaryOperator)) {
                return mongoOperator;
            }
        }
        throw new RuntimeException(sqlBinaryOperator.getName());
    }


    public boolean isLogical() {
        return this ==  AND || this == OR ;
    }

    private static List<MongoOperator> CONDITION_OPERATORS = Arrays.asList(
            EQUAL,
            NOT_EQUAL,
            LESS_THAN,
            LESS_THAN_OR_EQUAL,
            GREATER_THAN,
            GREATER_THAN_OR_EQUAL,
            IN
    );

    public boolean isCondition() {
        return CONDITION_OPERATORS.contains(this);
    }

    private static List<MongoOperator> COMPARE_OPERATORS = Arrays.asList(
            EQUAL,
            NOT_EQUAL,
            LESS_THAN,
            LESS_THAN_OR_EQUAL,
            GREATER_THAN,
            GREATER_THAN_OR_EQUAL,
            IN
    );

    public boolean isCompare() {
        return COMPARE_OPERATORS.contains(this);
    }


    private static List<MongoOperator> FIELD_TAG_OPERATORS = Arrays.asList(
            EQUAL,
            NOT_EQUAL,
            LESS_THAN,
            LESS_THAN_OR_EQUAL,
            GREATER_THAN,
            GREATER_THAN_OR_EQUAL,
            IN
    );

    public boolean isFieldTag() {
        return FIELD_TAG_OPERATORS.contains(this);
    }

}
