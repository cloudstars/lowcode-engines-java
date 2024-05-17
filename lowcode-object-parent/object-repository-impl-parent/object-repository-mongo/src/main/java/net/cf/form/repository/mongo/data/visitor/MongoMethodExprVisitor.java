package net.cf.form.repository.mongo.data.visitor;

import net.cf.form.repository.mongo.data.MongoUtils;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAggregateExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlMethodInvokeExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import org.bson.Document;

import java.util.*;

public class MongoMethodExprVisitor {


    /**
     * @param sqlMethodInvokeExpr
     * @param globalContext
     * @return
     */
    public static Object build(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext, VisitContext visitContext) {
        if (globalContext.getPositionEnum() == PositionEnum.HAVING) {
            // having中，返回函数别名
            return getMethodOrigin(sqlMethodInvokeExpr, globalContext, visitContext);
        } else {
            if (sqlMethodInvokeExpr instanceof SqlAggregateExpr && globalContext.getPositionEnum() == PositionEnum.WHERE) {
                // 聚合函数一般不存在where后面， 且聚合函数查询时会特殊处理
                return getMethodOrigin(sqlMethodInvokeExpr, globalContext, visitContext);
            } else {
                return buildCommon(sqlMethodInvokeExpr, globalContext);
            }
        }
    }

    private static String getMethodOrigin(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext, VisitContext visitContext) {
        if (visitContext.isFieldTag()) {
            return "$" + MongoUtils.getOriginExprAlias(sqlMethodInvokeExpr);
        }
        return MongoUtils.getOriginExprAlias(sqlMethodInvokeExpr);
    }


    private static final String DATETIME_FORMAT = "%Y-%m-%d";

    /**
     * @param sqlMethodInvokeExpr
     * @param globalContext
     * @return
     */
    private static Document buildSubString(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext) {
        Document document = new Document();
        List<SqlExpr> sqlExprList = sqlMethodInvokeExpr.getArguments();
        if (sqlExprList == null || sqlExprList.size() != 3) {
            throw new RuntimeException("param error");
        }

        Object strValue = getTagVal(sqlExprList.get(0), globalContext);
        Object startValue = MongoExprVisitor.visit(sqlExprList.get(1), globalContext);
        Object endValue = MongoExprVisitor.visit(sqlExprList.get(2), globalContext);

        int start = Integer.parseInt(startValue.toString()) - 1;
        int end = Integer.parseInt(endValue.toString());

        document.put("$substrCP", Arrays.asList(strValue, start, end));
        return document;
    }

    /**
     * @param sqlMethodInvokeExpr
     * @param globalContext
     * @return
     */
    private static Object buildConcat(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext) {
        if (globalContext.getMongoMode() == MongoMode.INSERT) {
            StringBuilder sb = new StringBuilder();
            List<SqlExpr> sqlExprList = sqlMethodInvokeExpr.getArguments();
            for (SqlExpr sqlExpr : sqlExprList) {
                String val = String.valueOf(getTagVal(sqlExpr, globalContext));
                sb.append(val);
            }
            return sb.toString();
        } else {
            Document document = new Document();
            List<Object> items = new ArrayList<>();
            for (SqlExpr sqlExpr : sqlMethodInvokeExpr.getArguments()) {
                Object val = getTagVal(sqlExpr, globalContext);
                items.add(val);
            }
            document.put("$concat", items);
            return document;
        }
    }

    /**
     * @param sqlMethodInvokeExpr
     * @param globalContext
     * @return
     */
    private static Document buildLength(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext) {
        Document document = new Document();
        SqlExpr sqlExpr = sqlMethodInvokeExpr.getArguments().get(0);
        Object val = getTagVal(sqlExpr, globalContext);
        document.put("$strLenCP", val);
        return document;
    }

    /**
     * @param sqlMethodInvokeExpr
     * @param globalContext
     * @return
     */
    private static Document buildDateSelect(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext) {
        Document document = new Document();
        SqlExpr sqlExpr = sqlMethodInvokeExpr.getArguments().get(0);
        Object val = getTagVal(sqlExpr, globalContext);
        String methodName = sqlMethodInvokeExpr.getMethodName().toUpperCase(Locale.ROOT);
        String method = null;
        if ("YEAR".equals(methodName)) {
            method = "$year";
        } else if ("MONTH".equals(methodName)) {
            method = "$month";
        } else if ("DAY".equals(methodName)) {
            method = "$dayOfMonth";
        }

        Document dateDoc = new Document();
        dateDoc.put("date", val);
        dateDoc.put("timezone", "Asia/Shanghai");
        document.put(method, dateDoc);
        return document;
    }

