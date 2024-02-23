package net.cf.excel.engine.commons.parse;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-22 18:54
 * @Description: excel解析用field集合
 */
public interface GroupParseField extends ParseField {
    List<SingleParseField> getSubField();
}
