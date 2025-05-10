package io.github.cloudstars.lowcode.pdf.vendor.openpdf;


import io.github.cloudstars.lowcode.OpenPdfVendorTestApplication;
import io.github.cloudstars.lowcode.pdf.vendor.OpenPdfBuilderImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OpenPdfVendorTestApplication.class)
public class OpenPdfBuildTest {

    @Resource
    private OpenPdfBuilderImpl openPdfVendor;

    @Test
    public void test() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();// 输出到客户段的流
        this.openPdfVendor.build(new HashMap<>(), byteArrayOutputStream);

        try {
            String data = "Hello, World!";
            byteArrayOutputStream.write(data.getBytes(StandardCharsets.UTF_8)); // 使用字符集编码确保正确处理字符串编码问题
            byte[] byteArray = byteArrayOutputStream.toByteArray(); // 获取字节数组

            // 使用FileUtils写入文件，这将自动处理文件输出流和输入流的关闭
            FileUtils.writeByteArrayToFile(new File("test.pdf"), byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(byteArrayOutputStream); // 确保关闭流以释放资源，即使是在异常发生时。使用IOUtils可以更优雅地处理。
        }
    }

}
