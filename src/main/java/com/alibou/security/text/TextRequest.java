package com.alibou.security.text;

import com.alibou.security.resource.ResourceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class TextRequest extends ResourceRequest {
    private String content;
}