package io.github.cloudstars.lowcode.object.editor.config;

/**
 * 文本字段配置
 *
 * @author clouds
 */
public class TextFieldConfig extends AbstractFieldConfig {

    /**
     * 最小长度
     */
    private Integer minLength;

    /**
     * 最大长度
     */
    private Integer maxLength;

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

}
