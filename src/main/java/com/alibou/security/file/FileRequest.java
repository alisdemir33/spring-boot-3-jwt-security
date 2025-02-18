package com.alibou.security.file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FileRequest {

    private String name;
    private String url;
    private Integer size;
    private String type;
    private int lectureId;

}
