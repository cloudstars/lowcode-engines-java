package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlObject;

/**
 * OQL 模型源
 *
 * @author clouds
 */
public interface OqlObjectSource extends SqlObject {

    /**
     * 获取别名
     *
     * @return
     */
    String getAlias();

    /**
     * 设置别名
     *
     * @param alias
     */
    void setAlias(String alias);

    /**
     * 克隆自已
     *
     * @return
     */
    OqlObjectSource cloneMe();

}
