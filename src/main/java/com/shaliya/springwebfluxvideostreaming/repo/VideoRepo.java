package com.shaliya.springwebfluxvideostreaming.repo;

import com.shaliya.springwebfluxvideostreaming.dto.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepo extends JpaRepository<Video, Long> {

    Video findByName(String name);

    boolean existsByName(String name);

    @Query(nativeQuery = true, value="SELECT name FROM video")
    List<String> getAllEntryNames();

}