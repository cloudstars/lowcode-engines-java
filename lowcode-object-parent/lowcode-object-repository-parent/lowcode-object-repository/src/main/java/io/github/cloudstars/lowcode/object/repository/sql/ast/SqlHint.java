package io.github.cloudstars.lowcode.object.repository.sql.ast;

/**
 * 提示
 *
 * @author clouds
 */
public interface SqlHint extends SqlObject {

    @Override
    SqlHint cloneMe();
}