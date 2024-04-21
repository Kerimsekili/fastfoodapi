package com.ks.fastfoodapi.repository;

import com.ks.fastfoodapi.model.User;
import com.ks.fastfoodapi.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRole(Role role);
}
