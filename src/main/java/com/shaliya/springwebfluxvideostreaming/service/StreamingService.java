package com.shaliya.springwebfluxvideostreaming.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

public interface StreamingService {
    Mono<Resource> getVideo(String title);

    Mono<ByteArrayResource> getVideoFromDatabase(String videoId);
}
