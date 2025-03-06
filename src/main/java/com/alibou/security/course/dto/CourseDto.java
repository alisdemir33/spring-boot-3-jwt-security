
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
    public class CourseDto {
        private Integer id;
        private String title;
        private String description;
        private List<AuthorDto> authors;
        private List<SectionDto> sections;


    }

