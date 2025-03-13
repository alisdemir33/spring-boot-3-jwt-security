
    package com.alibou.security.course.dto;

    import com.alibou.security.author.dto.AuthorDto;
    import com.alibou.security.author.dto.BaseAuthorDto;
    import com.alibou.security.section.dto.BaseSectionDto;
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

        private List<BaseAuthorDto> authors;
        private List<BaseSectionDto> sections;

        public CourseDto(Integer id, String title, String description, Object o, Object o1) {
        }
    }

