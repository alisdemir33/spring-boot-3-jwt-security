
    package com.alibou.security.course.dto;

    import com.alibou.security.author.dto.AuthorDto;
    import com.alibou.security.section.SectionDto;
    import lombok.*;

    import java.util.List;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class CourseDto {
        private Integer id;
        private String title;
        private String description;
        private List<AuthorDto> authors;
        private List<SectionDto> sections;


    }

