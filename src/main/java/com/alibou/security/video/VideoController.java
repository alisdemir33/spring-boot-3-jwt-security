package com.alibou.security.video;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public ResponseEntity<Video> createVideo(@RequestBody VideoRequest videoRequest) {
        Video createdVideo = videoService.createVideo(videoRequest);
        return ResponseEntity.ok(createdVideo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable Integer id) {
        Video video = videoService.getVideoById(id);
        return ResponseEntity.ok(video);
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoService.getAllVideos();
        return ResponseEntity.ok(videos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable Integer id, @RequestBody VideoRequest videoRequest) {
        Video updatedVideo = videoService.updateVideo(id, videoRequest);
        return ResponseEntity.ok(updatedVideo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Integer id) {
        videoService.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }
}