package com.ks.fastfoodapi.model;

import com.ks.fastfoodapi.security.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "manager")
    private List<Restaurant> restaurants;

    private int roleValue;

    @Transient
    public Role getRole() {
        return Role.values()[roleValue];
    }

    public void setRole(Role role) {
        this.roleValue = role.getValue();
    }


}

