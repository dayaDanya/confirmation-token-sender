package com.goncharov.securityapi.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean
    ManagedChannel managedChannel(){
        return ManagedChannelBuilder.forTarget("localhost:8081")
                .usePlaintext().build();
    }
}
