package com.example.lrb.service.impl;

import com.example.lrb.service.UploadService;
import com.example.lrb.vo.JsonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    private final Path uploadRoot = Paths.get("uploads").toAbsolutePath().normalize();

    @Override
    public JsonResult<String> uploadImage(MultipartFile file) throws IOException {
        return upload(file, "image");
    }

    @Override
    public JsonResult<String> uploadVideo(MultipartFile file) throws IOException {
        return upload(file, "video");
    }

    private JsonResult<String> upload(MultipartFile file, String directory) throws IOException {
        if (file == null || file.isEmpty()) {
            return JsonResult.badRequest("文件不能为空");
        }

        String originalName = file.getOriginalFilename();
        String suffix = "";
        if (originalName != null && originalName.lastIndexOf('.') >= 0) {
            suffix = originalName.substring(originalName.lastIndexOf('.')).toLowerCase();
        }

        Path targetDirectory = uploadRoot.resolve(directory);
        Files.createDirectories(targetDirectory);
        String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
        Path target = targetDirectory.resolve(fileName).normalize();
        if (!target.startsWith(targetDirectory)) {
            return JsonResult.badRequest("非法文件名");
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
        }

        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(directory)
                .path("/")
                .path(fileName)
                .toUriString();
        return JsonResult.ok("上传成功", url);
    }
}
