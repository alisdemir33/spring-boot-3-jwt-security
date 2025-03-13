package com.alibou.security.utils;

import com.alibou.security.common.SearchFormDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class QueryUtils {
    public static Pageable getPageable(SearchFormDto searchFormDto) {
        int maxPageSize = searchFormDto.getPageInfo().getPageSize();
        if (Objects.nonNull(searchFormDto.getMaxPageNumberForExcel())) {
            maxPageSize = (searchFormDto.getMaxPageNumberForExcel() -
                    (searchFormDto.getPageInfo().getPageNumber() - 1)) * searchFormDto.getPageInfo().getPageSize();
        }
        return PageRequest.of(searchFormDto.getPageInfo().getPageNumber() - 1, maxPageSize);
    }

    public static Pageable getPageable(SearchFormDto searchFormDto, String defaultOrderColumn) {
        int maxPageSize = searchFormDto.getPageInfo().getPageSize();
        if (Objects.nonNull(searchFormDto.getMaxPageNumberForExcel())) {
            maxPageSize = (searchFormDto.getMaxPageNumberForExcel() -
                    (searchFormDto.getPageInfo().getPageNumber() - 1)) * searchFormDto.getPageInfo().getPageSize();
        }
        Sort sort = getSort(searchFormDto.getPageInfo().getOrderByColumnNames(), defaultOrderColumn, true);
        return PageRequest.of(searchFormDto.getPageInfo().getPageNumber() - 1, maxPageSize, sort);
    }

    private static Sort getSort(List<OrderBy> orderByColumnNames, String defaultOrderColumn, boolean safe) {
        AtomicBoolean isAccending = new AtomicBoolean(false);
        List<String> orderByColumns = orderByColumnNames.stream().map(orderBy -> {
            isAccending.set(orderBy.isAscending());
            return orderBy.getColumnName();
        }).toList();
        if(safe){
            return !orderByColumns.isEmpty() ?
                    JpaSort.by(isAccending.get() ? Sort.Direction.ASC : Sort.Direction.DESC, orderByColumns.toArray(String[]::new)) :
                    JpaSort.by(Sort.Direction.DESC, defaultOrderColumn);
        }else{
            return !orderByColumns.isEmpty() ?
                    JpaSort.unsafe(isAccending.get() ? Sort.Direction.ASC : Sort.Direction.DESC, orderByColumns.toArray(String[]::new)) :
                    JpaSort.unsafe(Sort.Direction.DESC, defaultOrderColumn);
        }
    }
}
