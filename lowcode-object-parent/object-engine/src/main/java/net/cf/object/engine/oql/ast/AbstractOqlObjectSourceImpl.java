package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.util.SqlUtils;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.AbstractOqlObjectImpl;

import java.util.List;


/**
 * OQL 模型数据源抽象实现类
 *
 * @author clouds
 */
public abstract class AbstractOqlObjectSourceImpl extends AbstractOqlObjectImpl implements OqlObjectSource {

    /**
     * 是否有"alias"关键字
     */
    protected boolean hasAliasKeyword;

    /**
     * 别名
     */
    protected String alias;

    /**
     * 解析后的模型
     */
    protected XObject resolvedObject;

    /**
     * 解析后的子模型列表
     */
    protected List<XObject> resolvedDetailObjects;

    public AbstractOqlObjectSourceImpl() {
    }

    public AbstractOqlObjectSourceImpl(String alias) {
        this.alias = alias;
    }

    public AbstractOqlObjectSourceImpl(SqlExpr flashback, String alias) {
        this.alias = alias;
    }

    @Override
    public boolean isHasAliasKeyword() {
        return hasAliasKeyword;
    }

    @Override
    public void setHasAliasKeyword(boolean hasAliasKeyword) {
        this.hasAliasKeyword = hasAliasKeyword;
    }

    @Override
    public String getAlias() {
        return this.alias;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean containsAlias(String alias) {
        return SqlUtils.nameEquals(this.alias, alias);
    }

    @Override
    public OqlExprObjectSource cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    @Override
    public XObject getResolvedObject() {
        return resolvedObject;
    }

    public void setResolvedObject(XObject resolvedObject) {
        this.resolvedObject = resolvedObject;
    }

    @Override
    public List<XObject> getResolvedDetailObjects() {
        return resolvedDetailObjects;
    }

    public void setResolvedDetailObjects(List<XObject> resolvedDetailObjects) {
        this.resolvedDetailObjects = resolvedDetailObjects;
    }
}
