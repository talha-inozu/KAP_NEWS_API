package com.nirengi.kapnews;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class KapnewsApplication {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, JSONException {
        SpringApplication.run(KapnewsApplication.class, args);
    }

}
