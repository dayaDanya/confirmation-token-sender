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
    private long id;
    /**
     * имя пользователя
     */
    private String username;
    /**
     * электронная почта
     */
    private String email;
    /**
     * пароль
     */
    private String password;
    /**
     * Роль
     */
    private Role role;


}
