package com.ks.fastfoodapi.initializer;

import com.ks.fastfoodapi.model.User;
import com.ks.fastfoodapi.repository.UserRepository;
import com.ks.fastfoodapi.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        createUser("kerim", "pass1", Role.CUSTOMER);
        createUser("erdem", "pass2", Role.RESTAURANT_MANAGER);
        createUser("halil", "pass3", Role.GENERAL_MANAGER);
        createUser("ata", "pass4", Role.CUSTOMER);
        createUser("nesrin", "pass5", Role.RESTAURANT_MANAGER);
        createUser("muhammed", "pass6", Role.GENERAL_MANAGER);
        createUser("zeynep", "pass7", Role.CUSTOMER);
        createUser("sezen", "pass8", Role.RESTAURANT_MANAGER);
        createUser("emre", "pass9", Role.GENERAL_MANAGER);
        createUser("kaan", "pass10", Role.CUSTOMER);
        createUser("cangul", "pass11", Role.RESTAURANT_MANAGER);
        createUser("meryem", "pass12", Role.GENERAL_MANAGER);
    }

    private void createUser(String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
    }
}
