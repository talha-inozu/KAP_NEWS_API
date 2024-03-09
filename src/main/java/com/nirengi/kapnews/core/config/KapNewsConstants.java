package com.nirengi.kapnews.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class KapNewsConstants {

    private long scheduledTakeDisclosuresFixedRate;

    @Value("${scheduled.take.disclosures.fixedRate}")
    public void setScheduledTakeDisclosuresFixedRate(long scheduledTakeDisclosuresFixedRate) {
        this.scheduledTakeDisclosuresFixedRate = scheduledTakeDisclosuresFixedRate;
    }
}
