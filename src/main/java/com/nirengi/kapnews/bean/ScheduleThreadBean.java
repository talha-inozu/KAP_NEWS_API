package com.nirengi.kapnews.bean;


import com.nirengi.kapnews.thread.TakeDisclosureThread;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class ScheduleThreadBean {
    @Bean
    public TakeDisclosureThread getTakeDisclosureThread() {
        TakeDisclosureThread takeDisclosureThread = new TakeDisclosureThread();
        return takeDisclosureThread;
    }
}
