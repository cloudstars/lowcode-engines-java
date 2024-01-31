package net.cf.form.engine.repository.oql.ast;

/**
 * 提示
 *
 * @author clouds
 */
@Deprecated
public interface OqlHint<T extends OqlHint> extends OqlObject<T> {

    @Override
    T clone();
}