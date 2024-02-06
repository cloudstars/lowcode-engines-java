package net.cf.api.engine;

import net.cf.api.commons.definition.ApiDefinition;

/**
 * api definition 加载器
 * @author 80345746
 * @version v1.0
 * @date 2024/2/2 8:50
 */
public interface ApiDefinitionLoader {
    public ApiDefinition find(String apiKey);
}
