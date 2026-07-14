package com.example.notification.service.impl;

import com.example.notification.dto.IncidentDto;
import com.example.notification.enums.IncidentStatus;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> simpleMessageCaptor;

    @Test
    void sendSimpleEmail_shouldSendMessage() {
        emailService.sendSimpleEmail("test@test.com", "Subject", "Text");

        verify(emailSender).send(simpleMessageCaptor.capture());
        SimpleMailMessage message = simpleMessageCaptor.getValue();
        assertArrayEquals(new String[]{"test@test.com"}, message.getTo());
        assertEquals("Subject", message.getSubject());
        assertEquals("Text", message.getText());
    }

    @Test
    void sendHtmlEmail_shouldSendHtmlMessage() throws MessagingException {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(emailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendHtmlEmail("test@test.com", "Subject", "<h1>HTML</h1>");

        verify(emailSender).send(mimeMessage);
    }

    @Test
    void sendHtmlEmail_shouldThrowRuntimeException_onMessagingException() throws MessagingException {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(emailSender.createMimeMessage()).thenReturn(mimeMessage);

        doThrow(new RuntimeException("Failed to send email")).when(emailSender).send(any(MimeMessage.class));

        assertThrows(RuntimeException.class, () ->
                emailService.sendHtmlEmail("test@test.com", "Subject", "<h1>HTML</h1>")
        );
    }

    @Test
    void handleOrderPlaced_shouldLogEvent() {
        IncidentDto dto = IncidentDto.builder()
                .withId(1L)
                .withName("Test")
                .withStatus(IncidentStatus.OPEN)
                .build();

        assertDoesNotThrow(() -> emailService.handleOrderPlaced(dto));
    }
}