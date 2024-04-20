package com.ks.fastfoodapi.service.user;

import com.ks.fastfoodapi.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    UserDto create(UserDto userDTO);
    List<UserDto> getAll();
}
