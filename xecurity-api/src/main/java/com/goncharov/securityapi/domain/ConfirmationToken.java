package com.goncharov.securityapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "confirmation_token")
@Getter
@Setter
@RequiredArgsConstructor
public class ConfirmationToken {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "person_id")
    private Person person;

}
