package com.nirengi.kapnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KapnewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KapnewsApplication.class, args);
    }

}
