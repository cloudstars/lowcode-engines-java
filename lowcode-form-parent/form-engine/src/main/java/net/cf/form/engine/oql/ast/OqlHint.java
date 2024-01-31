package net.cf.form.engine.oql.ast;

/**
 * 提示
 *
 * @author clouds
 */
public interface OqlHint<T extends OqlHint> extends OqlObject<T> {

    @Override
    T clone();
}