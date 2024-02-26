package net.cf.form.engine;

import net.cf.form.engine.object.XObject;

import java.util.List;
import java.util.Map;

/**
 * 模型记录服务接口
 *
 * @author clouds
 */
public interface RecordEngine {

    /**
     * 查询一条记录，返回全部字段的数据
     *
     * @param object
     * @param recordId
     * @return
     */
    Map<String, Object> queryOne(XObject object, String recordId);

    /**
     * 查询一条记录，返回指定字段的数据
     *
     * @param object
     * @param recordId
     * @param fields 要查询的字段名列表
     * @return
     */
    Map<String, Object> queryOne(XObject object, String recordId, List<String> fields);

    /**
     * 查询批量记录，返回全部字段的数据
     *
     * @param object
     * @param recordIds
     * @return
     */
    List<Map<String, Object>> queryList(XObject object, List<String> recordIds);

    /**
     * 统计符合条件的记录数量
     *
     * @param object
     * @param queryMap
     * @return
     */
    int count(XObject object, Map<String, Object> queryMap);

    /**
     * 查询指定页的记录列表
     *
     * @param object
     * @param pageIndex
     * @param pageSize
     * @param queryMap
     * @return
     */
    List<Map<String, Object>> queryPageList(XObject object, int pageIndex, int pageSize, Map<String, Object> queryMap);

    /**
     * 查询批量记录，返回指定字段的数据
     *
     * @param object
     * @param recordIds
     * @param fields 要查询的字段名列表
     * @return
     */
    List<Map<String, Object>> queryList(XObject object, List<String> recordIds, List<String> fields);

    /**
     * 创建一条记录
     *
     * @param object 模型
     * @param data 数据
     * @return 记录ID
     */
    String createOne(XObject object, Object data);

    /**
     * 创建一条记录
     *
     * @param object 模型
     * @param dataMap 数据
     * @return 记录ID
     */
    String createOne(XObject object, Map<String, Object> dataMap);

    /**
     * 批量创建记录
     *
     * @param object
     * @param dataMaps
     * @return
     */
    List<String> createList(XObject object, List<Map<String, Object>> dataMaps);

    /**
     * 更新指定记录
     *
     * @param object
     * @param recordId
     * @param dataMap
     * @return
     */
    void modifyOne(XObject object, String recordId, Map<String, Object> dataMap);

    /**
     * 批量更新记录
     *
     * @param object
     * @param recordIds
     * @param dataMaps
     * @return
     */
    void modifyList(XObject object, List<String> recordIds, List<Map<String, Object>> dataMaps);

    /**
     * 移除一条记录
     *
     * @param object
     * @param recordId
     */
    void removeOne(XObject object, String recordId);

    /**
     * 批量移除记录
     *
     * @param object
     * @param recordIds
     */
    void removeOne(XObject object, List<String> recordIds);

}
