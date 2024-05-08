package net.cf.object.engine.oql.parser;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.op.*;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.form.repository.sql.parser.SqlExprParser;
import net.cf.form.repository.sql.parser.Token;
import net.cf.object.engine.object.*;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.util.OqlUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * OQL 表达式解析器
 *
 * @author clouds
 */
public class OqlExprParser extends SqlExprParser {

    protected CachedObjectResolverProxy resolver;

    protected Set<String> supportedMethodNames = new HashSet<>();

    public OqlExprParser(XObjectResolver resolver, String oql) {
        super(oql);
        this.resolver = new CachedObjectResolverProxy(resolver);
        this.supportedMethodNames.addAll(Arrays.asList("NOW", "LTRIM", "CONCAT", "SUBSTRING", "TRIM", "RTRIM", "YEAR", "MONTH", "DAY", "LENGTH"));
    }

    /**
     * 根据模型名称解析模型
     *
     * @param objectName
     * @return
     */
    protected XObject resolveObject(String objectName) {
        XObject resolvedObject = this.resolver.resolve(objectName);
        if (resolvedObject == null) {
            throw new FastOqlException("模型" + objectName + "不存在");
        }

        //this.lastResolvedObject = resolvedObject;
        return resolvedObject;
    }

    /**
     * 根据模型的字段名称解析普通字段
     *
     * @param object
     * @param fieldName
     * @return
     */
    protected XField resolveField(XObject object, String fieldName) {
        XField field = object.getField(fieldName);
        if (field == null) {
            String objectName = object.getName();
            throw new FastOqlException("模型" + objectName + "下的字段" + fieldName + "不存在");
        }

        return field;
    }

    /**
     * 根据模型的字段名称解析模型引用字段
     *
     * @param object
     * @param fieldName
     * @return
     */
    protected XObjectRefField resolveObjectRefField(XObject object, String fieldName) {
        XField field = this.resolveField(object, fieldName);
        if (!(field instanceof XObjectRefField)) {
            String objectName = object.getName();
            throw new FastOqlException("模型" + objectName + "下的字段" + fieldName + "不是一个模型引用的字段");
        }

        return (XObjectRefField) field;
    }

    /**
     * 根据模型的字段名称解析普通字段
     *
     * @param field
     * @param propName
     * @return
     */
    protected XProperty resolveProperty(XField field, String propName) {
        XProperty property = field.getProperty(propName);
        if (property == null) {
            XObject object = field.getOwner();
            String objectName = object.getName();
            String fieldName = field.getName();
            throw new FastOqlException("模型" + objectName + "下的字段" + fieldName + "下的属性" + propName + "不存在");
        }

        return property;
    }


    /**
     * 解析模型源
     *
     * @param tableSource
     * @return
     */
    protected OqlObjectSource parseObjectSource(SqlTableSource tableSource) {
        OqlObjectSource objectSource = null;
        if (tableSource instanceof SqlExprTableSource) {
            objectSource = this.parseExprObjectSource((SqlExprTableSource) tableSource);
        }

        return objectSource;
    }

    /**
     * 解析标识符模型源
     *
     * @param exprTableSource
     * @return
     */
    protected OqlExprObjectSource parseExprObjectSource(SqlExprTableSource exprTableSource) {
        String objectName = exprTableSource.getTableName();
        XObject resolvedObject = this.resolveObject(objectName);
        OqlExprObjectSource exprObjectSource = new OqlExprObjectSource(objectName);
        exprObjectSource.setResolvedObject(resolvedObject);
        return exprObjectSource;
    }

