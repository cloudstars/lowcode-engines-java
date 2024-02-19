package net.cf.form.repository.sql.ast;

/**
 * 提示
 *
 * @author clouds
 */
public interface SqlHint extends SqlObject {

    @Override
    SqlHint cloneMe();
}