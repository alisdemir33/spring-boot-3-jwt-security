package com.alibou.security.resource;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceRequest {
    private String name;
    private String url;
    private Integer size;
    private String description;
    private int lectureId;
}