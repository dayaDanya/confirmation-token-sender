package com.goncharov;

import com.goncharov.grpc.Greeting;
import com.goncharov.grpc.GreetingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub =
                GreetingServiceGrpc.newBlockingStub(channel);
        Greeting.HelloRequest request = Greeting.HelloRequest
                .newBuilder().setName("Danya").build();
        var response = stub.greeting(request);
        System.out.println(response);
        channel.shutdownNow();
    }
}
