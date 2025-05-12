package io.github.cloudstars.lowcode.pdf.vendor;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import io.github.cloudstars.lowcode.pdf.commons.config.PageSizeEnum;
import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;
import io.github.cloudstars.lowcode.pdf.commons.config.element.PdfConstants;
import io.github.cloudstars.lowcode.pdf.commons.config.element.XElementConfig;
import io.github.cloudstars.lowcode.pdf.vendor.convertor.ElementConvertorFactory;
import io.github.cloudstars.lowcode.pdf.vendor.convertor.XElementConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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

    private BaseFont bfChinese;


    public OpenPdfBuilderImpl(PdfBuildConfig config) {
        this.config = config;
        String fontName = "STSong-Light";
        try {
            this.bfChinese = BaseFont.createFont(fontName, "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (IOException e) {
            LOGGER.error("加载PDF构建的字体{}失败", e);
        }
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
        int columnSize = this.config.getColumnSize();
        int currRowSize = 0;
        // 依次生成每一个PDF元素
        for (XElementConfig elementConfig : elementConfigs) {
            int eleSize = elementConfig.getSize();
            if (eleSize + currRowSize > PdfConstants.MAX_ELEMENT_SIZE) {
                // 补足最后的空白区域
                PdfPCell blankCell = this.buildBlankCell(PdfConstants.MAX_ELEMENT_SIZE - eleSize);
                bodyTable.addCell(blankCell);
            }

            PdfPCell elementCell = this.buildElementCell(elementConfig);
            Element cellContent = this.buildCellContent(elementConfig);
            elementCell.addElement(cellContent);
            bodyTable.addCell(elementCell);
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
     * 构建PDF元素
     *
     * @param elementConfig PDF元素配置
     * @return 元素单元格
     */
    private PdfPCell buildElementCell(XElementConfig elementConfig) {
        int elementSize = elementConfig.getSize();
        PdfPCell cell = new PdfPCell();
        cell.setColspan(elementSize);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        return cell;
    }

    private Element buildCellContent(XElementConfig elementConfig) {
        XElementConvertor convertor = ElementConvertorFactory.newInstance(elementConfig);
        Element element = convertor.toPdfElement(elementConfig);
        return element;
    }

}
