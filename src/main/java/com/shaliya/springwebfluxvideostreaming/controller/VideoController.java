package com.shaliya.springwebfluxvideostreaming.controller;

import com.shaliya.springwebfluxvideostreaming.service.VideoService;
import com.shaliya.springwebfluxvideostreaming.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/video")
@AllArgsConstructor
public class VideoController {
    private VideoService videoService;

    @PostMapping()
    public ResponseEntity<StandardResponse> saveVideo(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException {
        System.out.println("1");
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "File is Successfully Saved",  videoService.saveVideo(file, name)),
                HttpStatus.CREATED);

    }

    @GetMapping("{name}")
    public ResponseEntity<StandardResponse> getVideoByName(@PathVariable("name") String name){
        System.out.println("2");
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "File is Successfully Get by name", new ByteArrayResource(videoService.getVideo(name).getData())),
                HttpStatus.CREATED);

    }
//    @GetMapping("{name}")
//    public ResponseEntity<StandardResponse> getVideoByName(@PathVariable("name") String name){
//        System.out.println("2");
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(201, "File is Successfully Get by name", videoService.getVideo(name)),
//                HttpStatus.CREATED);
//
//    }
    @GetMapping("all")
    public ResponseEntity<StandardResponse> getAllVideoNames(){
        System.out.println("3");
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "File is Successfully Get all", videoService.getAllVideoNames()),
                HttpStatus.CREATED);

    }



    //=======================================================================
    @PostMapping("/upload-video")

    public ResponseEntity<StandardResponse> uploadVideo(@RequestParam("file") MultipartFile file) {
        System.out.println("4");
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "File is Successfully Saved", videoService.uploadVideo(file)),
                HttpStatus.CREATED);

    }

    @GetMapping(path = "/get-video-by-id/{videoId}")
    public ResponseEntity<StandardResponse> getVideoDetails(@PathVariable Long videoId) {
        System.out.println("5");
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "File is Successfully Get", videoService.getVideoDetails(videoId)),
                HttpStatus.CREATED);

    }
}
