package com.alibou.security.lecture;

import com.alibou.security.common.ResultDto;
import com.alibou.security.lecture.dto.LectureDto;
import com.alibou.security.lecture.dto.LectureRequest;
import com.alibou.security.lecture.util.LectureMapper;
import com.alibou.security.lecture.util.LectureRepository;
import com.alibou.security.resource.Resource;
import com.alibou.security.resource.ResourceRepository;
import com.alibou.security.section.util.SectionMapper;
import com.alibou.security.section.util.SectionRepository;
import com.alibou.security.session.SessionService;
import com.alibou.security.utils.ConvertUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final SectionRepository sectionRepository;
    private final ResourceRepository resourceRepository;
    private final SessionService sessionService;
    private final LectureMapper lectureMapper;

    public LectureDto createLecture(LectureRequest lectureRequest) {

        Lecture lecture = Lecture.builder()
                .name(lectureRequest.getName())
                .description(lectureRequest.getDescription())
                .section(sectionRepository.findById(lectureRequest.getSectionId()).orElseThrow(() -> new RuntimeException("Section not found")))
                .build();
        String currentUser = sessionService.getCurrentUser();
        lecture.setCreatedBy(currentUser);
        Lecture savedLecture= lectureRepository.save(lecture);
        return lectureMapper.toLectureDto(savedLecture);
    }

    public LectureDto getLectureById(Integer id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new RuntimeException("Lecture not found"));
        return lectureMapper.toLectureDto(lecture);
    }

    public ResultDto<LectureDto> getAllLectures() {
        List<Lecture> list = lectureRepository.findAll();
        List<LectureDto>   dtoList=  list.stream().map(lectureMapper::toLectureDto).collect(Collectors.toList());
        return  ConvertUtils.listToResponseDtoFunction.apply(dtoList);
    }

    public LectureDto updateLecture(Integer id, LectureRequest lectureRequest) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new RuntimeException("Lecture not found"));
        lecture.setName(lectureRequest.getName());
        lecture.setDescription(lectureRequest.getDescription());
        lecture.setSection(sectionRepository.findById(lectureRequest.getSectionId()).orElseThrow(() -> new RuntimeException("Section not found")));
        lecture.setLastModifiedBy(sessionService.getCurrentUser());
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