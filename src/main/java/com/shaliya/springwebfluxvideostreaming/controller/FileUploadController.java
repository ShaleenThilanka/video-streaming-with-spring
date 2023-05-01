package com.shaliya.springwebfluxvideostreaming.controller;

import com.shaliya.springwebfluxvideostreaming.service.FileUploadService;
import com.shaliya.springwebfluxvideostreaming.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/record")
public class FileUploadController {
    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }


    @PostMapping("/upload")
    public ResponseEntity<StandardResponse> uploadMultipleFile(
            @RequestParam("file") List<MultipartFile> file) {
        Map<String, Object> fileUrl=fileUploadService.uploadMultipleFile(file);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "File is Successfully Saved", fileUrl),
                HttpStatus.CREATED);

    }

}
