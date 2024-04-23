package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.object.engine.object.XObject;

import java.util.List;

/**
 * OQL 模型源
 *
 * @author clouds
 */
public interface OqlObjectSource extends SqlTableSource {

    /**
     * 克隆自已
     *
     * @return
     */
    @Override
    OqlObjectSource cloneMe();

    /**
     * 获取解析后的模型
     *
     * @return
     */
    XObject getResolvedObject();

    /**
     * 获取解析后的子模型列表
     *
     * @return
     */
    List<XObject> getResolvedDetailObjects();

}