    /**
     * 将一个SQL层的表达式转为OQL层的表达式
     * <p>
     * 大部分表达式会还样返回，部分和模型、字段相关的表达式会提升为OQL特殊的表达式
     *
     * @param selfObject
     * @param x
     * @return
     */
    protected SqlExpr parseSqlExpr(final XObject selfObject, final SqlExpr x) {
        Class<?> clazz = x.getClass();
        if (clazz == SqlIdentifierExpr.class) {
            return this.parseIdentifierExpr(selfObject, (SqlIdentifierExpr) x);
        } else if (clazz == SqlPropertyExpr.class) {
            return this.parsePropertyExpr(selfObject, (SqlPropertyExpr) x);
        } else if (clazz == SqlLikeOpExpr.class) {
            return this.parseLikeExpr(selfObject, (SqlLikeOpExpr) x);
        } else if (clazz == SqlInListExpr.class) {
            return this.parseInListExpr(selfObject, (SqlInListExpr) x);
        } else if (clazz == SqlContainsOpExpr.class) {
            return this.parseContainsOpExpr(selfObject, (SqlContainsOpExpr) x);
        } else if (clazz == SqlArrayContainsOpExpr.class) {
            return this.parseArrayContainsOpExpr(selfObject, (SqlArrayContainsOpExpr) x);
        } else if (clazz == SqlBinaryOpExpr.class) {
            return this.parseBinaryOpExpr(selfObject, (SqlBinaryOpExpr) x);
        } else if (clazz == SqlBinaryOpExprGroup.class) {
            return this.parseBinaryOpExprGroup(selfObject, (SqlBinaryOpExprGroup) x);
        } else if (clazz == SqlAggregateExpr.class) {
            return this.parseAggregateExpr(selfObject, (SqlAggregateExpr) x);
        } else if (clazz == SqlMethodInvokeExpr.class) {
            return this.parseMethodInvokeExpr(selfObject, (SqlMethodInvokeExpr) x);
        } else {
            return x;
        }
    }

    /**
     * 解析标识符表达式
     *
     * @param selfObject
     * @param expr
     * @return
     */
    private SqlExpr parseIdentifierExpr(XObject selfObject, SqlIdentifierExpr expr) {
        String fieldName = expr.getName();
        XField resolvedField = this.resolveField(selfObject, fieldName);
        if (resolvedField instanceof XObjectRefField) {
            XObjectRefField objectRefField = (XObjectRefField) resolvedField;
            // 对于子表，如果直接指定本表中的子表字段名的话，默认查询子表的主键列，相当于detailObject(primaryField)
            if (objectRefField.getRefType() == ObjectRefType.DETAIL) {
                OqlObjectExpandExpr objectExpandExpr = new OqlObjectExpandExpr(fieldName);
                //objectExpandExpr.setDefaultExpanded(true);
                objectExpandExpr.setResolvedObjectRefField(objectRefField);
                String refObjectName = objectRefField.getRefObjectName();
                XObject refObject = this.resolver.resolve(refObjectName);
                objectExpandExpr.setResolvedRefObject(refObject);
                return objectExpandExpr;
            }
        }

        // 如果字段下存在子属性的话，那么转换为字段展开表达式（默认展开）
        OqlFieldExpr fieldExpr = new OqlFieldExpr(fieldName);
        fieldExpr.setResolvedField(resolvedField);
        return fieldExpr;
    }

    /**
     * 解析属性表达式
     *
     * @param selfObject
     * @param x
     * @return
     */
    private SqlExpr parsePropertyExpr(XObject selfObject, SqlPropertyExpr x) {
        SqlIdentifierExpr owner = x.getOwner();
        String propName = x.getName();
        int dotIndex = propName.indexOf(Token.DOT.name);
        if (dotIndex > 0) { // 三级属性处理，如：objectRefField.field.property
            // 解析模型、字段、属性
            String objectRefFieldName = owner.getName();
            XObjectRefField objectRefField = this.resolveObjectRefField(selfObject, objectRefFieldName);
            XObject resolvedOwnerObject = objectRefField.getOwner();
            String ownerFieldName = propName.substring(0, dotIndex);
            XField resolvedOwnerField = this.resolveField(resolvedOwnerObject, ownerFieldName);
            String ownerPropertyName = propName.substring(dotIndex + 1);
            XProperty resolvedOwnerProperty = this.resolveProperty(resolvedOwnerField, ownerPropertyName);

            // 生成属性表达式
            String ownerObjectName = resolvedOwnerObject.getName();
            OqlFieldExpr ownerFieldExpr = new OqlFieldExpr(ownerObjectName, ownerFieldName);
            ownerFieldExpr.setResolvedField(resolvedOwnerField);
            OqlPropertyExpr propExpr = new OqlPropertyExpr(ownerFieldExpr, ownerPropertyName);
            propExpr.setResolvedProperty(resolvedOwnerProperty);
            return ownerFieldExpr;
        } else {
            return this.parseSimplePropertyExpr(selfObject, x);
        }
    }