    /**
     * @param sqlMethodInvokeExpr
     * @param globalContext
     * @return
     */
    private static Document buildTrim(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext) {
        Document document = new Document();
        SqlExpr sqlExpr = sqlMethodInvokeExpr.getArguments().get(0);
        Object val = getTagVal(sqlExpr, globalContext);
        String methodName = sqlMethodInvokeExpr.getMethodName().toUpperCase(Locale.ROOT);
        String method = null;
        if ("TRIM".equals(methodName)) {
            method = "$trim";
        } else if ("LTRIM".equals(methodName)) {
            method = "$ltrim";
        } else if ("RTRIM".equals(methodName)) {
            method = "$rtrim";
        }
        document.put(method, new Document("input", val));
        return document;
    }

    /**
     * @param sqlMethodInvokeExpr
     * @param globalContext
     * @return
     */
    private static Object buildNow(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext) {
        if (globalContext.getMongoMode() == MongoMode.INSERT || globalContext.getMongoMode() == MongoMode.UPDATE) {
            return new Date();
        } else {
            return "$$NOW";
        }
    }

    /**
     * @param sqlMethodInvokeExpr
     * @param globalContext
     * @return
     */
    private static Object buildTimeStamp(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext) {
        Document document = new Document();
        SqlExpr sqlExpr = sqlMethodInvokeExpr.getArguments().get(0);
        Object val = getTagVal(sqlExpr, globalContext);
        document.put("$toLong", new Document("$toDate", val));
        return document;
    }

    /**
     * @param sqlMethodInvokeExpr
     * @param globalContext
     * @return
     */
    private static Object buildDateFormat(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext) {
        Document document = new Document();
        SqlExpr sqlExpr = sqlMethodInvokeExpr.getArguments().get(0);
        Object val = getTagVal(sqlExpr, globalContext);

        SqlExpr formatSqlExpr = sqlMethodInvokeExpr.getArguments().get(1);
        Object formatVal = MongoExprVisitor.visit(formatSqlExpr, globalContext);
        String format = String.valueOf(formatVal);
        String mongoFormat = format.replace("%i", "%M").replace("%s", "%S");

        Document dateToString = new Document();
        dateToString.put("date", val);
        dateToString.put("format", mongoFormat);
        dateToString.put("timezone", "+0800");
        document.put("$dateToString", dateToString);
        return document;
    }

    /**
     * 获取值
     *
     * @param sqlExpr
     * @param globalContext
     * @return
     */
    private static Object getTagVal(SqlExpr sqlExpr, GlobalContext globalContext) {

        if (sqlExpr instanceof SqlIdentifierExpr || sqlExpr instanceof SqlPropertyExpr) {
            // 特殊处理 SqlIdentifierExpr，SqlPropertyExpr 函数内需要打$
            VisitContext innerContext = VisitContext.getFieldTagContext();
            return MongoExprVisitor.visit(sqlExpr, globalContext, innerContext);
        } else {
            return MongoExprVisitor.visit(sqlExpr, globalContext);
        }
    }

    /**
     * @param sqlMethodInvokeExpr
     * @param globalContext
     * @return
     */
    private static Object buildCommon(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext) {
        String methodName = sqlMethodInvokeExpr.getMethodName();

        switch (methodName.toUpperCase()) {
            case "SUBSTRING":
                //
                return buildSubString(sqlMethodInvokeExpr, globalContext);
            case "CONCAT":
                //
                return buildConcat(sqlMethodInvokeExpr, globalContext);
            case "LENGTH":
                //
                return buildLength(sqlMethodInvokeExpr, globalContext);
            case "YEAR":
            case "MONTH":
            case "DAY":
                //
                return buildDateSelect(sqlMethodInvokeExpr, globalContext);
            case "TRIM":
            case "LTRIM":
            case "RTRIM":
                //
                return buildTrim(sqlMethodInvokeExpr, globalContext);
            case "NOW":
                // now
                return buildNow(sqlMethodInvokeExpr, globalContext);
            case "TIMESTAMP":
                // 转为时间戳
                return buildTimeStamp(sqlMethodInvokeExpr, globalContext);
            case "DATE_FORMAT":
                // 时间格式化字符串
                return buildDateFormat(sqlMethodInvokeExpr, globalContext);
            case "DATE":
                return buildDateConvert(sqlMethodInvokeExpr, globalContext);
            default:
                throw new RuntimeException("unknown method");
        }

    }

    private static Object buildDateConvert(SqlMethodInvokeExpr sqlMethodInvokeExpr, GlobalContext globalContext) {
        Document document = new Document();
        SqlExpr sqlExpr = sqlMethodInvokeExpr.getArguments().get(0);
        Object val = getTagVal(sqlExpr, globalContext);

        // 时间转日期字符串
        Document dateToString = new Document();
        dateToString.put("date", val);
        dateToString.put("format", DATETIME_FORMAT);
        dateToString.put("timezone", "+0800");
        document.put("$dateToString", dateToString);

//        // 重新转时间
//        Document op = new Document();
//        Document stringToDate = new Document();
//        stringToDate.put("dateString", document);
//        stringToDate.put("format", DATETIME_FORMAT);
//        stringToDate.put("timezone", "+0800");
//        op.put("$dateFromString", stringToDate);

        return document;
    }

}
