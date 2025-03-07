package com.alibou.security.lecture;

import com.alibou.security.lecture.dto.LectureDto;
import com.alibou.security.lecture.dto.LectureRequest;
import com.alibou.security.resource.Resource;
import com.alibou.security.resource.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;
    private final ResourceRepository resourceRepository;

    @PostMapping
    public ResponseEntity<Lecture> createLecture(@RequestBody LectureRequest lectureRequest) {
        Lecture createdLecture = lectureService.createLecture(lectureRequest);
        return ResponseEntity.ok(createdLecture);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LectureDto> getLectureById(@PathVariable Integer id) {
        LectureDto lecture = lectureService.getLectureById(id);
        return ResponseEntity.ok(lecture);
    }

    @GetMapping
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> lectures = lectureService.getAllLectures();
        return ResponseEntity.ok(lectures);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LectureDto> updateLecture(@PathVariable Integer id, @RequestBody LectureRequest lectureRequest) {
        LectureDto updatedLecture = lectureService.updateLecture(id, lectureRequest);
        return ResponseEntity.ok(updatedLecture);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecture(@PathVariable Integer id) {
        lectureService.deleteLecture(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{lectureId}/resources")
    public ResponseEntity<LectureDto> associateResource(@PathVariable Integer lectureId, @RequestBody Resource resource) {
        Resource savedResource = resourceRepository.save(resource);
        LectureDto updatedLecture = lectureService.associateResource(lectureId, savedResource);
        return ResponseEntity.ok(updatedLecture);
    }
}