package com.alibou.security.section.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseSectionDto {
    private Integer id;
    private String name;
    private String description;
    private int sectionOrder;
}