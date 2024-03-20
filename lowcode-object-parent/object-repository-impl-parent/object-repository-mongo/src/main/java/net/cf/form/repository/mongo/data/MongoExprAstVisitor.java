package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlDecimalExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import org.bson.Document;

import java.util.Arrays;
import java.util.Map;


/**
 * todo 整个expr解析逻辑还需要调整
 */
public class MongoExprAstVisitor {

    private SqlExpr sqlExpr ;

    private Map<String, Object> dataMap;

    private boolean enableVariable = false;


    public MongoExprAstVisitor(SqlExpr sqlExpr) {
        this.sqlExpr = sqlExpr;
    }

    public MongoExprAstVisitor(SqlExpr sqlExpr, Map<String, Object> dataMap) {
        this.sqlExpr = sqlExpr;
        if (MongoUtils.isVariableEnable(dataMap)) {
            this.enableVariable = true;
            this.dataMap = dataMap;
        }
    }

    /**
     *
     * @return
     */
    public Object visit() {
        return this.analyse(this.sqlExpr);
    }


    /**
     *
     * @return
     */
    public Object visitForValue() {
        if (sqlExpr instanceof SqlValuableExpr) {
            return visitValue((SqlValuableExpr)sqlExpr);
        }
        else if (sqlExpr instanceof SqlVariantRefExpr) {
            return MongoDataConverter.convertVariable(sqlExpr, dataMap);
        }
        throw  new RuntimeException("not support");
    }

    private Object analyse(SqlExpr sqlExpr) {
        return analyse(sqlExpr, ContextInfo.DEFAULT);
    }



    private Object analyse(SqlExpr sqlExpr, ContextInfo contextInfo) {
        if (sqlExpr instanceof SqlBinaryOpExpr) {
            return visitBinary((SqlBinaryOpExpr) sqlExpr);
        }
        else if (sqlExpr instanceof SqlIdentifierExpr) {
            return visitIdentify((SqlIdentifierExpr) sqlExpr);
        }
        else if (sqlExpr instanceof SqlValuableExpr) {
            return visitValue((SqlValuableExpr)sqlExpr, contextInfo);
        }
        else if (sqlExpr instanceof SqlVariantRefExpr) {
            return MongoDataConverter.convertVariable(sqlExpr, dataMap);
        }

        throw new RuntimeException("not support");
    }



    private Document visitBinary(SqlBinaryOpExpr sqlExpr) {
        SqlBinaryOperator sqlBinaryOperator = sqlExpr.getOperator();
        MongoOperator mongoOperator = MongoOperator.match(sqlBinaryOperator);

        if (mongoOperator.isLogical()) {
            Document document = new Document(mongoOperator.getExpr(),
                    Arrays.asList(analyse(sqlExpr.getLeft()), analyse(sqlExpr.getRight())));
            return document;
        } else if (mongoOperator.isCondition()) {

            return visitRelational(mongoOperator, sqlExpr);
        }
        throw new RuntimeException("not support");
    }


    private Document visitRelational(MongoOperator mongoOperator, SqlBinaryOpExpr sqlExpr) {

        Pair<Object, Object> pair = convertRelationalData(mongoOperator, sqlExpr);
        Object left = pair.getLeft();
        Object right = pair.getRight();

        if (mongoOperator.isCompare()) {
            Document document = new Document(mongoOperator.getExpr(), Arrays.asList(left, right));
            return new Document("$expr", document);
        }
        throw new RuntimeException("not support");

    }


    private Pair<Object, Object> convertRelationalData(MongoOperator mongoOperator, SqlBinaryOpExpr sqlExpr) {
        ExprTypeEnum leftType = ExprTypeEnum.match(sqlExpr.getLeft());
        ExprTypeEnum rightType = ExprTypeEnum.match(sqlExpr.getRight());
        // 判断左右两边是否有字段,需要上下文传递
        if (leftType == ExprTypeEnum.PARAM && rightType != ExprTypeEnum.PARAM) {
            SqlIdentifierExpr leftExpr = (SqlIdentifierExpr) sqlExpr.getLeft();
            Object left = visitIdentify(leftExpr, mongoOperator);

            ContextInfo contextInfo = new ContextInfo();
            contextInfo.autoGen = leftExpr.isAutoGen();
            Object right = analyse(sqlExpr.getRight(), contextInfo);
            return Pair.of(left, right);

        } else if (rightType == ExprTypeEnum.PARAM && leftType != ExprTypeEnum.PARAM) {
            SqlIdentifierExpr rightExpr = (SqlIdentifierExpr) sqlExpr.getRight();
            Object right = visitIdentify(rightExpr, mongoOperator);

            ContextInfo contextInfo = new ContextInfo();
            contextInfo.autoGen = rightExpr.isAutoGen();
            Object left = analyse(sqlExpr.getLeft(), contextInfo);
            return Pair.of(left, right);
        }
        return Pair.of(analyse(sqlExpr.getLeft()), analyse(sqlExpr.getRight()));
    }



    private Object visitIdentify(SqlIdentifierExpr sqlExpr) {
        return visitIdentify(sqlExpr, ContextInfo.DEFAULT);
    }

    private Object visitIdentify(SqlIdentifierExpr sqlExpr, MongoOperator mongoOperator) {
        ContextInfo identifyContext = new ContextInfo();
        identifyContext.fieldTag = mongoOperator.isFieldTag();
        return visitIdentify(sqlExpr, identifyContext);
    }


    private Object visitIdentify(SqlIdentifierExpr sqlExpr, ContextInfo contextInfo) {
        String field = sqlExpr.getName();
        if (contextInfo.fieldTag) {
            return "$" + field;
        }
        return field;
    }

    private Object visitValue(SqlValuableExpr sqlExpr) {
        return visitValue(sqlExpr, ContextInfo.DEFAULT);
    }

    private Object visitValue(SqlValuableExpr sqlExpr, ContextInfo contextInfo) {
        if (contextInfo.autoGen) {
            if (sqlExpr instanceof SqlCharExpr) {
                return MongoDataConverter.convertObjectId(sqlExpr.getValue());
            } else {
                throw new RuntimeException("not support");
            }
        }

        if (sqlExpr instanceof SqlDecimalExpr) {
            return MongoDataConverter.convertDecimal(((SqlDecimalExpr)sqlExpr).getValue());
        } else {
            return sqlExpr.getValue();
        }

    }


    /**
     * 上下文
     */
    private static class ContextInfo {
        // 自增
        public boolean autoGen = false;

        public boolean fieldTag = false;

        public static ContextInfo DEFAULT =  new ContextInfo();

    }


    /**
     *
     * @param <S>
     * @param <T>
     */
    private static class Pair<S, T> {
        private S left;

        private T right;

        public S getLeft() {
            return left;
        }

        public T getRight() {
            return right;
        }

        public Pair(S left , T right) {
            this.left = left;
            this.right = right;
        }

        public static <S, T> Pair<S, T> of (S left, T right) {
            return new Pair<>(left, right);
        }
    }
}
