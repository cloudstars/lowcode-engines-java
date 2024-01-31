package net.cf.form.engine.repository.sql.ast;

/**
 * 提示
 *
 * @author clouds
 */
public interface SqlHint extends SqlObject {

    @Override
    SqlHint _clone();
}