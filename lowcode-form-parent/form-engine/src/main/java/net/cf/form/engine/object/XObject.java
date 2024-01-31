package net.cf.form.engine.object;

import net.cf.form.engine.field.UniqueFields;
import net.cf.form.engine.field.XField;
import net.cf.form.engine.repository.data.DbType;

import java.util.List;
import java.util.Map;

/**
 * @author clouds
 *
 * 动态对象接口
 */
public interface XObject {

    /**
     * 获取对象的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取对象的标题
     *
     * @return
     */
    String getTitle();

    /**
     * 是否多租户对象
     *
     * @return
     */
    boolean isMultiTenant();

    /**
     * 获取对象的字段列表
     *
     * @return
     */
    List<XField> getFields();

    /**
     * 是否由驱动生成的主键
     *
     * @return
     */
    boolean isAutoPrimaryField();

    /**
     * 获取主键字段的名称
     *
     * @return
     */
    String getPrimaryFieldName();

    /**
     * 获取唯一性的字段列表（引擎默认会进行数据唯一性校验）
     *
     * @return
     */
    List<UniqueFields> getUniqueFields();

    /**
     * 校验函数
     * @return
     */
    ValidationResult validate(Map<String, Object> dataMap);

    /**
     * 是否持久化对象
     *
     * @return
     */
    @Deprecated
    boolean isPersistence();

    /**
     * 获取持久化数据库的类型
     *
     * @return
     */
    DbType getDbType();

    /**
     * 获取持久化表的名称
     *
     * @return
     */
    String getTableName();

    /**
     * 获取子对象（一对多）
     *
     * @return
     */
    List<XObject> getSubObjects();

    /**
     * 获取一对一的相关对象
     *
     * @return
     */
    List<XObject> getOneByOneRefObjects();

    /**
     * 获取一对多的相关对象
     *
     * @return
     */
    List<XObject> getOneByManyRefObjects();

    /**
     * 获取父对象（一对一）
     *
     * @return
     */
    XObject getParentObject();
}
