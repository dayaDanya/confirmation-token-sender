package com.goncharov.securityapi.services;

import com.goncharov.grpc.Email;
import com.goncharov.securityapi.domain.ConfirmationToken;
import com.goncharov.securityapi.domain.Person;
import com.goncharov.securityapi.domain.enums.Role;
import com.goncharov.securityapi.exceptions.EmailNotUniqueException;
import com.goncharov.securityapi.exceptions.PersonNotFoundException;
import com.goncharov.securityapi.repos.PersonRepo;
import com.goncharov.securityapi.security.auth.PersonDetails;
import com.goncharov.securityapi.security.dto.AuthenticationRequest;
import com.goncharov.securityapi.security.dto.AuthenticationResponse;
import com.goncharov.securityapi.security.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PersonRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final ConfirmationTokenService confirmationTokenService;

    public AuthenticationResponse confirmRegistration(String confirmationToken) {
        Person person = confirmationTokenService.findPersonByToken(confirmationToken);
        person.setRole(Role.USER);
        person.setEnabled(true);
        var userDetails =
                PersonDetails.builder()
                        .person(person)
                        .build();
        var jwtToken = jwtService.generateToken(userDetails);
        return new AuthenticationResponse(jwtToken);
    }

    public Email.EmailRequest register(RegisterRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailNotUniqueException(request.getEmail());
        }
        var person = Person.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .isEnabled(false)
                .build();
        repository.save(person);
        var confirmationToken = confirmationTokenService.generateToken(person);
        return Email.EmailRequest.newBuilder()
                .setEmail(person.getEmail())
                .setToken(confirmationToken.getToken())
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new PersonNotFoundException(request.getEmail()));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(PersonDetails.builder()
                .person(user)
                .build());
        return AuthenticationResponse.builder()
                .response(jwtToken)
                .build();
    }

}
