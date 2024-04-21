package com.goncharov.securityapi.controllers;

import com.goncharov.grpc.SendMessageServiceGrpc;
import com.goncharov.securityapi.security.dto.AuthenticationRequest;
import com.goncharov.securityapi.security.dto.AuthenticationResponse;
import com.goncharov.securityapi.security.dto.RegisterRequest;
import com.goncharov.securityapi.services.AuthenticationService;
import io.grpc.ManagedChannel;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final AuthenticationService service;

    private final ManagedChannel managedChannel;

    private SendMessageServiceGrpc.SendMessageServiceBlockingStub stub;


    @PostConstruct
    private void initializeStub() {
        stub = SendMessageServiceGrpc.newBlockingStub(managedChannel);
    }
    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid
            @RequestBody RegisterRequest request) {
        try {
            var emailRequest = service.register(request);

            //mesServ.sendMessage(mesServ.createMessage(emailRequest.getEmail(), emailRequest.getToken()));
            var response = stub.sendMessage(emailRequest);
            return ResponseEntity.ok(new AuthenticationResponse(response.getMessage()));//response.getMessage()));
        }catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new AuthenticationResponse(e.getMessage()));
        }
    }
    @GetMapping("/confirmation")
    public ResponseEntity<AuthenticationResponse> confirmReg(
            @RequestParam("token") String confirmationToken){
        try {
            service.confirmRegistration(confirmationToken);
            return ResponseEntity.ok(new AuthenticationResponse("activated"));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest()
                    .body(new AuthenticationResponse(e.getMessage()));
        }
    }

    @PostMapping("/auth")
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
