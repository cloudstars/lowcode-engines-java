package net.cf.object.engine.oql.info;

import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.util.OqlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * OQL删除语句指令构建器
 * <p>
 * 职责：用于将一条OQL语句解析成本表的插入与子表的插入
 */
public class OqlDeleteInfoParser extends AbstractOqlInfoParser {

    /**
     * 输入的语句
     */
    private final OqlDeleteStatement stmt;

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 输入的参数（批量模式）
     */
    private final List<Map<String, Object>> paramMaps;

    /**
     * 本表的删除信息
     */
    private OqlDeleteInfo selfDeleteInfo;

    /**
     * 子表的删除信息列表
     */
    private List<OqlDetailDeleteInfo> detailDeleteInfos;

    public OqlDeleteInfoParser(OqlDeleteStatement stmt) {
        this(stmt, (Map<String, Object>) null);
    }

    public OqlDeleteInfoParser(OqlDeleteStatement stmt, Map<String, Object> paramMap) {
        super(false);
        this.stmt = stmt;
        this.paramMap = paramMap;
        this.paramMaps = null;
    }

    public OqlDeleteInfoParser(OqlDeleteStatement stmt, List<Map<String, Object>> paramMaps) {
        super(true);
        this.stmt = stmt;
        this.paramMap = null;
        this.paramMaps = paramMaps;
    }

    public OqlDeleteInfo getSelfDeleteInfo() {
        return selfDeleteInfo;
    }

    public List<OqlDetailDeleteInfo> getDetailDeleteInfos() {
        return detailDeleteInfos;
    }

    /**
     * 解析OQL语句，要考虑insert into (...) values (), (), ... 这种情况
     */
    public void parse() {
        XObject selfObject = this.stmt.getFrom().getResolvedObject();
        OqlDeleteInfo selfOqlInfo = new OqlDeleteInfo();
        selfOqlInfo.setObject(selfObject);
        selfOqlInfo.setStatement(this.stmt);
        selfOqlInfo.setParamMap(this.paramMap);
        this.selfDeleteInfo = selfOqlInfo;

        // 生成子模型的删除语句
        List<OqlExprObjectSource> detailFroms = this.stmt.getDetailFroms();
        if (detailFroms != null && detailFroms.size() > 0) {
            this.detailDeleteInfos = new ArrayList<>();
            for (OqlExprObjectSource detailFrom : detailFroms) {
                OqlDeleteStatement detailOqlDeleteStmt = new OqlDeleteStatement();
                detailOqlDeleteStmt.setFrom(detailFrom);
                // where masterId in (#{masterIds})
                XObjectRefField masterField = detailFrom.getResolvedObject().getObjectRefField(selfObject.getName());
                SqlInListExpr whereExpr = OqlUtils.buildFieldInListVarRefExpr(masterField);
                detailOqlDeleteStmt.setWhere(whereExpr);
                OqlDetailDeleteInfo detailDeleteInfo = new OqlDetailDeleteInfo();
                detailDeleteInfo.setStatement(detailOqlDeleteStmt);
                detailDeleteInfo.setObject(detailFrom.getResolvedObject());
                detailDeleteInfos.add(detailDeleteInfo);
            }
        }
    }

}
