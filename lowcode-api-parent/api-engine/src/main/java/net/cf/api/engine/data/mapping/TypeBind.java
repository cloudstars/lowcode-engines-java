package net.cf.api.engine.data.mapping;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/25 18:18
 */
public class TypeBind {
    String targetType;
    String sourceType;

    public TypeBind(String targetType, String sourceType) {
        this.targetType = targetType;
        this.sourceType = sourceType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TypeBind) {
            TypeBind other = (TypeBind) obj;
            return this.sourceType == other.sourceType && this.targetType == other.targetType;
        }
        return false;
    }
}
