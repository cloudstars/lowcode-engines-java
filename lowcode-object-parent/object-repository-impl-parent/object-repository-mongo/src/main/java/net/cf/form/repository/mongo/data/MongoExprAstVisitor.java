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

    private Map<String, Object> dataMap;

    private VisitContextInfo visitContextInfo;

    private boolean enableVariable = false;


    public MongoExprAstVisitor() {

    }

    public MongoExprAstVisitor(Map<String, Object> dataMap) {
        if (MongoUtils.isVariableEnable(dataMap)) {
            this.enableVariable = true;
            this.dataMap = dataMap;
        }
    }

    public MongoExprAstVisitor(Map<String, Object> dataMap, VisitContextInfo visitContextInfo) {
        this.visitContextInfo = visitContextInfo;
        if (MongoUtils.isVariableEnable(dataMap)) {
            this.enableVariable = true;
            this.dataMap = dataMap;
        }
    }

    /**
     * @return
     */
    public Object visit(SqlExpr sqlExpr) {
        return this.analyse(sqlExpr);
    }


    private Object analyse(SqlExpr sqlExpr) {
        return analyse(sqlExpr, InnerContextInfo.getDefaultContextInfo());
    }


    private Object analyse(SqlExpr sqlExpr, InnerContextInfo contextInfo) {
        if (sqlExpr instanceof SqlBinaryOpExpr) {
            return visitBinary((SqlBinaryOpExpr) sqlExpr);
        } else if (sqlExpr instanceof SqlIdentifierExpr) {
            return visitIdentify((SqlIdentifierExpr) sqlExpr, contextInfo);
        } else if (sqlExpr instanceof SqlValuableExpr) {
            return visitValue((SqlValuableExpr) sqlExpr, contextInfo);
        } else if (sqlExpr instanceof SqlVariantRefExpr) {
            return visitVariable((SqlVariantRefExpr) sqlExpr, contextInfo);
        } else if (sqlExpr instanceof SqlInListExpr) {
            return visitList((SqlInListExpr) sqlExpr, contextInfo);
        } else if (sqlExpr instanceof SqlPropertyExpr) {
            return visitProperty((SqlPropertyExpr) sqlExpr);
        }

        log.error("not support, ", sqlExpr);
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

            InnerContextInfo contextInfo = new InnerContextInfo();
            contextInfo.setAutoGen(leftExpr.isAutoGen());
            Object right = analyse(sqlExpr.getRight(), contextInfo);
            return Pair.of(left, right);

        } else if (rightType == ExprTypeEnum.PARAM && leftType != ExprTypeEnum.PARAM) {
            SqlIdentifierExpr rightExpr = (SqlIdentifierExpr) sqlExpr.getRight();
            Object right = visitIdentify(rightExpr, mongoOperator);

            InnerContextInfo contextInfo = new InnerContextInfo();
            contextInfo.setAutoGen(rightExpr.isAutoGen());
            Object left = analyse(sqlExpr.getLeft(), contextInfo);
            return Pair.of(left, right);
        }
        return Pair.of(analyse(sqlExpr.getLeft()), analyse(sqlExpr.getRight()));
    }


    private Object visitIdentify(SqlIdentifierExpr sqlExpr) {
        // 默认添加$
        return visitIdentify(sqlExpr, InnerContextInfo.getDefaultContextInfo());
    }


    private Object visitIdentify(SqlIdentifierExpr sqlExpr, MongoOperator mongoOperator) {
        InnerContextInfo identifyContext = new InnerContextInfo();
        identifyContext.setFieldTag(mongoOperator.isFieldTag());
        return visitIdentify(sqlExpr, identifyContext);
    }


    private Object visitIdentify(SqlIdentifierExpr sqlExpr, InnerContextInfo contextInfo) {
        String field = sqlExpr.getName();
        if (contextInfo.isFieldTag()) {
            return "$" + field;
        }
        return field;
    }


    private Object visitValue(SqlValuableExpr sqlExpr, InnerContextInfo contextInfo) {
        if (contextInfo.isAutoGen()) {
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

    private Object visitVariable(SqlVariantRefExpr sqlExpr, InnerContextInfo contextInfo) {
        Object value = MongoDataConverter.convertVariable(sqlExpr, dataMap);
        return getVariable(value, contextInfo);
    }

    /**
     * 处理SqlVariantRefExpr 和 paramMap 替换后的值，可能为单项或列表
     *
     * @param value
     * @param contextInfo
     * @return
     */
    private Object getVariable(Object value, InnerContextInfo contextInfo) {
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


    private Object visitList(SqlInListExpr sqlExpr, InnerContextInfo contextInfo) {
        SqlExpr leftExpr = sqlExpr.getLeft();
        InnerContextInfo idContextInfo = InnerContextInfo.getDefaultContextInfo();
        if (leftExpr instanceof SqlIdentifierExpr) {
            SqlIdentifierExpr sqlIdentifierExpr = (SqlIdentifierExpr) leftExpr;
            if (sqlIdentifierExpr.isAutoGen()) {
                idContextInfo.setAutoGen(true);
            }
        }
        InnerContextInfo fieldContextInfo = InnerContextInfo.getFieldTagContext();
        Object left = analyse(leftExpr, fieldContextInfo);
        List<SqlExpr> targetList = sqlExpr.getTargetList();
        List<Object> rightValues = new ArrayList<>();
        for (SqlExpr item : targetList) {
            if (item instanceof SqlVariantRefExpr) {
                Object val = visitVariable((SqlVariantRefExpr) item, idContextInfo);
                if (val instanceof List) {
                    rightValues.addAll((List) val);
                } else {
                    rightValues.add(val);
                }
            } else {
                rightValues.add(analyse(item, idContextInfo));
            }
        }
        Document document = new Document(MongoOperator.IN.getExpr(), Arrays.asList(left, rightValues));
        return new Document("$expr", document);
    }


    private Object visitProperty(SqlPropertyExpr sqlPropertyExpr) {
        SqlName sqlName = sqlPropertyExpr.getOwner();
        StringBuilder propertyNameBuilder = new StringBuilder();
        if (sqlName instanceof SqlIdentifierExpr) {
            propertyNameBuilder.append(visitIdentify((SqlIdentifierExpr) sqlName));
        } else {
            throw new RuntimeException("NOT SUPPORT");
        }
        propertyNameBuilder.append(".").append(sqlPropertyExpr.getName());
        String propertyName = propertyNameBuilder.toString();
        if (this.visitContextInfo != null && this.visitContextInfo.getJoinInfo() != null) {
            // 替换
            JoinInfo joinInfo = this.visitContextInfo.getJoinInfo();
            propertyName = joinInfo.getConditionReplaceMap().get(propertyName);
        }
        return propertyName;
    }

}
