package com.ks.fastfoodapi.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.ks.fastfoodapi.model.User;
import com.ks.fastfoodapi.repository.UserRepository;
import com.ks.fastfoodapi.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
@Order(1)
public class UserInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(UserInitializer.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(String... args) {
        logger.info("Initializing users...");
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
        logger.info("User initialization completed.");
    }

    private void createUser(String username, String password, Role role) {
        logger.debug("Creating user: {}", username);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
        logger.debug("User created: {}", user);
    }
}
