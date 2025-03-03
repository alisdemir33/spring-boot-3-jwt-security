
    package com.alibou.security.course;

    import com.alibou.security.author.Author;
    import com.alibou.security.section.Section;
    import com.alibou.security.section.SectionDto;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class CourseDto {
        private Integer id;
        private String title;
        private String description;
        private List<Author> authors;
        private List<SectionDto> sections;

        public CourseDto(Integer id, String title, String description) {
        }
    }

