package io.github.cloudstars.lowcode.object.view;

/**
 * 视图操作（特指前端的操作）
 *
 * @author clouds
 */
public interface ObjectViewAction<T extends Object, R extends Object> {

    /**
     * 获取操作的名称
     *
     * @return
     */
    String getName();

}
