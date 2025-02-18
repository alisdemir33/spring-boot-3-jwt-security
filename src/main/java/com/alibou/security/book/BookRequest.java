package com.alibou.security.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BookRequest {

    private Integer id;
    private String isbn;
    private String title;
    private String description;
    private List<Integer> authorIds;
}