
package net.cf.object.engine.oqlnew.parser;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oqlnew.cmd.OqlDeleteInfos;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * OQL删除语句解析器
 * <p>
 * 职责：用于将一条OQL删除语句解析成本表的删除与子表的删除
 */
public class OqlDeleteInfosParser extends AbstractOqInfoParser<OqlDeleteStatement, OqlDeleteInfos> {

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 输入的参数（批量模式）
     */
    private final List<Map<String, Object>> paramMaps;


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

        // 构建本模型信息
        /*OqlDeleteCmd selfDeleteInfo = new OqlDeleteCmd();
        selfDeleteInfo.setResolvedObject(this.masterObject);
        selfDeleteInfo.setBatch(this.isBatch);
        selfDeleteInfo.setParamMap(this.paramMap);
        selfDeleteInfo.setParamMaps(this.paramMaps);*/

        //selfDeleteInfo.setFieldMappings();
        List<OqlExprObjectSource> detailObjectSources = stmt.getDetailFroms();
        if (!CollectionUtils.isEmpty(detailObjectSources)) {
            for (OqlExprObjectSource detailObjectSource : detailObjectSources) {
                XObject detailObject = detailObjectSource.getResolvedObject();
                // delete from detail where masterField in (#{masterFields})
                //OqlDeleteCmd detailDeleleInfo = new OqlDeleteCmd();
                //detailDeleleInfo.setResolvedObject(detailObject);
            }
        }


        return null;
    }

}
