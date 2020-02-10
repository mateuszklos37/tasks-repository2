package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    SimpleEmailService simpleEmailService;

    @Mock
    JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail(){
        //Given
        Mail mail = new Mail("mateuszklos37@gmail.com", "Test", "Test message");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mail.getMailTo());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getMessage());
        //When
        simpleEmailService.send(mail);
        //Then
        verify(javaMailSender, times(1)).send(simpleMailMessage);
    }

    @Test
    public void shouldSendEmailWithCc(){
        //Given
        Mail mail = new Mail("mateuszklos37@gmail.com", "Test", "Test message", "test@test.com");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mail.getMailTo());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getMessage());
        simpleMailMessage.setCc((mail.getToCc()));
        //When
        simpleEmailService.send(mail);
        //Then
        verify(javaMailSender, times(1)).send(simpleMailMessage);
    }
}