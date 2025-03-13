package com.alibou.security.common;

import com.alibou.security.utils.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {
    private int pageSize;
    private int pageNumber;
    private int totalCount;
    private List<OrderBy> orderByColumnNames;
}
