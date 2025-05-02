

package io.github.cloudstars.lowcode.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 用于测试API远程上传文件的Controller
 *
 * @author clouds
 */
@RestController
@RequestMapping("/remote/api/upload")
public class UploadFileRemoteTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileRemoteTestController.class);

    @PostMapping("/0")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Cannot upload empty file");
            }

            if (file.getSize() > 2000000) { // 文件大小限制为2MB，可根据需要调整。
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File size exceeds allowed limit");
            }

            String contentType = file.getContentType();
            if (!contentType.equals("image/png")
                    && !contentType.equals("image/jpeg")
                    && !contentType.equals("application/xml")) { // 示例：只允许上传PNG或JPEG图片或XML。可根据需要调整。
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Unsupported file type");
            }

            // 获取文件名称（包含扩展名）
            String fileName = file.getOriginalFilename();
            assert fileName != null; // 断言文件名不为空，以防万一的空指针异常处理。实际开发中应避免使用断言，这里只是为了示例。
            Path targetLocation = Paths.get(FileTestUtils.getUploadFolder()).resolve(fileName);
            File targetFile = targetLocation.toFile();
            if (targetFile.exists()) { // 如果存在先删除，方便跑单测
                targetFile.delete();
            }

            Files.copy(file.getInputStream(), targetLocation);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/remote/api/download/")
                    .path(fileName)
                    .toUriString();
            return ResponseEntity.ok("File uploaded successfully: " + fileDownloadUri);
        } catch (IOException ex) {
            LOGGER.error("Could not upload the file: {}", file.getOriginalFilename(), ex);
            return ResponseEntity.internalServerError().body("Could not upload the file: " + file.getOriginalFilename() + "!");
        }
    }

}
