package com.alibou.security.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFormDto {
    PageInfo pageInfo;
    private Integer maxPageNumberForExcel;
}
