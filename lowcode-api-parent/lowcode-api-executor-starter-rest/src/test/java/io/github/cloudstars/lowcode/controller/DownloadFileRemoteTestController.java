

package io.github.cloudstars.lowcode.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 用于测试API远程下载文件的Controller
 *
 * @author clouds
 */
@RestController
@RequestMapping("/remote/api/download")
public class DownloadFileRemoteTestController {

    @GetMapping("/{fileName}")
    public ResponseEntity<Object> downloadFile(@PathVariable String fileName) {
        assert fileName != null; // 断言文件名不为空，以防万一的空指针异常处理。实际开发中应避免使用断言，这里只是为了示例。
        Path targetLocation = Paths.get(FileTestUtils.getUploadFolder()).resolve(fileName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // 打开文件输入流
        try (FileInputStream fis = new FileInputStream(targetLocation.toFile());
             // 获取文件通道
             FileChannel fileChannel = fis.getChannel()) {

            // 创建一个ByteBuffer
            ByteBuffer buffer = ByteBuffer.allocate(1024); // 分配1024字节的缓冲区

            // 读取数据到缓冲区
            while (fileChannel.read(buffer) != -1) {
                buffer.flip(); // 切换为读模式

                // 处理数据
                while (buffer.hasRemaining()) {
                    byteArrayOutputStream.write(buffer.get());
                }

                buffer.clear(); // 切换为写模式，为下一次读取做准备
            }
        } catch (FileNotFoundException e) {
            return ResponseEntity.unprocessableEntity().body("Could not download the file: " + fileName + "!");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Could not download the file!");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);
    }

}
