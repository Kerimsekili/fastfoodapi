package com.ks.fastfoodapi.service.user;

import com.ks.fastfoodapi.dto.UserDto;
import com.ks.fastfoodapi.enums.Role;
import com.ks.fastfoodapi.model.User;
import com.ks.fastfoodapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        // Map user to UserDto
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;
    }

    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public List<String> getAllManagers() {
        List<User> managers = userRepository.findAllByRole(Role.RESTAURANT_MANAGER);
        return managers.stream().map(User::getUsername).collect(Collectors.toList());
    }

}

