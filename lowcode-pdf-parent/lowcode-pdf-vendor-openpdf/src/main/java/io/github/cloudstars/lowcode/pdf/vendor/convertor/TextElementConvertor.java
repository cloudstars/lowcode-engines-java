package io.github.cloudstars.lowcode.pdf.vendor.convertor;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import io.github.cloudstars.lowcode.commons.datasource.DataSourceLoaderFactory;
import io.github.cloudstars.lowcode.commons.datasource.XDataSourceLoader;
import io.github.cloudstars.lowcode.commons.datasource.config.XDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.util.StringUtils;
import io.github.cloudstars.lowcode.value.type.TextValueTypeConfig;
import io.github.cloudstars.lowcode.pdf.commons.config.element.text.TextElementConfig;
import io.github.cloudstars.lowcode.pdf.vendor.PdfStyle;

@ElementConvertorClass(type = "TEXT", elementClass = TextElementConfig.class)
public class TextElementConvertor extends AbstractElementConvertor<TextElementConfig> {

    public TextElementConvertor(TextElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    public PdfPCell toPdfCellElement(TextElementConfig elementConfig) {
        XDataSourceConfig<TextValueTypeConfig> dataSourceConfig = elementConfig.getDataSource();
        XDataSourceLoader dataSourceLoader = DataSourceLoaderFactory.newInstance(dataSourceConfig);
        Object data = dataSourceLoader.loadData(null);
        String content = data != null ? data.toString() : StringUtils.EMPTY;
        Font contentFont = new Font(PdfStyle.BASE_FONT, 18, Font.NORMAL);
        Paragraph paragraph = new Paragraph(content, contentFont);
        PdfPCell cell = new PdfPCell(paragraph); // 建立一个单元格
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT); // 设置内容水平居中显示
        return cell;
    }

}
