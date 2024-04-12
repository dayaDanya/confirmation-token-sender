package com.goncharov.securityapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "confirmation_token")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmationToken {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_at")
    private Date expiredAt;

    @OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "person_email")
    private Person person;

}
