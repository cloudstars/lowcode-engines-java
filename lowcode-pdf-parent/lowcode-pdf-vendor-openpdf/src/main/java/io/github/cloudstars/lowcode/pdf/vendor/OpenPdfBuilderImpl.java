package io.github.cloudstars.lowcode.pdf.vendor;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;
import io.github.cloudstars.lowcode.pdf.commons.config.PageSizeEnum;
import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;
import io.github.cloudstars.lowcode.pdf.commons.config.element.PdfConstants;
import io.github.cloudstars.lowcode.pdf.commons.config.element.XElementConfig;
import io.github.cloudstars.lowcode.pdf.vendor.convertor.ElementConvertorFactory;
import io.github.cloudstars.lowcode.pdf.vendor.convertor.XElementConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 基于OpenPdf的PDF生成实现
 *
 * @author clouds
 */
public class OpenPdfBuilderImpl implements PdfBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenPdfBuilderImpl.class);

    private PdfBuildConfig config;

    public OpenPdfBuilderImpl(PdfBuildConfig config) {
        this.config = config;
    }

    @Override
    public PdfBuildConfig getConfig() {
        return config;
    }

    @Override
    public void build(Map<String, Object> dataMap, OutputStream os) {
        try (Document document = this.buildDocument(os)) {
            PdfPTable bodyTable = this.buildBodyTable();
            document.add(bodyTable);
        }
    }

    /**
     * 构建PDF文档
     *
     * @param os 输出流
     * @return PDF文档
     */
    private Document buildDocument(OutputStream os) {
        Rectangle pageSize = this.parsePageSize(this.config);
        Document document = new Document(pageSize);
        PdfWriter.getInstance(document, os);// 创建书写器
        document.open(); // 打开文档
        return document;
    }

    /**
     * 解析纸张大小配置
     *
     * @param config PDF生成配置
     * @return 纸张大小
     */
    private Rectangle parsePageSize(PdfBuildConfig config) {
        PageSizeEnum pageSize = config.getPageSize();
        if (pageSize == PageSizeEnum.A3) {
            return PageSize.A3;
        }

        if (pageSize == PageSizeEnum.A4) {
            return PageSize.A4;
        }

        if (pageSize == PageSizeEnum.A5) {
            return PageSize.A5;
        }

        return PageSize.A4;
    }

    /**
     * 构建PDF主体表格
     *
     * @return 主体表格
     */
    private PdfPTable buildBodyTable() {
        PdfPTable bodyTable = new PdfPTable(PdfConstants.MAX_ELEMENT_SIZE);
        bodyTable.setWidthPercentage(100f);
        bodyTable.setSpacingAfter(20f);
        this.buildBodyElements(bodyTable);

        return bodyTable;
    }

    /**
     * 构建PDF文档主体的元素全部元素
     *
     * @param bodyTable PDF文档主体
     */
    private void buildBodyElements(PdfPTable bodyTable) {
        List<XElementConfig> elementConfigs = this.config.getElements();
        int columnSize = this.config.getColumnSize(); // 分栏数量
        int perElementSize = PdfConstants.MAX_ELEMENT_SIZE / columnSize; // 每一个元素的占比
        int currRowSize = 0;
        // 依次生成每一个PDF元素
        for (XElementConfig elementConfig : elementConfigs) {
            int elementSize = ObjectUtils.getOrDefault(elementConfig.getSize(), perElementSize);
            if (elementSize + currRowSize > PdfConstants.MAX_ELEMENT_SIZE) {
                // 补足最后的空白区域
                PdfPCell blankCell = this.buildBlankCell(PdfConstants.MAX_ELEMENT_SIZE - elementSize);
                bodyTable.addCell(blankCell);
                currRowSize = elementSize;
            } else {
                currRowSize += elementSize;
            }

            PdfPCell elementLabelCell = this.buildElementLabelCell(elementConfig, 2);
            PdfPCell elementContentCell = this.buildElementContentCell(elementConfig, elementSize - 2);
            bodyTable.addCell(elementLabelCell);
            bodyTable.addCell(elementContentCell);
        }

        // 补足最后一行的空白区域（不补足会报错）
        if (currRowSize != 0 && currRowSize < PdfConstants.MAX_ELEMENT_SIZE) {
            PdfPCell blankCell = this.buildBlankCell(PdfConstants.MAX_ELEMENT_SIZE - currRowSize);
            bodyTable.addCell(blankCell);
        }
    }

    /**
     * 构建一个空白单元格
     */
    private PdfPCell buildBlankCell(int columnSize) {
        PdfPCell cell = new PdfPCell();
        cell.setColspan(columnSize);
        cell.setBorder(0);
        return cell;
    }

    /**
     * 构建PDF标题元素
     *
     * @param elementConfig PDF元素配置
     * @param elementLabelSize PDF元素标题占比
     * @return PDF元素标题单元格
     */
    private PdfPCell buildElementLabelCell(XElementConfig elementConfig, int elementLabelSize) {
        Font labelFont = new Font(PdfStyle.BASE_FONT, 16, Font.BOLD);
        String label = elementConfig.getLabel();
        Paragraph paragraph = new Paragraph(label, labelFont);
        PdfPCell labelCell = new PdfPCell(paragraph);
        labelCell.setColspan(elementLabelSize);
        labelCell.setBorder(0);
        labelCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        return labelCell;
    }

    /**
     * 构建PDF元素内容
     *
     * @param elementConfig PDF元素配置
     * @param elementContentSize PDF元素内容占比
     * @return PDF元素内容单元格
     */
    private PdfPCell buildElementContentCell(XElementConfig elementConfig, int elementContentSize) {
        XElementConvertor convertor = ElementConvertorFactory.newInstance(elementConfig);
        PdfPCell contentCell = convertor.toPdfCellElement(elementConfig);
        contentCell.setColspan(elementContentSize);
        contentCell.setBorder(0);
        contentCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        return contentCell;
    }

}