    /**
     * 解析简单的属性表达式，即不含嵌套的属性，如：objectRefField.field 或者 objectRefField.* 或者 field.property
     *
     * @param selfObject 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseSimplePropertyExpr(XObject selfObject, SqlPropertyExpr x) {
        // 校验模型的存在性
        SqlIdentifierExpr owner = x.getOwner();
        String ownerName = owner.getName();
        String propName = x.getName();
        XField ownerField = this.resolveField(selfObject, ownerName);
        if (!(ownerField instanceof XObjectRefField)) { // 非引用模型（它表）的字段
            XProperty property = this.resolveProperty(ownerField, propName);
            // 识别为字段属性表达式
            OqlFieldExpr fieldExpr = new OqlFieldExpr(ownerName);
            OqlPropertyExpr propExpr = new OqlPropertyExpr(fieldExpr, propName);
            propExpr.setResolvedProperty(property);
            return propExpr;
        } else { // 当作引用模型（它表）处理
            XObjectRefField objectRefField = (XObjectRefField) ownerField;
            String refObjectName = objectRefField.getRefObjectName();
            XObject refObject = this.resolveObject(refObjectName);
            if (Token.STAR.getName().equals(propName)) { // 展开整个模型
                // 识别为OQL模型展开表达式
                OqlObjectExpandExpr objectExpandExpr = new OqlObjectExpandExpr(ownerName);
                objectExpandExpr.setStarExpanded(true);
                objectExpandExpr.setResolvedObjectRefField(objectRefField);
                objectExpandExpr.setResolvedRefObject(refObject);
                return objectExpandExpr;
            } else { // 指定的字段
                XField refField = this.resolveField(refObject, propName);
                // 识别为OQL字段表达式
                OqlFieldExpr fieldExpr = new OqlFieldExpr(ownerName, propName);
                fieldExpr.setResolvedField(refField);
                return fieldExpr;
            }
        }
    }

    /**
     * 解析 Like 表达式
     *
     * @param selfObject 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseLikeExpr(XObject selfObject, SqlLikeOpExpr x) {
        SqlLikeOpExpr sqlX = new SqlLikeOpExpr();
        sqlX.setLeft(this.parseSqlExpr(selfObject, x.getLeft()));
        sqlX.setRight(this.parseSqlExpr(selfObject, x.getRight()));
        return sqlX;
    }

    /**
     * 解析 InList 表达式
     *
     * @param selfObject 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseInListExpr(XObject selfObject, SqlInListExpr x) {
        SqlInListExpr sqlX = new SqlInListExpr();
        sqlX.setLeft(this.parseSqlExpr(selfObject, x.getLeft()));
        List<SqlExpr> targetList = x.getTargetList();
        for (SqlExpr targetItem : targetList) {
            sqlX.addTarget(this.parseSqlExpr(selfObject, targetItem));
        }
        return sqlX;
    }

    /**
     * 解析 Contains 表达式
     *
     * @param selfObject 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseContainsOpExpr(XObject selfObject, SqlContainsOpExpr x) {
        SqlContainsOpExpr sqlX = new SqlContainsOpExpr();
        sqlX.setLeft(this.parseSqlExpr(selfObject, x.getLeft()));
        sqlX.setOperator(x.getOperator());
        sqlX.setRight(this.parseSqlExpr(selfObject, x.getRight()));
        sqlX.setParenthesized(x.isParenthesized());
        return sqlX;
    }

    /**
     * 解析 array Contains 表达式
     *
     * @param selfObject 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseArrayContainsOpExpr(XObject selfObject, SqlArrayContainsOpExpr x) {
        SqlArrayContainsOpExpr sqlX = new SqlArrayContainsOpExpr();
        sqlX.setLeft(this.parseSqlExpr(selfObject, x.getLeft()));
        sqlX.setOperator(x.getOperator());
        sqlX.setRight(this.parseSqlExpr(selfObject, x.getRight()));
        sqlX.setParenthesized(x.isParenthesized());
        sqlX.setOption(x.getOption());
        return sqlX;
    }

    /**
     * 解析二元操作表达式
     *
     * @param selfObject 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseBinaryOpExpr(XObject selfObject, SqlBinaryOpExpr x) {
        SqlBinaryOpExpr sqlX = new SqlBinaryOpExpr();
        sqlX.setLeft(this.parseSqlExpr(selfObject, x.getLeft()));
        sqlX.setOperator(x.getOperator());
        sqlX.setRight(this.parseSqlExpr(selfObject, x.getRight()));
        sqlX.setParenthesized(x.isParenthesized());
        return sqlX;
    }

    /**
     * 解析二元操作表达式组合
     *
     * @param selfObject
     * @param x
     * @return
     */
    private SqlExpr parseBinaryOpExprGroup(XObject selfObject, SqlBinaryOpExprGroup x) {
        SqlBinaryOpExprGroup sqlX = new SqlBinaryOpExprGroup(x.getOperator());
        List<SqlExpr> items = x.getItems();
        for (SqlExpr item : items) {
            sqlX.addItem(this.parseSqlExpr(selfObject, item));
        }

        return sqlX;
    }

