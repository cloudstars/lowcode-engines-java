package io.github.cloudstars.lowcode.object.view;

/**
 * 模型视图接口
 *
 * @author clouds
 */
public interface ObjectView {

    /**
     * 执行视图API
     *
     * @param viewApi
     * @return
     */
    Object execute(ObjectViewApi viewApi);

}
