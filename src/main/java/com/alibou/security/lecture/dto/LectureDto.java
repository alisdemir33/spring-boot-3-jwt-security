package com.alibou.security.lecture.dto;

    import com.alibou.security.section.dto.SectionDto;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.experimental.SuperBuilder;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    public class LectureDto {
        private Integer id;
        private String name;
        private String description;
        private SectionDto section;
    }

