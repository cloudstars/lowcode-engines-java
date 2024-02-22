package net.cf.excel.engine.commons.parse;

import net.cf.excel.engine.commons.DataType;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-22 16:49
 * @Description: excel表字段
 */
public interface ExcelSheetField {
    String getCode();

    String getName();

    DataType getDataType();

    DataFormatter getDataFormatter();

    List<ExcelSheetField> getSubFields();

    ExcelSheetField getParentField();

    default boolean isMainField() {
        return getParentField() == null;
    }

    default String getUniqueName() {
        if (isMainField()) {
            return getName();
        }else return getParentField().getUniqueName() + "." + getName();
    }

    default boolean hasSubField() {
        return getSubFields() != null && !CollectionUtils.isEmpty(getSubFields());
    }
}
