package com.alibou.security.section.dto;

import com.alibou.security.common.SearchFormDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionSearchFormDto extends SearchFormDto {
    private String name;
    private String description;
}