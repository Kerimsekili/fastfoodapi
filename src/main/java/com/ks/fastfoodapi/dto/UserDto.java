package com.ks.fastfoodapi.dto;

import com.ks.fastfoodapi.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private Role role;
}
