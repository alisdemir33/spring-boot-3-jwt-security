
    package com.alibou.security.course.dto;

    import com.alibou.security.author.dto.AuthorDto;
    import com.alibou.security.section.dto.SectionDto;
    import lombok.*;
    import lombok.experimental.SuperBuilder;

    import java.util.List;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    public class CourseDto extends BaseCourseDto {
        private List<AuthorDto> authors;
        private List<SectionDto> sections;

        public CourseDto(Integer id, String title, String description, Object o, Object o1) {
        }
    }

