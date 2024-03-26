package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.AbstractSqlNumericLiteralExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlDecimalExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * todo 整个expr解析逻辑还需要调整
 */
public class MongoExprAstVisitor {

    private static final Logger log = LoggerFactory.getLogger(MongoExprAstVisitor.class);

    public MongoExprAstVisitor() {

    }

    /**
     * @return
     */
    public static Object visit(SqlExpr sqlExpr) {
        return visit(sqlExpr, GlobalContext.getDefault());
    }

    /**
     * @return
     */
    public static Object visit(SqlExpr sqlExpr, Map<String, Object> dataMap) {
        return visit(sqlExpr, new GlobalContext(dataMap));
    }

    /**
     * @return
     */
    public static Object visit(SqlExpr sqlExpr, GlobalContext globalContext) {
        return analyse(sqlExpr, globalContext, InnerContext.getDefaultContextInfo());
    }


    private static Object analyse(SqlExpr sqlExpr, GlobalContext globalContext) {
        return analyse(sqlExpr, globalContext, InnerContext.getDefaultContextInfo());
    }


    private static Object analyse(SqlExpr sqlExpr, GlobalContext globalContext, InnerContext innerContext) {
        if (sqlExpr instanceof SqlBinaryOpExpr) {
            return visitBinary((SqlBinaryOpExpr) sqlExpr, globalContext, innerContext);
        } else if (sqlExpr instanceof SqlIdentifierExpr) {
            return visitIdentify((SqlIdentifierExpr) sqlExpr, globalContext, innerContext);
        } else if (sqlExpr instanceof SqlValuableExpr) {
            return visitValue((SqlValuableExpr) sqlExpr, globalContext, innerContext);
        } else if (sqlExpr instanceof SqlVariantRefExpr) {
            return visitVariable((SqlVariantRefExpr) sqlExpr, globalContext, innerContext);
        } else if (sqlExpr instanceof SqlInListExpr) {
            return visitList((SqlInListExpr) sqlExpr, globalContext);
        } else if (sqlExpr instanceof SqlPropertyExpr) {
            return visitProperty((SqlPropertyExpr) sqlExpr, globalContext, innerContext);
        }
        log.error("not support, ", sqlExpr);
        throw new RuntimeException("not support");
    }


    private static Document visitBinary(SqlBinaryOpExpr sqlExpr, GlobalContext globalContext, InnerContext innerContext) {
        SqlBinaryOperator sqlBinaryOperator = sqlExpr.getOperator();
        MongoOperator mongoOperator = MongoOperator.match(sqlBinaryOperator);

        if (mongoOperator.isLogical()) {
            Document document = new Document(mongoOperator.getExpr(),
                    Arrays.asList(analyse(sqlExpr.getLeft(), globalContext),
                            analyse(sqlExpr.getRight(), globalContext)));
            return document;
        } else if (mongoOperator.isCondition()) {
            return visitRelational(mongoOperator, sqlExpr, globalContext);
        }
        throw new RuntimeException("not support");
    }


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


    private static Pair<Object, Object> convertRelationalData(MongoOperator mongoOperator, SqlBinaryOpExpr sqlExpr, GlobalContext globalContext) {
        ExprTypeEnum leftType = ExprTypeEnum.match(sqlExpr.getLeft());
        ExprTypeEnum rightType = ExprTypeEnum.match(sqlExpr.getRight());
        // 判断左右两边是否有字段,需要上下文传递
        if (leftType == ExprTypeEnum.PARAM && rightType != ExprTypeEnum.PARAM) {
            SqlIdentifierExpr leftExpr = (SqlIdentifierExpr) sqlExpr.getLeft();
            Object left = visitIdentify(leftExpr, mongoOperator, globalContext);

            InnerContext contextInfo = new InnerContext();
            contextInfo.setAutoGen(leftExpr.isAutoGen());
            Object right = analyse(sqlExpr.getRight(), globalContext, contextInfo);
            return Pair.of(left, right);

        } else if (rightType == ExprTypeEnum.PARAM && leftType != ExprTypeEnum.PARAM) {
            SqlIdentifierExpr rightExpr = (SqlIdentifierExpr) sqlExpr.getRight();
            Object right = visitIdentify(rightExpr, mongoOperator, globalContext);

            InnerContext contextInfo = new InnerContext();
            contextInfo.setAutoGen(rightExpr.isAutoGen());
            Object left = analyse(sqlExpr.getLeft(), globalContext, contextInfo);
            return Pair.of(left, right);
        }
        return Pair.of(analyse(sqlExpr.getLeft(), globalContext), analyse(sqlExpr.getRight(), globalContext));
    }


