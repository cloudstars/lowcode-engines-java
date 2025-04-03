package net.cf.object.engine.oql.ast;

import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * OQL子查询数据源
 * 如：select * from (select * from a where ... ) where ... 中括内部分就是子查询数据源
 *
 * @author clouds
 */
public class OqlSubQueryObjectSource extends AbstractOqlObjectSourceImpl {

    @Override
    public void accept(OqlAstVisitor visitor) {

    }

}
