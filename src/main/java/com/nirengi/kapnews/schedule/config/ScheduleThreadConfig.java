package com.nirengi.kapnews.schedule.config;

import com.nirengi.kapnews.concurrent.TakeDisclosureThread;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleThreadConfig {
    @Bean
    public TakeDisclosureThread getTakeDisclosureThread() {
        TakeDisclosureThread takeDisclosureThread = new TakeDisclosureThread();
        return takeDisclosureThread;
    }
}
