package com.goncharov.securityapi.controllers;

import com.goncharov.securityapi.security.dto.AuthenticationRequest;
import com.goncharov.securityapi.security.dto.AuthenticationResponse;
import com.goncharov.securityapi.security.dto.RegisterRequest;
import com.goncharov.securityapi.services.AuthenticationService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid
            @RequestBody RegisterRequest request) {
        AuthenticationResponse response = service.register(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid
            @RequestBody AuthenticationRequest request
    ) {

        AuthenticationResponse response = service.authenticate(request);
        return ResponseEntity.ok(response);
    }
        public String auth(){
            return "hello";
//            ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
//                    .usePlaintext().build();
//            GreetingServiceGrpc.GreetingServiceBlockingStub stub =
//                    GreetingServiceGrpc.newBlockingStub(channel);
//            Greeting.HelloRequest request = Greeting.HelloRequest
//                    .newBuilder().setName("Danya").build();
//            var response = stub.greeting(request);
//            System.out.println(response);
//            channel.shutdownNow();
        }
}