    private static Object visitIdentify(SqlIdentifierExpr sqlExpr, MongoOperator mongoOperator, GlobalContext globalContext) {
        InnerContext identifyContext = new InnerContext();
        identifyContext.setFieldTag(mongoOperator.isFieldTag());
        return visitIdentify(sqlExpr, globalContext, identifyContext);
    }


    private static Object visitIdentify(SqlIdentifierExpr sqlExpr, GlobalContext globalContext, InnerContext innerContext) {
        String field = sqlExpr.getName();
        if (innerContext != null && innerContext.isFieldTag()) {
            return "$" + field;
        }
        return field;
    }


    private static Object visitValue(SqlValuableExpr sqlExpr, GlobalContext globalContext, InnerContext innerContext) {
        if (innerContext != null && innerContext.isAutoGen()) {
            if (sqlExpr instanceof SqlCharExpr) {
                return MongoDataConverter.convertObjectId(sqlExpr.getValue());
            } else {
                throw new RuntimeException("not support");
            }
        }

        if (sqlExpr instanceof SqlDecimalExpr) {
            return MongoDataConverter.convert(((SqlDecimalExpr) sqlExpr).getValue());
        } else if (sqlExpr instanceof AbstractSqlNumericLiteralExpr) {
            return MongoDataConverter.convert(((AbstractSqlNumericLiteralExpr) sqlExpr).getNumber());
        } else {
            return sqlExpr.getValue();
        }

    }

    private static Object visitVariable(SqlVariantRefExpr sqlExpr, GlobalContext globalContext, InnerContext innerContext) {
        Object value = MongoDataConverter.convertVariable(sqlExpr, globalContext.getDataMap());
        return getVariable(value, innerContext);
    }

    /**
     * 处理SqlVariantRefExpr 和 paramMap 替换后的值，可能为单项或列表
     *
     * @param value
     * @param contextInfo
     * @return
     */
    private static Object getVariable(Object value, InnerContext contextInfo) {
        // 如果变量塞列表
        if (value instanceof List) {
            List<Object> valList = new ArrayList<>();
            for (Object val : (List) value) {
                valList.add(getVariable(val, contextInfo));
            }
            return valList;
        }

        // 如果变量塞单项
        if (contextInfo.isAutoGen()) {
            if (value instanceof String) {
                return MongoDataConverter.convertObjectId(value);
            } else {
                throw new RuntimeException("not support");
            }
        }
        return MongoDataConverter.convert(value);
    }


    private static Object visitList(SqlInListExpr sqlExpr, GlobalContext globalContext) {
        SqlExpr leftExpr = sqlExpr.getLeft();
        InnerContext idContextInfo = InnerContext.getDefaultContextInfo();
        if (leftExpr instanceof SqlIdentifierExpr) {
            SqlIdentifierExpr sqlIdentifierExpr = (SqlIdentifierExpr) leftExpr;
            if (sqlIdentifierExpr.isAutoGen()) {
                idContextInfo.setAutoGen(true);
            }
        }
        InnerContext fieldContextInfo = InnerContext.getFieldTagContext();
        Object left = analyse(leftExpr, globalContext, fieldContextInfo);
        List<SqlExpr> targetList = sqlExpr.getTargetList();
        List<Object> rightValues = new ArrayList<>();
        for (SqlExpr item : targetList) {
            if (item instanceof SqlVariantRefExpr) {
                Object val = visitVariable((SqlVariantRefExpr) item, globalContext, idContextInfo);
                if (val instanceof List) {
                    rightValues.addAll((List) val);
                } else {
                    rightValues.add(val);
                }
            } else {
                rightValues.add(analyse(item, globalContext, idContextInfo));
            }
        }
        Document document = new Document(MongoOperator.IN.getExpr(), Arrays.asList(left, rightValues));
        return new Document("$expr", document);
    }

    // 目前property只会有两层
    private static Object visitProperty(SqlPropertyExpr sqlPropertyExpr, GlobalContext globalContext, InnerContext contextInfo) {
        SqlName sqlName = sqlPropertyExpr.getOwner();
        StringBuilder propertyNameBuilder = new StringBuilder();
        if (sqlName instanceof SqlIdentifierExpr) {
            propertyNameBuilder.append(visitIdentify((SqlIdentifierExpr) sqlName, globalContext, contextInfo));
        } else {
            throw new RuntimeException("NOT SUPPORT");
        }
        propertyNameBuilder.append(".").append(sqlPropertyExpr.getName());
        String propertyName = propertyNameBuilder.toString();
        // join替换
        if (globalContext != null && globalContext.getJoinInfo() != null) {
            // 替换
            JoinInfo joinInfo = globalContext.getJoinInfo();
            propertyName = joinInfo.getConditionReplaceMap().get(propertyName);
        }
        return propertyName;
    }


}
