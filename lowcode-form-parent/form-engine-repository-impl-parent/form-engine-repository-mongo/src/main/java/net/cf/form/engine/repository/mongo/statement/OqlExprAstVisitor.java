package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlValuableExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlPropertyExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOperator;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlNotExpr;
import net.cf.form.engine.repository.util.CommaSeperatedListOutput;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;
import org.bson.Document;
import org.bson.types.Decimal128;

import java.math.BigDecimal;
import java.util.*;

/**
 * OQL语句中的表达式访问器
 *
 * @author clouds
 */
public class OqlExprAstVisitor {

    private DataObject dataObject;

    private QqlExpr expr;

    private AbstractQueryableSqlInfo sqlInfo;

    public OqlExprAstVisitor(DataObject dataObject, QqlExpr expr, AbstractQueryableSqlInfo sqlInfo) {
        this.dataObject = dataObject;
        this.expr = expr;
        this.sqlInfo = sqlInfo;
    }

    /**
     * 获取表达式产生的SQL语句
     *
     * @return
     */
    public void visit() {
        Document document = (Document) this.getOql(this.expr);
        this.sqlInfo.setWhere(document);
    }

    /**
     * 访问一个表达式
     *
     * @param x
     */
    private Object getOql(QqlExpr x) {
        Class<?> clazz = x.getClass();
        if (clazz == OqlCharExpr.class) {
            return this.getOql((OqlCharExpr) x);
        } else if (clazz == OqlBooleanExpr.class) {
            return this.getOql((OqlBooleanExpr) x);
        } else if (clazz == OqlIntegerExpr.class) {
            return this.getOql((OqlIntegerExpr) x);
        } else if (clazz == OqlNumberExpr.class) {
            return this.getOql((OqlNumberExpr) x);
        } else if (clazz == OqlNullExpr.class) {
            return this.getOql((OqlNullExpr) x);
        } else if (clazz == OqlJsonObjectExpr.class) {
            return this.getOql((OqlJsonObjectExpr) x);
        } else if (clazz == OqlJsonArrayExpr.class) {
            return this.getOql((OqlJsonArrayExpr) x);
        } else if (clazz == OqlIdentifierExpr.class) {
            return this.getOql((OqlIdentifierExpr) x);
        } else if (clazz == OqlPropertyExpr.class) {
            return this.getOql((OqlPropertyExpr) x);
        } else if (clazz == OqlNotExpr.class) {
            return this.getOql((OqlNotExpr) x);
        } else if (clazz == OqlBinaryOpExpr.class) {
            return this.getOql((OqlBinaryOpExpr) x);
        } else if (clazz == OqlMethodInvokeExpr.class) {
            return this.getOql((OqlMethodInvokeExpr) x);
        } else {
            throw new RuntimeException("不接受的表达式类型:" + x.getClass());
        }
    }

    /**
     * 访问一个字符串
     *
     * @param x
     * @return
     */
    private Object getOql(OqlCharExpr x) {
        return x.getValue();
    }

    /**
     * 访问一个布尔
     *
     * @param x
     * @return
     */
    private Object getOql(OqlBooleanExpr x) {
        return x.getValue();
    }

    /**
     * 访问一个整数
     *
     * @param x
     * @return
     */
    private Object getOql(OqlIntegerExpr x) {
        return x.getValue();
    }

    /**
     * 访问一个整数
     *
     * @param x
     * @return
     */
    private Object getOql(OqlNumberExpr x) {
        return new Decimal128(new BigDecimal(String.valueOf(x.getValue())));
    }

    /**
     * 访问一个NULL
     *
     * @param x
     * @return
     */
    private Object getOql(OqlNullExpr x) {
        return null;
    }

    /**
     * 访问一个JSON对象
     *
     * @param x
     * @return
     */
    // todo
    private Object getOql(OqlJsonObjectExpr x) {
        return analyseJsonObjectFieldData(x);
    }


    /**
     * 访问一个JSON数据
     *
     * @param x
     * @return
     */
    // todo
    private Object getOql(OqlJsonArrayExpr x) {
        return analyseListFieldData(x);
    }

    /**
     * 访问一个标识符
     *
     * @param x
     * @return
     */
    private Object getOql(OqlIdentifierExpr x) {
        String name = x.getName();
        DataField field = this.dataObject.getField(name);
        if (field != null) {
            return "$" + field.getColumnName();
        } else {
            return name;
        }
    }

    /**
     * 访问一个属性标识符
     *
     * @param x
     * @return
     */
    private Object getOql(OqlPropertyExpr x) {
        StringBuilder builder = new StringBuilder();
        builder.append(x.getOwner()).append(".").append(x.getName());
        return builder.toString();
    }


    /**
     * 访问一个非表达式
     *
     * @param x
     * @return
     */
    private Object getOql(OqlNotExpr x) {
        StringBuilder builder = new StringBuilder();
        builder.append("not (").append(x.getExpr()).append(")");
        return builder.toString();
    }

    /**
     * 访问一个二元操作
     *
     * @param x
     * @return
     */
    private Document getOql(OqlBinaryOpExpr x) {
        Object left = this.getOql(x.getLeft());
        Object right = this.getOql(x.getRight());
        Document document = new Document(getMongoOpeartor(x.getOperator()), Arrays.asList(left, right));
        return document;
    }

    /**
     * 访问一个方法调用
     *
     * @param x
     * @return
     */
    // todo
    private Object getOql(OqlMethodInvokeExpr x) {
        StringBuilder builder = new StringBuilder();
        builder.append(x.getMethodName()).append("(");
        CommaSeperatedListOutput.output(builder, x.getArguments(), (argument) -> {
            this.getOql(argument);
        });
        builder.append(")");
        return builder.toString();
    }


    private String getMongoOpeartor(OqlBinaryOperator opeartor) {

        switch (opeartor) {
            case Equal:
                return "$eq";
            case GreaterThan:
                return "$gt";
            case And:
                return "$and";
            default:
                throw new RuntimeException();
        }
    }




    private Object analyseJsonObjectFieldData(OqlJsonObjectExpr value) {
        Map<String, Object> res = new HashMap<>();

        for (Map.Entry<String, QqlExpr> entry : value.getItems().entrySet()) {
            QqlExpr qqlExpr = entry.getValue();
            res.put(entry.getKey(), analyseFieldData(qqlExpr));
        }
        return res;
    }

    private Object analyseListFieldData(OqlListExpr expr) {
        List<Object> res = new ArrayList<>();
        for (QqlExpr qqlExpr : expr.getItems()) {
            res.add(analyseFieldData(qqlExpr));
        }
        return res;
    }

    private Object analyseListFieldData(OqlJsonArrayExpr expr) {
        List<Object> res = new ArrayList<>();
        for (QqlExpr qqlExpr : expr.getItems()) {
            res.add(analyseFieldData(qqlExpr));
        }
        return res;
    }

    /**
     * todo
     * @param qqlExpr
     * @return
     */
    private Object analyseFieldData(QqlExpr qqlExpr) {
        if (qqlExpr instanceof OqlJsonObjectExpr) {
            return analyseJsonObjectFieldData((OqlJsonObjectExpr) qqlExpr);
        } else if (qqlExpr instanceof OqlListExpr) {
            return analyseListFieldData((OqlListExpr) qqlExpr);
        } else if (qqlExpr instanceof QqlValuableExpr) {
            return ((QqlValuableExpr) qqlExpr).getValue();
        }
        return null;
    }
}
