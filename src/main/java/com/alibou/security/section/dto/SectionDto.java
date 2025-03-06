package com.alibou.security.section.dto;
    import com.alibou.security.course.dto.CourseDto;
    import com.alibou.security.lecture.Lecture;
    import com.alibou.security.lecture.dto.LectureDto;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.experimental.SuperBuilder;

    import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    public class SectionDto {

       private Integer id;
        private String name;
        private String description;
        private int sectionOrder;
        private CourseDto course;
        private List<LectureDto> lectures;
    }

