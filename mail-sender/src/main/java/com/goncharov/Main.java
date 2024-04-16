package com.goncharov;

import com.goncharov.services.SendMessageServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(Main.class, args);
        Server server = ServerBuilder.forPort(8081)
                .addService(new SendMessageServiceImpl(new JavaMailSenderImpl()))
                .build();
        server.start();
        server.awaitTermination();
    }
}