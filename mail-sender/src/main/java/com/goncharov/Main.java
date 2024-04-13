package com.goncharov;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(8080)
                //.addService(new GreetingServiceImpl())
                .build();
        server.start();
        server.awaitTermination();
    }
}