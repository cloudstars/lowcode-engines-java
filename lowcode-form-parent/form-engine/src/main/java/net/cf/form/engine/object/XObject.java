package net.cf.form.engine.object;

import java.util.List;
import java.util.Map;

/**
 * @author clouds
 *
 * 动态模型接口
 */
public interface XObject {

    /**
     * 获取模型的编号
     *
     * @return
     */
    String getCode();

    /**
     * 获取模型的名称
     *
     * @return
     */
    String getName();

    /**
     * 是否多租户模型
     *
     * @return boolean isMultiTenant(); */

    /**
     * 获取模型的字段列表
     *
     * @return
     */
    <T extends XField> List<T> getFields();

    /**
     * 根据字段名称获取字段
     *
     * @param fieldCode
     * @return
     */
    <T extends XField> T getField(String fieldCode);

    /**
     * 获取主键字段
     *
     * @return
     */
    <T extends XField> T getPrimaryField();

    /**
     * 是否由驱动生成的主键
     *
     * @return
     */
    boolean isAutoPrimaryField();

    /**
     * 校验函数
     *
     * @return
     */
    void validate(Map<String, Object> dataMap);

    /**
     * 获取表的名称
     *
     * @return
     */
    String getTableName();
}
