package com.shaliya.springwebfluxvideostreaming.controller;



import com.shaliya.springwebfluxvideostreaming.service.StreamingService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class StreamingController {
    private StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @GetMapping(value = "v1/video/{title}")
    public Mono<Resource> getVideo(@PathVariable String title, @RequestHeader("Range") String range){
        System.out.println("range :"+ range);
        return streamingService.getVideo(title);
    }

    @GetMapping(value = "video/{videoId}")
    public Mono<ByteArrayResource> getVideoFromDatabase(@PathVariable String videoId, @RequestHeader("Range") String range){
        System.out.println("range in data base vide0 :"+ range);
        return streamingService.getVideoFromDatabase(videoId);
    }


//    @GetMapping(value = "video/{videoId}")
//    public ResponseEntity<StandardResponse> getVideoFromDatabase(@PathVariable String videoId, @RequestHeader("Range") String range){
//        System.out.println("range :"+ range);
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(201, "File is Successfully Get by name", new ByteArrayResource(videoService.getVideo(videoId).getData())),
//                HttpStatus.CREATED);
////        return streamingService.getVideoFromDatabase(videoId);
//    }
}
