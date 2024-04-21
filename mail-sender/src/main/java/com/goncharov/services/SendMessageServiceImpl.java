package com.goncharov.services;

import com.goncharov.grpc.Email;
import com.goncharov.grpc.SendMessageServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

@GrpcService
public class SendMessageServiceImpl extends SendMessageServiceGrpc.SendMessageServiceImplBase {
    private final JavaMailSender javaMailSender;

    @Autowired
    public SendMessageServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Async
    public void sendMessage(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public SimpleMailMessage createMessage(String email, String token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("danyagoncharov99@yandex.ru");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8080/confirmation?token=" + token);
        System.out.println(mailMessage.toString());
        return mailMessage;
    }

    @Override
    public void sendMessage(Email.EmailRequest request, StreamObserver<Email.EmailResponse> responseObserver) {
        try {
            sendMessage(createMessage(request.getEmail(), request.getToken()));
            var response = Email.EmailResponse.newBuilder()
                    .setMessage("Sent")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (MailException e){
            e.printStackTrace();
            var response = Email.EmailResponse.newBuilder()
                    .setMessage(e.getMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }


    }
}
