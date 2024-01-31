package net.cf.api.engine.data.mapping;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/25 15:01
 */
public interface DataMappingHandler {
    Object handle(Object o);
    TypeBind getTypeBind();
}

