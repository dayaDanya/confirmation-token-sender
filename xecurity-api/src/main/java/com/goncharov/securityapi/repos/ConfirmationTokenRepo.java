package com.goncharov.securityapi.repos;

import com.goncharov.securityapi.domain.ConfirmationToken;
import com.goncharov.securityapi.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, Long> {
    Optional<Person> findPersonByToken(String token);
}
