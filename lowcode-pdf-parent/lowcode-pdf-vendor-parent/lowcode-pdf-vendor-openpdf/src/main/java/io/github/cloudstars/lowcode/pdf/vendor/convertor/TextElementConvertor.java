package io.github.cloudstars.lowcode.pdf.vendor.convertor;

import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import io.github.cloudstars.lowcode.commons.datasource.DataSourceLoaderFactory;
import io.github.cloudstars.lowcode.commons.datasource.XDataSourceLoader;
import io.github.cloudstars.lowcode.commons.datasource.config.AbstractDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.util.StringUtils;
import io.github.cloudstars.lowcode.commons.value.type.TextValueTypeConfig;
import io.github.cloudstars.lowcode.pdf.commons.config.element.text.TextElementConfig;

@ElementConvertorClass(type = "TEXT")
public class TextElementConvertor extends AbstractElementConvertor<TextElementConfig, PdfPCell> {

    public TextElementConvertor(TextElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    public PdfPCell toPdfElement(TextElementConfig elementConfig) {
        AbstractDataSourceConfig<TextValueTypeConfig> dataSourceConfig = elementConfig.getDataSource();
        XDataSourceLoader dataSourceLoader = DataSourceLoaderFactory.newInstance(dataSourceConfig);
        Object data = dataSourceLoader.loadData();
        Paragraph paragraph = new Paragraph(data != null ? data.toString() : StringUtils.EMPTY);
        PdfPCell cell = new PdfPCell(paragraph); // 建立一个单元格
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT); // 设置内容水平居中显示
        return cell;
    }

}
