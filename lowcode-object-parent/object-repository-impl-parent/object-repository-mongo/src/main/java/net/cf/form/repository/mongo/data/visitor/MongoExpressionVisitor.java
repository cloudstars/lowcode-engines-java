package net.cf.form.repository.mongo.data.visitor;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.op.*;
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
    public static Object visitBinary(SqlBinaryOpExpr sqlExpr, GlobalContext globalContext, VisitContext innerContext) {
        // like todo
        if (sqlExpr instanceof SqlLikeOpExpr) {
            return visitLike((SqlLikeOpExpr) sqlExpr, globalContext);
        }
        // in
        if (sqlExpr instanceof SqlInListExpr) {
            return visitIn((SqlInListExpr) sqlExpr, globalContext);
        }
        // contains
        if (sqlExpr instanceof SqlContainsOpExpr || sqlExpr instanceof SqlArrayContainsOpExpr) {
            return visitContains(sqlExpr, globalContext, innerContext);
        }

        SqlBinaryOperator sqlBinaryOperator = sqlExpr.getOperator();
        MongoOperator mongoOperator = MongoOperator.match(sqlBinaryOperator);
        if (mongoOperator.isLogical()) {
            // 逻辑
            return visitLogical(mongoOperator, sqlExpr, globalContext);
        } else if (mongoOperator.isCondition()) {
            // 关系
            return visitRelational(mongoOperator, sqlExpr, globalContext);
        } else if (mongoOperator.isCompute()) {
            // 运算
            return visitCompute(mongoOperator, sqlExpr, globalContext);
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
    public static Object visitIn(SqlInListExpr sqlExpr, GlobalContext globalContext) {
        SqlExpr leftExpr = sqlExpr.getLeft();
        VisitContext idContextInfo = VisitContext.getDefaultContextInfo();
        VisitContext fieldContextInfo = VisitContext.getDefaultContextInfo();

        if (globalContext.getPositionEnum() == PositionEnum.JOIN) {
            if (sqlExpr.getLeft() instanceof SqlPropertyExpr) {
                fieldContextInfo.setFieldTag(true);
            }
        } else {
            if (sqlExpr.getLeft() instanceof SqlIdentifierExpr) {
                fieldContextInfo.setFieldTag(true);
                idContextInfo.setAutoGen(((SqlIdentifierExpr) sqlExpr.getLeft()).isAutoGen());
            }
        }

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
            // {"$expr":{"$not":{"$in":[param, []]}}}
            return new Document("$expr", new Document("$not", document));
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
            // {"$nor":[{"param":{"$regex", ""}}]}
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

        if (mongoOperator == MongoOperator.IS || mongoOperator == MongoOperator.IS_NOT) {
            return visitIsOp(mongoOperator, sqlExpr, left, right, globalContext);
        } else if (mongoOperator.isCompare()) {
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
//        ExprTypeEnum leftType = ExprTypeEnum.match(sqlExpr.getLeft());
//        ExprTypeEnum rightType = ExprTypeEnum.match(sqlExpr.getRight());
//        // 判断左右两边是否有字段,需要上下文传递
//        if (leftType == ExprTypeEnum.PARAM && rightType != ExprTypeEnum.PARAM) {
//            SqlIdentifierExpr leftExpr = (SqlIdentifierExpr) sqlExpr.getLeft();
//            Object left = visitBinaryIdentify(leftExpr, mongoOperator, globalContext);
//
//            VisitContext contextInfo = new VisitContext();
//            contextInfo.setAutoGen(leftExpr.isAutoGen());
//            Object right = MongoExprVisitor.visit(sqlExpr.getRight(), globalContext, contextInfo);
//            return Pair.of(left, right);
//
//        } else if (rightType == ExprTypeEnum.PARAM && leftType != ExprTypeEnum.PARAM) {
//            SqlIdentifierExpr rightExpr = (SqlIdentifierExpr) sqlExpr.getRight();
//            Object right = visitBinaryIdentify(rightExpr, mongoOperator, globalContext);
//
//            VisitContext contextInfo = new VisitContext();
//            contextInfo.setAutoGen(rightExpr.isAutoGen());
//            Object left = MongoExprVisitor.visit(sqlExpr.getLeft(), globalContext, contextInfo);
//            return Pair.of(left, right);
//        }

        VisitContext leftContextInfo = new VisitContext();
        VisitContext rightContextInfo = new VisitContext();

        if (globalContext.getPositionEnum() == PositionEnum.JOIN) {
            if (sqlExpr.getLeft() instanceof SqlPropertyExpr) {
                leftContextInfo.setFieldTag(mongoOperator.isFieldTag());
            }
            if (sqlExpr.getRight() instanceof SqlPropertyExpr) {
                rightContextInfo.setFieldTag(mongoOperator.isFieldTag());
            }
        } else {
            if (sqlExpr.getLeft() instanceof SqlIdentifierExpr) {
                leftContextInfo.setFieldTag(mongoOperator.isFieldTag());
                rightContextInfo.setAutoGen(((SqlIdentifierExpr) sqlExpr.getLeft()).isAutoGen());
            }
            if (sqlExpr.getRight() instanceof SqlIdentifierExpr) {
                rightContextInfo.setFieldTag(mongoOperator.isFieldTag());
                leftContextInfo.setAutoGen(((SqlIdentifierExpr) sqlExpr.getLeft()).isAutoGen());
            }
        }

        // 如果两边都不是字段，暂时不支持往下检索
        return Pair.of(MongoExprVisitor.visit(sqlExpr.getLeft(), globalContext, leftContextInfo), MongoExprVisitor.visit(sqlExpr.getRight(), globalContext, rightContextInfo));
    }


    /**
     * @param mongoOperator
     * @param sqlExpr
     * @param left
     * @param right
     * @param globalContext
     * @return
     */
    private static Document visitIsOp(MongoOperator mongoOperator, SqlBinaryOpExpr sqlExpr, Object left, Object right, GlobalContext globalContext) {
        Document document = new Document();
        String field = String.valueOf(left);
        if (right != null) {
            throw new RuntimeException("只支持is null 或 is not null");
        }

        if (mongoOperator == MongoOperator.IS) {
            // is null
            // 字段不存在或字段的值为null
            List<Document> documents = new ArrayList<>();
            Document existDoc = new Document(field, new Document("$exists", false));
            Document nullDoc = new Document("$expr", new Document("$eq", Arrays.asList("$" + field, null)));
            documents.add(existDoc);
            documents.add(nullDoc);
            document.put("$or", documents);
            return document;
        } else {
            // is not null
            // 字段存在且字段的值部位null
            List<Document> documents = new ArrayList<>();
            Document existDoc = new Document(field, new Document("$exists", true));
            Document notNullDoc = new Document("$expr", new Document("$ne", Arrays.asList("$" + field, null)));
            documents.add(existDoc);
            documents.add(notNullDoc);
            document.put("$and", documents);
            return document;
        }

    }

    /**
     * @param sqlExpr
     * @param globalContext
     * @param visitContext
     * @return
     */
    private static Document visitContains(SqlBinaryOpExpr sqlExpr, GlobalContext globalContext, VisitContext visitContext) {
        Pair<Object, Object> pair = convertRelationalData(MongoOperator.CONTAINS, sqlExpr, globalContext);
        Object left = pair.getLeft();
        Object right = pair.getRight();

        if (sqlExpr instanceof SqlContainsOpExpr) {
            return buildContainsSingle(sqlExpr, left, right, globalContext);
        }

        SqlArrayContainsOpExpr sqlArrayContainsOpExpr = (SqlArrayContainsOpExpr) sqlExpr;
        if (sqlArrayContainsOpExpr.getOption() == SqlContainsOption.ALL) {
            return buildContainsAll(sqlExpr, left, right, globalContext);
        } else {
            return buildContainsAny(sqlExpr, left, right, globalContext);
        }
    }

    /**
     * @param sqlExpr
     * @param left
     * @param right
     * @param globalContext
     * @return
     */
    private static Document buildContainsAll(SqlBinaryOpExpr sqlExpr, Object left, Object right, GlobalContext globalContext) {
        return new Document(String.valueOf(left), new Document("$all", Arrays.asList(right)));
    }

    /**
     * @param sqlExpr
     * @param left
     * @param right
     * @param globalContext
     * @return
     */
    private static Document buildContainsAny(SqlBinaryOpExpr sqlExpr, Object left, Object right, GlobalContext globalContext) {
        return new Document(String.valueOf(left), new Document("$eleMatch", new Document("$in", right)));
    }

    /**
     * @param sqlExpr
     * @param left
     * @param right
     * @param globalContext
     * @return
     */
    private static Document buildContainsSingle(SqlBinaryOpExpr sqlExpr, Object left, Object right, GlobalContext globalContext) {
        return new Document(String.valueOf(left), new Document("$all", Arrays.asList(right)));
    }


    /**
     * @param mongoOperator
     * @param sqlExpr
     * @param globalContext
     * @return
     */
    private static Document visitCompute(MongoOperator mongoOperator, SqlBinaryOpExpr sqlExpr, GlobalContext globalContext) {
        Document document = new Document();
        Object left = MongoExprVisitor.visit(sqlExpr.getLeft(), globalContext);
        Object right = MongoExprVisitor.visit(sqlExpr.getRight(), globalContext);
        document.put(mongoOperator.getExpr(), Arrays.asList(left, right));
        return document;
    }

    /**
     * @param mongoOperator
     * @param sqlExpr
     * @param globalContext
     * @return
     */
    private static Document visitLogical(MongoOperator mongoOperator, SqlBinaryOpExpr sqlExpr, GlobalContext globalContext) {
        Document document = new Document();
        Object left = MongoExprVisitor.visit(sqlExpr.getLeft(), globalContext);
        Object right = MongoExprVisitor.visit(sqlExpr.getRight(), globalContext);
        document.put(mongoOperator.getExpr(), Arrays.asList(left, right));
        return document;
    }

    /**
     * @param sqlExpr
     * @param mongoOperator
     * @param globalContext
     * @return
     */
    private static Object visitBinaryIdentify(SqlIdentifierExpr sqlExpr, MongoOperator mongoOperator, GlobalContext globalContext) {
        VisitContext identifyContext = new VisitContext();

        // 表达式中可能需要添加fieldTag
        if (mongoOperator.isCompute() || mongoOperator.isCompare()) {
            identifyContext.setFieldTag(true);
        }

        return MongoExprVisitor.visit(sqlExpr, globalContext, identifyContext);
    }


}
