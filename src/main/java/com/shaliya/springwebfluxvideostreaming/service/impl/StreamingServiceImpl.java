package com.shaliya.springwebfluxvideostreaming.service.impl;

import com.shaliya.springwebfluxvideostreaming.service.StreamingService;
import com.shaliya.springwebfluxvideostreaming.service.VideoService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StreamingServiceImpl implements StreamingService {

    private final static String FORMAT = "classpath:video/%s.mp4";
    private ResourceLoader resourceLoader;
    private VideoService videoService;

    public StreamingServiceImpl(ResourceLoader resourceLoader, VideoService videoService) {
        this.resourceLoader = resourceLoader;
        this.videoService = videoService;
    }

    public Mono<Resource> getVideo(String title){
        return Mono.fromSupplier(()->resourceLoader.getResource(String.format(FORMAT,title)));
    }

    public Mono<ByteArrayResource> getVideoFromDatabase(String videoId) {
        return Mono.fromSupplier(()-> new ByteArrayResource(videoService.getVideo(videoId).getData()));
    }
}
