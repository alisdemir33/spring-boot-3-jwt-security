package com.alibou.security.video;

import com.alibou.security.lecture.Lecture;
import com.alibou.security.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final SessionService sessionService;
    public Video createVideo(VideoRequest videoRequest) {
        Video video = Video.builder()
                .name(videoRequest.getName())
                .description(videoRequest.getDescription())
                .length(videoRequest.getLength())
                .size(videoRequest.getSize()) // Set size
                .lecture(Lecture.builder().id(videoRequest.getLectureId()).build())
                .build();
        String currentUser = sessionService.getCurrentUser();
        video.setCreatedBy(currentUser);
        return videoRepository.save(video);
    }

    public Video getVideoById(Integer id) {
        return videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Video updateVideo(Integer id, VideoRequest videoRequest) {
        Video video = Video.builder()
                .name(videoRequest.getName())
                .description(videoRequest.getDescription())
                .length(videoRequest.getLength())
                .size(videoRequest.getSize()) // Set size
                .lecture(Lecture.builder().id(videoRequest.getLectureId()).build())
                .build();
        String currentUser = sessionService.getCurrentUser();
        video.setCreatedBy(currentUser);
        return videoRepository.save(video);
    }

    public void deleteVideo(Integer id) {
        videoRepository.deleteById(id);
    }
}