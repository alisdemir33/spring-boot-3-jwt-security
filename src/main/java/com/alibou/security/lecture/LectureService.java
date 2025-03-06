package com.alibou.security.lecture;

import com.alibou.security.lecture.dto.LectureRequest;
import com.alibou.security.resource.Resource;
import com.alibou.security.resource.ResourceRepository;
import com.alibou.security.section.util.SectionRepository;
import com.alibou.security.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final SectionRepository sectionRepository;
    private final ResourceRepository resourceRepository;
    private final SessionService sessionService;

    public Lecture createLecture(LectureRequest lectureRequest) {

        Lecture lecture = Lecture.builder()
                .name(lectureRequest.getName())
                .description(lectureRequest.getDescription())
                .section(sectionRepository.findById(lectureRequest.getSectionId()).orElseThrow(() -> new RuntimeException("Section not found")))
                .build();
        String currentUser = sessionService.getCurrentUser();
        lecture.setCreatedBy(currentUser);
        return lectureRepository.save(lecture);
    }

    public Lecture getLectureById(Integer id) {
        return lectureRepository.findById(id).orElseThrow(() -> new RuntimeException("Lecture not found"));
    }

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public Lecture updateLecture(Integer id, LectureRequest lectureRequest) {
        Lecture lecture = getLectureById(id);
        lecture.setName(lectureRequest.getName());
        lecture.setDescription(lectureRequest.getDescription());
        return lectureRepository.save(lecture);
    }

    public void deleteLecture(Integer id) {
        lectureRepository.deleteById(id);
    }
    public Lecture associateResource(Integer lectureId, Resource resource) {
    Lecture lecture = getLectureById(lectureId);
    lecture.setResource(resource);
    return lectureRepository.save(lecture);
}
}