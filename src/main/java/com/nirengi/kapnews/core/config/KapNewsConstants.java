package com.nirengi.kapnews.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KapNewsConstants {

    private long scheduledTakeDisclosuresFixedRate;

    @Value("${scheduled.take.disclosures.fixedRate}")
    public void setScheduledTakeDisclosuresFixedRate(long scheduledTakeDisclosuresFixedRate) {
        this.scheduledTakeDisclosuresFixedRate = scheduledTakeDisclosuresFixedRate;
    }

    public long getScheduledTakeDisclosuresFixedRate() {
        return scheduledTakeDisclosuresFixedRate;
    }

}
