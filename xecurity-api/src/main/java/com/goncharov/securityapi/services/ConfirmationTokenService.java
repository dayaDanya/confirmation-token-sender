package com.goncharov.securityapi.services;

import com.goncharov.securityapi.domain.ConfirmationToken;
import com.goncharov.securityapi.domain.Person;
import com.goncharov.securityapi.repos.ConfirmationTokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepo confirmationTokenRepo;

    public Person findPersonByToken(String token){
        //todo add ex
        return confirmationTokenRepo.findPersonByToken(token).orElseThrow();
    }

    public ConfirmationToken generateToken(String email) {
        //todo
    }
}
