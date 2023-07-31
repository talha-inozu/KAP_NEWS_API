package com.nirengi.kapnews.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface ScheduleService {

    public ResponseEntity<String> startDisclosureThread();
    public ResponseEntity<String> stopDisclosureThread();
    public ResponseEntity<String> changeThreadWorkingRate(Long millisecond);

}
