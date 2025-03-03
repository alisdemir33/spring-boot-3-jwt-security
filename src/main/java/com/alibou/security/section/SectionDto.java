package com.alibou.security.section;
    import com.alibou.security.lecture.Lecture;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class SectionDto {

       private Integer id;
        private String name;
        private String description;
        private int sectionOrder;
        private List<Lecture> lectures;
    }

