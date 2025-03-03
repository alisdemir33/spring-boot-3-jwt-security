
package com.alibou.security.utils;

import com.alibou.security.common.ResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.function.Function;

public final class ConvertUtils {

    final static public Function<Page, ResultDto> pageToResponseDtoFunction = paging -> {
        ResultDto resultDto = new ResultDto<>();
        resultDto.setCount(paging.getTotalElements());
        resultDto.setResultList(paging.getContent());
        return resultDto;
    };

    final static public Function<Slice, ResultDto> sliceToResponseDtoFunction = paging -> {
        ResultDto resultDto = new ResultDto<>();
        if (paging instanceof Page<?> page) {
            resultDto.setCount(page.getTotalElements());
        }
        resultDto.setResultList(paging.getContent());
        return resultDto;
    };

    final static public Function<List, ResultDto> listToResponseDtoFunction = list -> {
        ResultDto resultDto = new ResultDto<>();
        resultDto.setResultList(list);
        return resultDto;
    };
}
