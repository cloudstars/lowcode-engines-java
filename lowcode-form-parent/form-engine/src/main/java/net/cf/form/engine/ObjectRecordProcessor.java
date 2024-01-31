package net.cf.form.engine;

import net.cf.form.engine.object.XObject;

import java.util.Map;

/**
 * 对象数据处理器
 *
 * @author clouds
 */
@Deprecated
public interface ObjectRecordProcessor {

    /**
     * 数据处理（如校验、生成初始值、格式化、反格式化等）
     *
     * @param object
     * @param dataMap
     */
    void process(XObject object, Map<String, Object> dataMap);

    /**
     * 获取获处器特性
     *
     * @return
     */
    ObjectRecordProcessFeature[] getFeatures();

    /**
     * 设置处理器特性
     *
     * @param features
     */
    void setFeatures(ObjectRecordProcessFeature[] features);
}
