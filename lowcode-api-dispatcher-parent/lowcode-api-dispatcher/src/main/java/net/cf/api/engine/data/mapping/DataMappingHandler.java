package net.cf.api.engine.data.mapping;
/**
 * 数据类型转换handler
 * @author 80345746
 * @version v1.0
 * @date 2024/1/25 15:01
 */
public interface DataMappingHandler {
    /**
     * 处理方法
     * @param o 处理参数
     * @return 处理结果
     */
    Object handle(Object o);

    /**
     * 获取类型
     * @return 返回结果
     */
    TypeBind getTypeBind();
}

