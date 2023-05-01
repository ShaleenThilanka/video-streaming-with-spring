package com.shaliya.springwebfluxvideostreaming.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.shaliya.springwebfluxvideostreaming.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    private Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Value("${bucketNameVideo}")
    private String bucketName;

    @Autowired
    private AmazonS3Client s3Client;

    @Override
    public Map<String, Object> uploadMultipleFile(List<MultipartFile> file) {
        try {
            Map<String, Object> map = new HashMap<>();
            File fileObj;
            String fileName = "";
            for (int i = 0; i < file.size(); i++) {
                fileObj = convertMultiPartFileToFile(file.get(i));
                fileName = System.currentTimeMillis() + "_" + file.get(i).getOriginalFilename();
                System.out.println(fileName);
                s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj).withCannedAcl(CannedAccessControlList.PublicRead));
                fileObj.delete();
                map.put(fileName,s3Client.getUrl(bucketName, fileName));
            }
            return map;
        }catch  (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean deleteFile(String fileName) {

        s3Client.deleteObject(bucketName, fileName);
        return true;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        var key = UUID.randomUUID().toString() + "." + filenameExtension;

        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            s3Client.putObject(bucketName, key, file.getInputStream(), metadata);
        } catch (IOException ioException) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An Exception occured while uploading the file");
        }

        s3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);

        return s3Client.getResourceUrl(bucketName, key);
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}