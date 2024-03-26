package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlLikeOpExpr;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoExpressionVisitor {

    private static final List<String> ESCAPED_TAGS = Arrays.asList("\\", "$", "(", ")", "*", "+", ".", "[", "]", ">", "?", "{", "}", "|");

    /**
     * 解析操作
     *
     * @param sqlExpr
     * @param globalContext
     * @param innerContext
     * @return
     */
    public static Document visitBinary(SqlBinaryOpExpr sqlExpr, GlobalContext globalContext, VisitContext innerContext) {
        SqlBinaryOperator sqlBinaryOperator = sqlExpr.getOperator();
        MongoOperator mongoOperator = MongoOperator.match(sqlBinaryOperator);

        if (mongoOperator.isLogical()) {
            Document document = new Document(mongoOperator.getExpr(),
                    Arrays.asList(MongoExprVisitor.visit(sqlExpr.getLeft(), globalContext),
                            MongoExprVisitor.visit(sqlExpr.getRight(), globalContext)));
            return document;
        } else if (mongoOperator.isCondition()) {
            return visitRelational(mongoOperator, sqlExpr, globalContext);
        }
        throw new RuntimeException("not support");
    }


    /**
     * 解析列表
     *
     * @param sqlExpr
     * @param globalContext
     * @return
     */
    public static Object visitList(SqlInListExpr sqlExpr, GlobalContext globalContext) {
        SqlExpr leftExpr = sqlExpr.getLeft();
        VisitContext idContextInfo = VisitContext.getDefaultContextInfo();
        if (leftExpr instanceof SqlIdentifierExpr) {
            SqlIdentifierExpr sqlIdentifierExpr = (SqlIdentifierExpr) leftExpr;
            if (sqlIdentifierExpr.isAutoGen()) {
                idContextInfo.setAutoGen(true);
            }
        }
        VisitContext fieldContextInfo = VisitContext.getFieldTagContext();
        Object left = MongoExprVisitor.visit(leftExpr, globalContext, fieldContextInfo);
        List<SqlExpr> targetList = sqlExpr.getTargetList();
        List<Object> rightValues = new ArrayList<>();
        for (SqlExpr item : targetList) {
            if (item instanceof SqlVariantRefExpr) {
                Object val = MongoExprVisitor.visit((SqlVariantRefExpr) item, globalContext, idContextInfo);
                if (val instanceof List) {
                    rightValues.addAll((List) val);
                } else {
                    rightValues.add(val);
                }
            } else {
                rightValues.add(MongoExprVisitor.visit(item, globalContext, idContextInfo));
            }
        }
        Document document = new Document(MongoOperator.IN.getExpr(), Arrays.asList(left, rightValues));
        if (sqlExpr.not) {
            return new Document("$expr", new Document("$nor", Arrays.asList(document)));
        } else {
            return new Document("$expr", document);
        }
    }

    /**
     * @param sqlLikeOpExpr
     * @param globalContext
     * @return
     */
    public static Document visitLike(SqlLikeOpExpr sqlLikeOpExpr, GlobalContext globalContext) {
        Object left = MongoExprVisitor.visit(sqlLikeOpExpr.getLeft(), globalContext);
        String field = String.valueOf(left);
        Object right = MongoExprVisitor.visit(sqlLikeOpExpr.getRight(), globalContext);
        Object regexValue = parseRegexValue(right);
        Document document = new Document(field, new Document(MongoOperator.LIKE.getExpr(), regexValue));
        if (sqlLikeOpExpr.not) {
            return new Document("$nor", Arrays.asList(document));
        } else {
            return document;
        }
    }


    private static Object parseRegexValue(Object value) {
        String escapedValue = String.valueOf(value);
        for (String item : ESCAPED_TAGS) {
            if (escapedValue.contains(item)) {
                escapedValue = escapedValue.replace(item, "\\" + item);
            }
        }
        if (escapedValue.startsWith("%") && escapedValue.endsWith("%")) {
            if (escapedValue.length() < 2) {
                return "";
            }
            return escapedValue.substring(1, escapedValue.length() - 1);
        }

        if (escapedValue.startsWith("%")) {
            return escapedValue.substring(1) + "$";
        }

        if (escapedValue.endsWith("%")) {
            return "^" + escapedValue.substring(0, escapedValue.length() - 1);
        }
        return escapedValue;
    }


    /**
     * @param mongoOperator
     * @param sqlExpr
     * @param globalContext
     * @return
     */
    private static Document visitRelational(MongoOperator mongoOperator, SqlBinaryOpExpr sqlExpr, GlobalContext globalContext) {

        Pair<Object, Object> pair = convertRelationalData(mongoOperator, sqlExpr, globalContext);
        Object left = pair.getLeft();
        Object right = pair.getRight();

        if (mongoOperator.isCompare()) {
            Document document = new Document(mongoOperator.getExpr(), Arrays.asList(left, right));
            return new Document("$expr", document);
        }
        throw new RuntimeException("not support");

    }


    /**
     * @param mongoOperator
     * @param sqlExpr
     * @param globalContext
     * @return
     */
    private static Pair<Object, Object> convertRelationalData(MongoOperator mongoOperator, SqlBinaryOpExpr sqlExpr, GlobalContext globalContext) {
        ExprTypeEnum leftType = ExprTypeEnum.match(sqlExpr.getLeft());
        ExprTypeEnum rightType = ExprTypeEnum.match(sqlExpr.getRight());
        // 判断左右两边是否有字段,需要上下文传递
        if (leftType == ExprTypeEnum.PARAM && rightType != ExprTypeEnum.PARAM) {
            SqlIdentifierExpr leftExpr = (SqlIdentifierExpr) sqlExpr.getLeft();
            Object left = visitIdentify(leftExpr, mongoOperator, globalContext);

            VisitContext contextInfo = new VisitContext();
            contextInfo.setAutoGen(leftExpr.isAutoGen());
            Object right = MongoExprVisitor.visit(sqlExpr.getRight(), globalContext, contextInfo);
            return Pair.of(left, right);

        } else if (rightType == ExprTypeEnum.PARAM && leftType != ExprTypeEnum.PARAM) {
            SqlIdentifierExpr rightExpr = (SqlIdentifierExpr) sqlExpr.getRight();
            Object right = visitIdentify(rightExpr, mongoOperator, globalContext);

            VisitContext contextInfo = new VisitContext();
            contextInfo.setAutoGen(rightExpr.isAutoGen());
            Object left = MongoExprVisitor.visit(sqlExpr.getLeft(), globalContext, contextInfo);
            return Pair.of(left, right);
        }
        return Pair.of(MongoExprVisitor.visit(sqlExpr.getLeft(), globalContext), MongoExprVisitor.visit(sqlExpr.getRight(), globalContext));
    }


    /**
     * @param sqlExpr
     * @param mongoOperator
     * @param globalContext
     * @return
     */
    private static Object visitIdentify(SqlIdentifierExpr sqlExpr, MongoOperator mongoOperator, GlobalContext globalContext) {
        VisitContext identifyContext = new VisitContext();
        identifyContext.setFieldTag(mongoOperator.isFieldTag());
        return MongoExprVisitor.visit(sqlExpr, globalContext, identifyContext);
    }


}
