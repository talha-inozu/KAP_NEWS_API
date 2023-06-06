package com.nirengi.kapnews;

import com.nirengi.kapnews.schedule.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

import java.net.URISyntaxException;


@SpringBootApplication
@EnableScheduling
public class KapnewsApplication {
    @Autowired
    ScheduledTasks scheduledTasks;
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, JSONException {

        SpringApplication.run(KapnewsApplication.class, args);
    }


}
