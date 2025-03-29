
package io.github.cloudstars.lowcode.commons.api.template;

/**
 * 表单数据插入API配置模板
 *
 * @author clouds
 */
public abstract class AbstractApiConfigTemplate<PT extends Object> implements ApiConfigTemplate<PT> {

    /**
     * 模板代码
     */
    private String code;

    /**
     * 模板名称
     */
    private String name;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    protected AbstractApiConfigTemplate(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
