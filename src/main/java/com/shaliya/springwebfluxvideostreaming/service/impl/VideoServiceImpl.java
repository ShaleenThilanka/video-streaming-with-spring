package com.shaliya.springwebfluxvideostreaming.service.impl;

import com.shaliya.springwebfluxvideostreaming.dto.Video;
import com.shaliya.springwebfluxvideostreaming.dto.responsedto.UploadVideoResponse;
import com.shaliya.springwebfluxvideostreaming.exception.VideoException;
import com.shaliya.springwebfluxvideostreaming.repo.VideoRepo;
import com.shaliya.springwebfluxvideostreaming.service.FileUploadService;
import com.shaliya.springwebfluxvideostreaming.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {
    private VideoRepo repo;
    private FileUploadService fileUploadService;

    @Override
    public Video getVideo(String name) {
        if(!repo.existsByName(name)){
            throw new VideoException();
        }
        return  repo.findByName(name);
    }

//    @Override
//    public Mono<ByteArrayResource> getVideo(String name) {
//        if(!repo.existsByName(name)){
//            throw new VideoException();
//        }
//
//        return Mono.fromSupplier(()-> new ByteArrayResource(repo.findByName(name).getData()));
////        return  repo.findByName(name);
//    }

    @Override
    public List<String> getAllVideoNames() {
        return repo.getAllEntryNames();
    }
    @Override
    public String saveVideo(MultipartFile file, String name) throws IOException {
        if(repo.existsByName(name)){
            throw new VideoException();
        }
        Video newVid = new Video(name, file.getBytes());
      return  repo.save(newVid).getName();
    }







    //===========================================================================///
    @Override
    public UploadVideoResponse uploadVideo(MultipartFile file) {
        String videoUrl = fileUploadService.uploadFile(file);
        var video = new Video();
        video.setName(videoUrl);

        var savedVideo = repo.save(video);
        return new UploadVideoResponse(savedVideo.getId(), savedVideo.getName());
    }

    @Override
    public Video getVideoDetails(Long videoId) {

        return repo.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by id - " + videoId));
    }


}