package com.alibou.security.author.dto;

import com.alibou.security.common.SearchFormDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorSearchFormDto extends SearchFormDto  {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
}