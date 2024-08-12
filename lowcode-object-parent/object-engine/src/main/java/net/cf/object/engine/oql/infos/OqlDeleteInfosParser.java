
package net.cf.object.engine.oql.infos;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.sql.SqlDeleteCmd;
import net.cf.object.engine.sql.SqlDeleteCmdBuilder;
import net.cf.object.engine.util.OqlUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OQL删除语句解析器
 * <p>
 * 职责：用于将一条OQL删除语句解析成本表的删除与子表的删除
 */
public class OqlDeleteInfosParser extends AbstractOqInfosParser<OqlDeleteStatement, OqlDeleteInfos> {

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 输入的参数（批量模式）
     */
    private final List<Map<String, Object>> paramMaps;

    /**
     * 子模型关联的删除语句
     * <p>
     * delete from detailObject where masterPrimaryField = #{masterPrimaryField}
     */
    private final Map<XObject, OqlDeleteStatement> detailObjectDeleteStmtMap = new HashMap<>();

    public OqlDeleteInfosParser(OqlDeleteStatement stmt, Map<String, Object> paramMap) {
        super(stmt, false);
        this.paramMap = paramMap;
        this.paramMaps = null;
    }

    public OqlDeleteInfosParser(OqlDeleteStatement stmt, List<Map<String, Object>> paramMaps) {
        super(stmt, true);
        this.paramMap = null;
        this.paramMaps = paramMaps;
    }

    /**
     * 解析成OQL删除语句指令信息
     *
     * @return
     */
    public OqlDeleteInfos parse() {
        // 解析当前语句的本模型
        this.masterObject = this.stmt.getFrom().getResolvedObject();
        this.masterPrimaryFieldValueExpr = this.extractMasterIdInWhere(this.masterObject, this.stmt.getWhere());

        List<OqlExprObjectSource> detailObjectSources = stmt.getDetailFroms();
        if (!CollectionUtils.isEmpty(detailObjectSources)) {
            for (OqlExprObjectSource detailObjectSource : detailObjectSources) {
                XObject detailObject = detailObjectSource.getResolvedObject();
                this.buildDetailDeleteStmt(detailObject);
            }
        }

        return this.buildDeleteInfos();
    }

    /**
     * 构建删除信息
     *
     * @return
     */
    private OqlDeleteInfos buildDeleteInfos() {
        OqlDeleteInfos deleteInfos = new OqlDeleteInfos();
        deleteInfos.setResolvedMasterObject(this.masterObject);

        SqlDeleteCmdBuilder builder;
        if (!this.isBatch) {
            builder = new SqlDeleteCmdBuilder(this.stmt, this.paramMap);
        } else {
            builder = new SqlDeleteCmdBuilder(this.stmt, this.paramMaps);
        }
        SqlDeleteCmd masterDeleteCmd = builder.build();
        deleteInfos.setMasterDeleteCmd(masterDeleteCmd);

        if (!detailObjectDeleteStmtMap.isEmpty()) {
            detailObjectDeleteStmtMap.forEach((detailObject, stmt) -> {
                deleteInfos.addResolvedDetailObject(detailObject);
                XObjectRefField masterRefField = detailObject.getMasterField();
                String masterRefFieldName = masterRefField.getName();

                SqlDeleteCmdBuilder detailBuilder;
                if (!this.isBatch) {
                    Map<String, Object> detailParamMap = this.f(this.paramMap, masterRefFieldName);
                    detailBuilder = new SqlDeleteCmdBuilder(stmt, detailParamMap);
                } else {
                    List<Map<String, Object>> detailParamMaps = new ArrayList<>();
                    for (Map<String, Object> paramMap : this.paramMaps) {
                        Object masterRecordId = this.extractMasterId(paramMap);
                        Map<String, Object> detailParamMap = new HashMap<>();
                        detailParamMap.put(masterRefFieldName, masterRecordId);
                        detailParamMaps.add(detailParamMap);
                    }
                    detailBuilder = new SqlDeleteCmdBuilder(stmt, detailParamMaps);
                }
                SqlDeleteCmd detailCmd = detailBuilder.build();
                deleteInfos.addDetailDeleteCmd(detailObject, detailCmd);
            });
        }

        return deleteInfos;
    }

    private Map<String, Object> f(Map<String, Object> paramMap, String masterRefFieldName) {
        Object masterRecordId = this.extractMasterId(paramMap);
        Map<String, Object> detailParamMap = new HashMap<>();
        if (masterRecordId instanceof List) {
            detailParamMap.put(masterRefFieldName + 's', masterRecordId);
        } else {
            detailParamMap.put(masterRefFieldName, masterRecordId);
        }
        return detailParamMap;
    }

    /**
     * 构建子表的删除语句，数据库中的指定主表记录ID的数据全删, where masterField = #{masterField}
     *
     * @param detailObject
     */
    private void buildDetailDeleteStmt(XObject detailObject) {
        XObjectRefField masterRefField = detailObject.getMasterField();
        OqlDeleteStatement detailStmt = this.getDetailObjectDeleteStmtByObject(detailObject);
        Map<String, Object> paramMap = !this.isBatch ? this.paramMap : this.paramMaps.get(0);
        Object masterPrimaryFieldValue = this.extractMasterId(paramMap);
        if (masterPrimaryFieldValue instanceof List) {
            detailStmt.setWhere(OqlUtils.buildFieldInListVarRefExpr(masterRefField));
        } else {
            detailStmt.setWhere(OqlUtils.buildFieldEqualsVarRefExpr(masterRefField));
        }
    }

    /**
     * 根据子模型获取它对应的单条删除语句
     *
     * @param detailObject
     * @return
     */
    private OqlDeleteStatement getDetailObjectDeleteStmtByObject(XObject detailObject) {
        OqlDeleteStatement stmt = this.detailObjectDeleteStmtMap.get(detailObject);
        if (stmt == null) {
            stmt = new OqlDeleteStatement();
            stmt.setFrom(OqlUtils.buildObjectSource(detailObject));
            this.detailObjectDeleteStmtMap.put(detailObject, stmt);
        }

        return stmt;
    }

}