    /**
     * 解析聚合表达式
     *
     * @param selfObject 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseAggregateExpr(XObject selfObject, SqlAggregateExpr x) {
        SqlAggregateExpr sqlX = new SqlAggregateExpr();
        sqlX.setMethodName(x.getMethodName());
        List<SqlExpr> args = x.getArguments();
        for (SqlExpr arg : args) {
            if (arg instanceof SqlAllColumnExpr) {
                sqlX.addArgument(arg);
            } else {
                sqlX.addArgument(this.parseSqlExpr(selfObject, arg));
            }
        }
        return sqlX;
    }

    /**
     * 解析方法调用表达式
     *
     * @param selfObject 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseMethodInvokeExpr(XObject selfObject, SqlMethodInvokeExpr x) {
        String methodName = x.getMethodName();
        if (this.supportedMethodNames.contains(methodName.toUpperCase())) {
            SqlMethodInvokeExpr sqlX = new SqlMethodInvokeExpr();
            sqlX.setMethodName(x.getMethodName());
            List<SqlExpr> args = x.getArguments();
            for (SqlExpr arg : args) {
                sqlX.addArgument(this.parseSqlExpr(selfObject, arg));
            }
            return sqlX;
        }

        //不是方法调用的话，那就是模型展开
        XObjectRefField objectRefField = this.resolveObjectRefField(selfObject, methodName);
        return this.expandObject(objectRefField, x);
    }

    /**
     * 展开模型
     *
     * @param objectRefField 本表中展开的字段
     * @param x
     */
    private OqlObjectExpandExpr expandObject(XObjectRefField objectRefField, SqlMethodInvokeExpr x) {
        XObject refObject = this.resolver.resolve(objectRefField.getRefObjectName());
        String refObjectName = refObject.getName();

        String refObjectFieldName = x.getMethodName();
        OqlObjectExpandExpr objectExpandExpr = new OqlObjectExpandExpr(refObjectFieldName);
        objectExpandExpr.setResolvedObjectRefField(objectRefField);
        objectExpandExpr.setResolvedRefObject(refObject);
        List<SqlExpr> args = x.getArguments();
        for (SqlExpr arg : args) {
            if (arg instanceof SqlAggregateExpr) {
                throw new FastOqlException("模型展开的字段中不能有嵌合函数");
            } else if (arg instanceof SqlMethodInvokeExpr) { // 内嵌字段展开
                SqlExpr argX = this.parseMethodInvokeExpr(refObject, (SqlMethodInvokeExpr) arg);
                if (argX instanceof OqlExpr) {
                    throw new FastOqlException("OQL模型展开的字段中，只允许出现OqlExpr类型的表达式");
                }
                objectExpandExpr.addField((OqlExpr) argX);
            } else if (arg instanceof SqlIdentifierExpr) {
                String fieldName = ((SqlIdentifierExpr) arg).getName();
                XField field = this.resolveField(refObject, fieldName);
                objectExpandExpr.addField(OqlUtils.defaultFieldExpr(field));
            } else {
                SqlExpr argX = this.parseSqlExpr(refObject, arg);
                if (argX instanceof OqlExpr) {
                    throw new FastOqlException("OQL模型展开的字段中，只允许出现OqlExpr类型的表达式");
                }
                objectExpandExpr.addField((OqlExpr) argX);
            }
        }

        return objectExpandExpr;
    }

}