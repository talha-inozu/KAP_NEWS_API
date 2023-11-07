package com.nirengi.kapnews.schedule.service;

import org.springframework.http.ResponseEntity;

public interface ScheduleService {

    public ResponseEntity<String> startDisclosureThread();

    public ResponseEntity<String> stopDisclosureThread();

    public ResponseEntity<String> changeThreadWorkingRate(Long millisecond);

    public ResponseEntity<String> rewireNewThread();

}
