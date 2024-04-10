package com.goncharov.securityapi.services;

import com.goncharov.securityapi.domain.Person;
import com.goncharov.securityapi.exceptions.PersonNotFoundException;
import com.goncharov.securityapi.repos.PersonRepo;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Person findByEmail(String email) {
        return personRepo.findByEmail(email).orElseThrow(() -> new PersonNotFoundException(email));
    }
}
