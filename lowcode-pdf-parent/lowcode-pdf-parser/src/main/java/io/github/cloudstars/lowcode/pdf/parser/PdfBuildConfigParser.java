package io.github.cloudstars.lowcode.pdf.parser;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.pdf.commons.config.PageSizeEnum;
import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;
import io.github.cloudstars.lowcode.pdf.commons.config.element.AbstractElementConfig;
import io.github.cloudstars.lowcode.pdf.commons.config.element.XElementConfig;

import java.util.List;

/**
 * PDF构建配置解析器
 *
 * @author clouds
 */
public class PdfBuildConfigParser implements XConfigParser<PdfBuildConfig> {

    // 纸张大小
    private static final String ATTR_PAGE_SIZE = "pageSize";

    // 分栏数
    private static final String ATTR_COLUMN_SIZE = "columnSize";

    // 元素列表
    private static final String ATTR_ELEMENTS = "elements";

    @Override
    public PdfBuildConfig parse(JsonObject configJson) {
        PdfBuildConfig config = new PdfBuildConfig();
        ConfigUtils.consumeIfPresent(configJson, ATTR_PAGE_SIZE, (v) -> {
            config.setPageSize(PageSizeEnum.valueOf((String) v));
        });
        ConfigUtils.consumeIfPresent(configJson, ATTR_COLUMN_SIZE, (v) -> {
            config.setColumnSize((Integer) v);
        });
        ConfigUtils.consumeIfPresent(configJson, ATTR_ELEMENTS, (v) -> {
            config.setElements(this.parseElements((JsonArray) v));
        });

        return config;
    }

    /**
     * 解析元素列表
     *
     * @param elementsConfigJson 元素列表配置
     * @return 元素列表
     */
    private List<XElementConfig> parseElements(JsonArray elementsConfigJson) {
        List<XElementConfig> elementConfigs =
                ConfigUtils.fromJsonArray(elementsConfigJson, (elementConfigJson) -> {
                    String type = ConfigUtils.getString(elementConfigJson, XTypedConfig.ATTR);
                    AbstractElementConfigParser<AbstractElementConfig> elementConfigParser = ElementConfigParserFactory.getInstance(type);
                    XElementConfig elementConfig = elementConfigParser.parse(elementConfigJson);
                    return elementConfig;
                });

        return elementConfigs;
    }

}
