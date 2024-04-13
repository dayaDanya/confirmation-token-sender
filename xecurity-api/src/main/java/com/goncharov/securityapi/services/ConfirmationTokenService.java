package com.goncharov.securityapi.services;

import com.goncharov.securityapi.domain.ConfirmationToken;
import com.goncharov.securityapi.domain.Person;
import com.goncharov.securityapi.repos.ConfirmationTokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepo confirmationTokenRepo;

    public Person findPersonByToken(String token){
        //todo add ex
        return confirmationTokenRepo.findPersonByToken(token).orElseThrow(() ->
                new RuntimeException("Confirmation token isn't actual"));
    }

    public ConfirmationToken generateToken(Person person) {
        return ConfirmationToken.builder()
                .token(UUID.randomUUID().toString())
                .expiredAt(getExpiryDate())
                .person(person)
                .build();
    }
    public Date getExpiryDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }
}
