package com.alibou.security.file;

import com.alibou.security.resource.ResourceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class FileRequest extends ResourceRequest {
    private String type;
}
