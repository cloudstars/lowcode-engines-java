package net.cf.form.repository.mongo.data.visitor;

import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;

import java.util.Arrays;
import java.util.List;

public enum MongoOperator {

    MULTIPLY("$multiply", SqlBinaryOperator.MULTIPLY),
    DIVIDE("$divide", SqlBinaryOperator.DIVIDE),
    MODULUS("$mod", SqlBinaryOperator.MODULUS),
    ADD("$add", SqlBinaryOperator.ADD),
    SUBTRACT("$subtract", SqlBinaryOperator.SUBTRACT),

    EQUAL("$eq", SqlBinaryOperator.EQUALITY),
    NOT_EQUAL("$ne", SqlBinaryOperator.NOT_EQUAL),
    LESS_THAN("$lt", SqlBinaryOperator.LESS_THAN),
    LESS_THAN_OR_EQUAL("$lte", SqlBinaryOperator.LESS_THAN_OR_EQUAL),
    GREATER_THAN("$gt", SqlBinaryOperator.GREATER_THAN),
    GREATER_THAN_OR_EQUAL("$gte", SqlBinaryOperator.GREATER_THAN_OR_EQUAL),
    IN("$in", SqlBinaryOperator.IN),
    LIKE("$regex", SqlBinaryOperator.LIKE),
    CONTAINS("$contains", SqlBinaryOperator.CONTAINS),
    CONTAINS_ALL("", SqlBinaryOperator.CONTAINS_ALL),
    CONTAINS_ANY("", SqlBinaryOperator.CONTAINS_ANY),
    IS("", SqlBinaryOperator.IS),
    IS_NOT("", SqlBinaryOperator.IS_NOT),

    AND("$and", SqlBinaryOperator.BOOLEAN_AND),
    OR("$or", SqlBinaryOperator.BOOLEAN_OR);
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
        return this == AND || this == OR;
    }

    private static List<MongoOperator> CONDITION_OPERATORS = Arrays.asList(
            IN,
            CONTAINS
    );

    private static List<MongoOperator> COMPARE_OPERATORS = Arrays.asList(
            EQUAL,
            NOT_EQUAL,
            LESS_THAN,
            LESS_THAN_OR_EQUAL,
            GREATER_THAN,
            GREATER_THAN_OR_EQUAL
    );
    private static List<MongoOperator> COMPUTE_OPERATORS = Arrays.asList(
            MULTIPLY,
            ADD,
            DIVIDE,
            MODULUS,
            SUBTRACT
    );

    public boolean isCondition() {
        if (this.isCompare()) {
            return true;
        }
        return CONDITION_OPERATORS.contains(this);
    }

    /**
     * 是否是比较符号
     *
     * @return
     */
    public boolean isCompare() {
        return COMPARE_OPERATORS.contains(this);
    }

    /**
     * 是否是计算符号
     *
     * @return
     */
    public boolean isCompute() {
        return COMPUTE_OPERATORS.contains(this);
    }

    public boolean isContains() {
        return this == CONTAINS || this == CONTAINS_ALL || this == CONTAINS_ANY;
    }


    /**
     * 是否字段标记
     *
     * @return
     */
    public boolean isFieldTag() {
        if (this == CONTAINS || this == CONTAINS_ALL || this == CONTAINS_ANY) {
            return false;
        }
        return true;
    }

}
