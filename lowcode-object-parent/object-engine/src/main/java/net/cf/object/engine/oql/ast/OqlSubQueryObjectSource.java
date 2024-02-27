package net.cf.object.engine.oql.ast;

import net.cf.object.engine.oql.AbstractOqlObjectImpl;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * OQL子查询数据源
 * 如：select * from (select * from a where ... ) where ... 中括内部分就是子查询数据源
 *
 * @author clouds
 */
public class OqlSubQueryObjectSource extends AbstractOqlObjectImpl {

    @Override
    protected void accept0(OqlAstVisitor visitor) {

    }
}
