package com.shaliya.springwebfluxvideostreaming.service;

import com.shaliya.springwebfluxvideostreaming.dto.Video;
import com.shaliya.springwebfluxvideostreaming.dto.responsedto.UploadVideoResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    Video getVideo(String name);

//    Mono<ByteArrayResource> getVideo(String name);

    String saveVideo(MultipartFile file, String name) throws IOException;

    List<String> getAllVideoNames();

    UploadVideoResponse uploadVideo(MultipartFile file);

    Video getVideoDetails(Long videoId);
}