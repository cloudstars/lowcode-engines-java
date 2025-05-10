package io.github.cloudstars.lowcode.pdf.vendor.openpdf;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import io.github.cloudstars.lowcode.OpenPdfVendorTestApplication;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OpenPdfVendorTestApplication.class)
public class PdfBuilderTest {

    @Test
    public void test1() {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, new FileOutputStream("target/example1.pdf"));
            document.open();
            document.add(new Paragraph("Hello, OpenPDF!"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test2() throws Exception {
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        ByteArrayOutputStream os = new ByteArrayOutputStream();// 输出到客户段的流
        Document document = new Document(PageSize.A4); // 创建document对象
        PdfWriter.getInstance(document, os);// 创建书写器
        document.open(); // 打开文档

        {
            // 创建一个对中文字符集支持的基础字体
            Font titleFont = new Font(bfChinese, 16, Font.BOLD);
            PdfPTable topTable = new PdfPTable(2);
            topTable.setWidthPercentage(100f);
            topTable.setSpacingAfter(20f);
            Paragraph paragraph = new Paragraph("寄件服务明细", titleFont);
            PdfPCell cell = new PdfPCell(paragraph); // 建立一个单元格
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT); // 设置内容水平居中显示
            topTable.addCell(cell);

            document.add(topTable);
        }

        {
            Font headFont = new Font(bfChinese, 12, Font.BOLD, Color.WHITE);
            float[] widths = new float[]{5, 10, 15, 10, 10, 15, 10, 15, 10};
            String[] tableTitle = new String[]{"#", "业务类型", "运单号码", "订单时间", "寄方", "寄件地", "收方", "收件地", "金额(元)"};
            Table bodyTable = new Table(tableTitle.length);
            bodyTable.setWidths(widths);
            bodyTable.setWidth(100);
            bodyTable.setBorder(0);
            Chunk chunk;
            Cell cell1;
            for (int i = 0; i < tableTitle.length; i++) {
                chunk = new Chunk(tableTitle[i], headFont);
                cell1 = new Cell(chunk); // 建立一个单元格
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER); // 设置内容水平居中显示
                cell1.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直居中
                cell1.setBackgroundColor(Color.DARK_GRAY);
                cell1.setBorderColor(Color.WHITE);
                bodyTable.addCell(cell1);
            }
            document.add(bodyTable);
        }

        document.close();// 关闭文档

        try {
            byte[] byteArray = os.toByteArray(); // 获取字节数组
            // 使用FileUtils写入文件，这将自动处理文件输出流和输入流的关闭
            FileUtils.writeByteArrayToFile(new File("target/example2.pdf"), byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os); // 确保关闭流以释放资源，即使是在异常发生时。使用IOUtils可以更优雅地处理。
        }
    }

}
