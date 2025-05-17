package io.github.cloudstars.object.engine;

import java.util.List;

/**
 * 模型引擎
 *
 * @author clouds
 */
public interface ObjectEngine {

    /**
     * 插入
     *
     * @param object
     * @return
     */
    int insert(XObject object);

    /**
     * 批量插入
     *
     * @param objects
     * @return
     */
    int[] batchInsert(List<XObject> objects);

    /**
     * 更新
     *
     * @param object
     * @return
     */
    int update(XObject object);

    /**
     * 批量更新
     *
     * @param objects
     * @return
     */
    int[] batchUpdate(List<XObject> objects);

}
