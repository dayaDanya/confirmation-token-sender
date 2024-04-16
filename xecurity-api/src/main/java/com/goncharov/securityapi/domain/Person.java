package com.goncharov.securityapi.domain;

import com.goncharov.securityapi.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {

    /**
     * id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * электронная почта
     */
    @Column(name = "email", nullable = false)
    private String email;
    /**
     * пароль
     */
    @Column(name = "password", nullable = false)
    private String password;
    /**
     * Роль
     */
    @Column(name = "role")
    private Role role;
    /**
     * Активирован
     */
    @Column(name = "is_enabled")
    private boolean isEnabled;


}
