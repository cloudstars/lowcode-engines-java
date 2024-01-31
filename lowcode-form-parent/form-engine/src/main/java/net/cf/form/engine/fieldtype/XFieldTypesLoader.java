package net.cf.form.engine.fieldtype;

import java.util.List;

/**
 * 字段类型加载器
 *
 * @author clouds
 */
@Deprecated
public interface XFieldTypesLoader {

    /**
     * 加载函数
     *
     * @return
     */
    List<XFieldType> load();
}
