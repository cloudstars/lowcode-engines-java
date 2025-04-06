package net.cf.api.engine.data.mapping;

import java.util.Objects;

/**
 * 类型绑定对象
 * @author 80345746
 * @version v1.0
 * @date 2024/1/25 18:18
 */
public class TypeBind {
    private String targetType;
    private String sourceType;

    public TypeBind(String targetType, String sourceType) {
        this.targetType = targetType;
        this.sourceType = sourceType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TypeBind) {
            TypeBind other = (TypeBind) obj;
            return Objects.equals(this.sourceType, other.sourceType) && Objects.equals(this.targetType, other.targetType);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetType, sourceType);
    }

}
