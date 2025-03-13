package com.alibou.security.lecture;

import com.alibou.security.common.ResultDto;
import com.alibou.security.lecture.dto.LectureDto;
import com.alibou.security.lecture.dto.LectureRequest;
import com.alibou.security.resource.Resource;
import com.alibou.security.resource.ResourceRepository;
import com.alibou.security.utils.ConvertUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;
    private final ResourceRepository resourceRepository;

    @GetMapping("/{id}")
    public ResponseEntity<LectureDto> getLectureById(@PathVariable Integer id) {
        LectureDto lecture = lectureService.getLectureById(id);
        return ResponseEntity.ok(lecture);
    }

    @GetMapping
    public ResponseEntity<ResultDto<LectureDto>> getAllLectures() {
        ResultDto<LectureDto>  result = lectureService.getAllLectures();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LectureDto> createLecture(@RequestBody LectureRequest lectureRequest) {
        LectureDto createdLecture = lectureService.createLecture(lectureRequest);
        return ResponseEntity.ok(createdLecture);
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