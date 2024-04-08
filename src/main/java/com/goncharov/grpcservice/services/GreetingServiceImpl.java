package com.goncharov.grpcservice.services;
import com.goncharov.grpc.Greeting;
import com.goncharov.grpc.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(Greeting.HelloRequest helloRequest,
                         StreamObserver<Greeting.HelloResponse> responseObserver){
        Greeting.HelloResponse response = Greeting.HelloResponse.newBuilder()
                .setGreeting("Hello " + helloRequest.getName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
