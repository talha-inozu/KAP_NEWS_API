package com.nirengi.kapnews.email.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender emailSender;

    @Override
    public void sendEmail(String context, List<String> receiverEmails) {
        for (String receiverEmail : receiverEmails) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("kapnewsapi@gmail.com");
            message.setTo(receiverEmail);
            message.setSubject("New disclosure is exist!");
            message.setText(context);
            emailSender.send(message);
        }
    }

    @Override
    public void sendEmail(String context, String receiverEmail) {
        List<String> emaiList = new ArrayList<>();
        emaiList.add(receiverEmail);
        sendEmail(context, emaiList);
    }

    @Override
    public void sendErrorEmail(String context, String receiverEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kapnewsapi@gmail.com");
        message.setTo(receiverEmail);
        message.setSubject("Error Occured In KAPNEWSAPI!");
        message.setText(context);
        emailSender.send(message);
    }
}
