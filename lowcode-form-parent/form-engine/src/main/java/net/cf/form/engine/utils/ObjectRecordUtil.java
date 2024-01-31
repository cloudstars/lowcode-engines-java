package net.cf.form.engine.utils;

import net.cf.form.engine.field.XDetailFieldBak;
import net.cf.form.engine.field.XField;
import net.cf.form.engine.object.XObject;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.oql.ast.expr.identifier.OqlObjectExpandExpr;
import net.cf.form.engine.oql.ast.expr.identifier.OqlVariantRefExpr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对象记录工具类
 *
 * @author liuyangjunwu
 */
public class ObjectRecordUtil {
    private static final Logger logger = LoggerFactory.getLogger(ObjectRecordUtil.class);

    private final static String variableTemp = "#{variable}";



    /**
     * 构建ObjectFiledMap
     *
     * @param object
     * @return
     */
    public static Map<String, XField> getObjectFieldMap(XObject object) {
        return object.getFields().stream().collect(Collectors.toMap(XField::getName, field -> field));
    }

    /**
     * 生成OQLField
     *
     * @param fieldName
     * @param value
     * @param fieldMap
     * @return
     */
    public static OqlExpr buildOQLField(String fieldName, Object value, Map<String, XField> fieldMap) {
        XField xField = fieldMap.get(fieldName);
        //如果字段是子表，需要补充子表字段的字段名
        OqlExpr field;
        if (xField instanceof XDetailFieldBak) {
            OqlObjectExpandExpr oqlObjectExpandExpr = new OqlObjectExpandExpr(fieldName);
            List<Map<String, Object>> detailDataMaps = (List<Map<String, Object>>) value;
            if (!detailDataMaps.isEmpty()) {
                Map<String, Object> detailData = detailDataMaps.get(0);
                //TODO  存在一个点是只知道变量的Key，不知道变量对应的字段
                detailData.keySet().forEach(key -> oqlObjectExpandExpr.addArgument(new OqlIdentifierExpr(key)));
            }
            field = oqlObjectExpandExpr;
        } else {
            field = new OqlIdentifierExpr(fieldName);
        }
        return field;
    }

    /**
     * 生成参数变量
     *
     * @param value
     * @return
     */
    public static OqlVariantRefExpr getOQLVariantRefExpr(String value) {
        return new OqlVariantRefExpr(variableTemp.replace("variable", value));
    }






}
