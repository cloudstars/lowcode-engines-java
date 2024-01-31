package net.cf.form.engine.repository.mysql.statement;

import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.*;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlInOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlLikeOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlNotExpr;
import net.cf.form.engine.repository.oql.parser.Token;
import net.cf.form.engine.repository.util.CommaSeperatedListOutput;
import net.cf.form.engine.repository.oql.ast.expr.identifier.*;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;

import java.util.Map;

/**
 * OQL语句中的表达式访问器
 *
 * @author clouds
 */
public final class OqlExprAstVisitor {

    /**
     * 当前正在解析的数据对象
     */
    private DataObject object;

    public OqlExprAstVisitor(DataObject object) {
        this.object = object;
    }

    /**
     * 访问一个表达式
     *
     * @param x
     * @return 表达式生成的SQL
     */
    public String getSql(QqlExpr x) {
        StringBuilder builder = new StringBuilder();
        append(x, builder);
        return builder.toString();
    }

    /**
     * 生成表达式的SQL，输出到缓存
     *
     * @param x
     * @param builder
     */
    public void append(QqlExpr x, StringBuilder builder) {
        Class<?> clazz = x.getClass();
        if (clazz == OqlCharExpr.class) {
            this.append((OqlCharExpr) x, builder);
        } else if (clazz == OqlBooleanExpr.class) {
            this.append((OqlBooleanExpr) x, builder);
        } else if (clazz == OqlIntegerExpr.class) {
            this.append((OqlIntegerExpr) x, builder);
        } else if (clazz == OqlNumberExpr.class) {
            this.append((OqlNumberExpr) x, builder);
        } else if (clazz == OqlNullExpr.class) {
            this.append((OqlNullExpr) x, builder);
        } else if (clazz == OqlJsonObjectExpr.class) {
            this.append((OqlJsonObjectExpr) x, builder);
        } else if (clazz == OqlJsonArrayExpr.class) {
            this.append((OqlJsonArrayExpr) x, builder);
        } else if (clazz == OqlIdentifierExpr.class) {
            this.append((OqlIdentifierExpr) x, builder);
        } else if (clazz == OqlPropertyExpr.class) {
            this.append((OqlPropertyExpr) x, builder);
        } else if (clazz == OqlVariantRefExpr.class) {
            this.append((OqlVariantRefExpr) x, builder);
        } else if (clazz == OqlNotExpr.class) {
            this.append((OqlNotExpr) x, builder);
        } else if (clazz == OqlBinaryOpExpr.class) {
            this.append((OqlBinaryOpExpr) x, builder);
        } else if (clazz == OqlMethodInvokeExpr.class) {
            this.append((OqlMethodInvokeExpr) x, builder);
        } else if (clazz == OqlAggregateExpr.class) {
            this.append((OqlAggregateExpr) x, builder);
        } else if (clazz == OqlInOpExpr.class) {
            this.getSql((OqlInOpExpr) x, builder);
        } else if (clazz == OqlListExpr.class) {
            this.getSql((OqlListExpr) x, builder);
        } else if (clazz == OqlLikeOpExpr.class) {
            this.getSql((OqlLikeOpExpr) x, builder);
        } else if (clazz == OqlAllFieldExpr.class) {
            builder.append(Token.STAR.name);
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
    private void append(OqlCharExpr x, StringBuilder builder) {
        builder.append("'").append(x.getValue()).append("'");
    }

    /**
     * 访问一个布尔
     *
     * @param x
     * @return
     */
    private void append(OqlBooleanExpr x, StringBuilder builder) {
        builder.append(x.getValue());
    }

    /**
     * 访问一个整数
     *
     * @param x
     * @return
     */
    private void append(OqlIntegerExpr x, StringBuilder builder) {
        builder.append(x.getValue());
    }

    /**
     * 访问一个整数
     *
     * @param x
     * @return
     */
    private void append(OqlNumberExpr x, StringBuilder builder) {
        builder.append(x.getValue());
    }

    /**
     * 访问一个NULL
     *
     * @param x
     * @return
     */
    private void append(OqlNullExpr x, StringBuilder builder) {
        builder.append("null");
    }

    /**
     * 访问一个JSON对象
     *
     * @param x
     * @return
     */
    private void append(OqlJsonObjectExpr x, StringBuilder builder) {
        builder.append("'{");
        int i = 0;
        for (Map.Entry<String, QqlExpr> entry : x.getItems().entrySet()) {
            if (i++ > 0) {
                builder.append(", ");
            }
            builder.append("\"").append(entry.getKey()).append("\"").append(":");
            if (entry.getValue() instanceof OqlCharExpr) {
                builder.append("\"").append(((OqlCharExpr) entry.getValue()).getValue()).append("\"");
            } else {
                this.append(entry.getValue(), builder);
            }
        }
        builder.append("}'");
    }

    /**
     * 以单引号输出
     *
     * @param text
     * @param builder
     */
    private void singleQuotedAppend(String text, StringBuilder builder) {
        builder.append("\'").append(text).append("\'");
    }


    /**
     * 访问一个JSON数组
     *
     * @param x
     * @return
     */
    private void append(OqlJsonArrayExpr x, StringBuilder builder) {
        builder.append("'[");
        CommaSeperatedListOutput.output(builder, x.getItems(), (item) -> {
            this.append(item, builder);
        });
        builder.append("]'");
    }

    /**
     * 访问一个标识符
     *
     * @param x
     * @return
     */
    private void append(OqlIdentifierExpr x, StringBuilder builder) {
        String name = x.getName();
        DataField field = this.object.getField(name);
        if (field != null) {
            builder.append(field.getColumnName());
        } else {
            builder.append(name);
        }
    }

    /**
     * 访问一个属性标识符
     *
     * @param x
     * @return
     */
    private void append(OqlPropertyExpr x, StringBuilder builder) {
        builder.append(x.getOwner()).append(".").append(x.getName());
    }

    /**
     * 访问一个变量引用标识符
     *
     * @param x
     * @return
     */
    private void append(OqlVariantRefExpr x, StringBuilder builder) {
        if (x.isReplaceable()) {
            builder.append(x.getName());
        } else {
            builder.append(":").append(x.getVarName());
        }
    }


    /**
     * 访问一个非表达式
     *
     * @param x
     * @return
     */
    private void append(OqlNotExpr x, StringBuilder builder) {
        builder.append("not (").append(x.getExpr()).append(")");
    }

    /**
     * 访问一个二元操作
     *
     * @param x
     * @return
     */
    private void append(OqlBinaryOpExpr x, StringBuilder builder) {
        if (x.isParenthesized()) {
            builder.append("(");
        }
        this.append(x.getLeft(), builder);
        builder.append(" ").append(x.getOperator().name).append(" ");
        this.append(x.getRight(), builder);
        if (x.isParenthesized()) {
            builder.append(")");
        }
    }

    /**
     * 访问一个方法调用
     *
     * @param x
     * @return
     */
    private void append(OqlMethodInvokeExpr x, StringBuilder builder) {
        builder.append(x.getMethodName()).append("(");
        CommaSeperatedListOutput.output(builder, x.getArguments(), (argument) -> {
            this.append(argument, builder);
        });
        builder.append(")");
    }

    /**
     * 访问一个in表达式
     *
     * @param x
     * @return
     */
    private void getSql(OqlInOpExpr x, StringBuilder builder) {
        this.append(x.getLeft(), builder);
        builder.append(" in (");
        CommaSeperatedListOutput.output(builder, x.getValues().getItems(), (value) -> {
            this.append(value, builder);
        });
        builder.append(")");
    }

    /**
     * 访问一个列表(x,x,x)表达式
     *
     * @param x
     * @return
     */
    private void getSql(OqlListExpr x, StringBuilder builder) {
        builder.append("(");
        CommaSeperatedListOutput.output(builder, x.getItems(), (value) -> {
            this.append(value, builder);
        });
        builder.append(")");
    }

    /**
     * 访问一个like表达式
     *
     * @param x
     * @return
     */
    private void getSql(OqlLikeOpExpr x, StringBuilder builder) {
        this.append(x.getLeft(), builder);
        builder.append(" like ");
        this.append(x.getRight(), builder);
    }

}
