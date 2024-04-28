package com.ks.fastfoodapi.service.user;

import com.ks.fastfoodapi.model.UserResponse;
import com.ks.fastfoodapi.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto login(String username, String password);
    List<UserDto> getAll();
    List<String> getAllManagers();
    List<UserResponse> getAllManagersWithUserResponse();
}
