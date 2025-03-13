package com.alibou.security.video;

import com.alibou.security.resource.ResourceRequest;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class VideoRequest extends ResourceRequest {
    private int length;
}