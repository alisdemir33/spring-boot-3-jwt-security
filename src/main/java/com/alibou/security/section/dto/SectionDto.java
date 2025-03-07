package com.alibou.security.section.dto;
    import com.alibou.security.course.dto.CourseDto;
    import com.alibou.security.lecture.Lecture;
    import com.alibou.security.lecture.dto.LectureDto;
    import lombok.*;
    import lombok.experimental.SuperBuilder;

    import java.util.List;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    public class SectionDto extends BaseSectionDto{
         CourseDto course;
         List<LectureDto> lectures;
    }

