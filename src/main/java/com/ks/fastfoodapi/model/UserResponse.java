package com.ks.fastfoodapi.model;

import com.ks.fastfoodapi.enums.Role;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private Role role;
}
