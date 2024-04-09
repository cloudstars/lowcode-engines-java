package net.cf.object.engine.oql.info;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.object.ObjectRefType;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectExpandExpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 抽象的OQL指令构建器
 *
 * @author clouds
 */
public abstract class AbstractOqlInfoParser {

    /**
     * 是否批量模式
     */
    protected final boolean isBatchMode;

    public AbstractOqlInfoParser(boolean isBatchMode) {
        this.isBatchMode = isBatchMode;
    }

    public boolean isBatchMode() {
        return isBatchMode;
    }


    /**
     * 是否子模型扩展字段
     *
     * @param expr
     * @return
     */
    protected boolean isDetailFieldExpr(SqlExpr expr) {
        if (expr instanceof OqlObjectExpandExpr) {
            OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) expr;
            if (objectExpandExpr.getResolvedObjectRefField().getRefType() == ObjectRefType.DETAIL) {
                return true;
            }
        }

        return false;
    }

    /**
     * 构建模型源
     *
     * @param object
     * @return
     */
    protected OqlExprObjectSource buildExprObjectSource(XObject object) {
        OqlExprObjectSource objectSource = new OqlExprObjectSource(object.getName());
        objectSource.setResolvedObject(object);
        return objectSource;
    }

    /**
     * 构建一个字段与它的变量相等的二元操作表达式（即：fieldName = #{fieldName}）
     *
     * @param field
     * @return
     */
    /*protected SqlBinaryOpExpr buildFieldEqualityVariantRefExpr(XField field) {
        String fieldName = field.getName();
        SqlBinaryOpExpr binaryOpExpr = new SqlBinaryOpExpr();
        OqlFieldExpr fieldExpr = new OqlFieldExpr();
        fieldExpr.setName(fieldName);
        fieldExpr.setResolvedField(field);
        binaryOpExpr.setLeft(fieldExpr);
        binaryOpExpr.setOperator(SqlBinaryOperator.EQUALITY);
        binaryOpExpr.setRight(new SqlVariantRefExpr("#{" + fieldName + "}"));
        return binaryOpExpr;
    }*/

    /**
     * 构建一个字段in它的变量复数的inList表达式（即：fieldName in (#{fieldNames})）
     *
     * @param field
     * @return
     */
    /*protected SqlInListExpr buildFieldInVariantRefListExpr(XField field) {
        String fieldName = field.getName();
        SqlInListExpr inListExpr = new SqlInListExpr();
        OqlFieldExpr fieldExpr = new OqlFieldExpr(fieldName);
        fieldExpr.setResolvedField(field);
        inListExpr.setLeft(fieldExpr);
        inListExpr.setTargetList(Arrays.asList(new SqlVariantRefExpr("#{" + fieldName + "s}")));
        return inListExpr;
    }*/

    /**
     * 从批量的参数中抽取子表相关的数据
     *
     * @param paramMaps
     * @param varName
     * @return
     */
    protected List<Map<String, Object>> extractDetailValues(List<Map<String, Object>> paramMaps, String varName) {
        List<Map<String, Object>> targetValues = new ArrayList<>();
        for (Map<String, Object> paramMap : paramMaps) {
            Object paramValue = paramMap.get(varName);
            if (!(paramValue instanceof List)) {
                throw new FastOqlException("子模型的参数类型必须是一个List<Map>");
            }
            List<Map<String, Object>> listParamValue = (List<Map<String, Object>>) paramValue;
            targetValues.addAll(listParamValue);
        }

        return targetValues;
    }
}
