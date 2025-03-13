package com.alibou.security.author.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseAuthorDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}