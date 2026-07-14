package com.example.notification.service.impl;

import com.example.grpc.generated.GetUserByAnalystIdRequest;
import com.example.grpc.generated.GetUserResponse;
import com.example.grpc.generated.GetUsersResponse;
import com.example.grpc.generated.UserImageServiceGrpc;
import com.example.notification.dto.IncidentDto;
import com.example.notification.enums.UserRole;
import com.example.notification.mapper.UserMapper;
import com.example.notification.service.EmailService;
import com.google.protobuf.Empty;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;
    private final UserImageServiceGrpc.UserImageServiceBlockingStub blockingStub;
    private final UserMapper userMapper;

    public EmailServiceImpl(@Autowired JavaMailSender javaMailSender, @GrpcClient("user-image-service") UserImageServiceGrpc.UserImageServiceBlockingStub blockingStub, @Autowired UserMapper userMapper) {
        this.emailSender = javaMailSender;
        this.blockingStub = blockingStub;
        this.userMapper = userMapper;
    }

    @Override
    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // Второй параметр true указывает на HTML-контент

            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @KafkaListener(topics = "incident-queue", groupId = "incident-service")
    public void handleOrderPlaced(IncidentDto incidentDto) {
        log.info("Получено событие о новом заказе от пользователя {}: {}", incidentDto.getId(), incidentDto);
        var users = userMapper.fromProtoList(getUsers().getUserList());

        for (var user : users.stream().filter(t -> t.getRole() == UserRole.ADMIN).toList()) {
            sendSimpleEmail(user.getEmail(), Long.toString(incidentDto.getId()) + incidentDto.getDateCreate() + incidentDto.getDescription(), incidentDto.toString());
        }
    }

    private GetUsersResponse getUsers() {
        return blockingStub.getAllUsers(Empty.newBuilder().build());
    }
}
