package com.shaliya.springwebfluxvideostreaming.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileUploadService {
    Map<String, Object> uploadMultipleFile(List<MultipartFile> file);

    Boolean deleteFile(String fileName);
    String uploadFile(MultipartFile file);
}
