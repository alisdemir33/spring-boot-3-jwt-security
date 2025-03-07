package com.alibou.security.lecture;

import com.alibou.security.lecture.dto.LectureDto;
import com.alibou.security.lecture.dto.LectureRequest;
import com.alibou.security.lecture.util.LectureMapper;
import com.alibou.security.lecture.util.LectureRepository;
import com.alibou.security.resource.Resource;
import com.alibou.security.resource.ResourceRepository;
import com.alibou.security.section.util.SectionMapper;
import com.alibou.security.section.util.SectionRepository;
import com.alibou.security.session.SessionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final SectionRepository sectionRepository;
    private final ResourceRepository resourceRepository;
    private final SessionService sessionService;
    private final LectureMapper lectureMapper;


//    @Autowired
//    public LectureService(LectureMapper lectureMapper) {
//        this.lectureMapper = lectureMapper;
//    }

    //private final LectureMapper lectureMapper = LectureMapper.INSTANCE;

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

    public LectureDto getLectureById(Integer id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new RuntimeException("Lecture not found"));
        return lectureMapper.toLectureDto(lecture);
    }


    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public LectureDto updateLecture(Integer id, LectureRequest lectureRequest) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new RuntimeException("Lecture not found"));
        lecture.setName(lectureRequest.getName());
        lecture.setDescription(lectureRequest.getDescription());
        return lectureMapper.toLectureDto(lectureRepository.save(lecture));

    }

    public void deleteLecture(Integer id) {
        lectureRepository.deleteById(id);
    }

    public LectureDto associateResource(Integer lectureId, Resource resource) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new RuntimeException("Lecture not found"));
        lecture.setResource(resource);
        return lectureMapper.toLectureDto(lectureRepository.save(lecture));
    }
}