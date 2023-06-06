package com.nirengi.kapnews.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    JavaMailSender emailSender;


    @Override
    public void sendEmail(String context, List<String> receiverEmails) {
        for(String receiverEmail : receiverEmails){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("kapnewsapi@gmail.com");
            message.setTo(receiverEmail);
            message.setSubject("New disclosure is exist!");
            message.setText(context);
            emailSender.send(message);
        }


    }
}
