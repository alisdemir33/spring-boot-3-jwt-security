package com.alibou.security.author;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorRequest {
    private String firstName;
    private String lastName;
    private String email;
}