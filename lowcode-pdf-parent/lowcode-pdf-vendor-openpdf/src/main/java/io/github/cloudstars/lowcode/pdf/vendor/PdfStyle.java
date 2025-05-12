package io.github.cloudstars.lowcode.pdf.vendor;

import com.lowagie.text.pdf.BaseFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * PDF样式
 *
 * @author clouds
 */
public class PdfStyle {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfStyle.class);

    private static String BASE_FONT_NAME = "STSong-Light";

    /**
     * 基础的字体
     */
    public static BaseFont BASE_FONT;

    static {
        try {
            BASE_FONT = BaseFont.createFont(BASE_FONT_NAME, "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (IOException e) {
            LOGGER.error("加载PDF构建的字体{}失败", e);
        }
    }

}
