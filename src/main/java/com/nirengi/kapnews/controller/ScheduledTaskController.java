package com.nirengi.kapnews.controller;

import com.nirengi.kapnews.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduledTaskController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/start-disclosure-thread")
    public ResponseEntity<String> startDisclosureThread(){
         return scheduleService.startDisclosureThread();
    }
    @GetMapping("/stop-disclosure-thread")
    public ResponseEntity<String> stopDisclosureThread(){
        return scheduleService.stopDisclosureThread();
    }
    @PutMapping("/change-rate/{millisecond}")
    public ResponseEntity<String> changeThreadWorkingRate(@PathVariable Long millisecond){
        return scheduleService.changeThreadWorkingRate(millisecond);
    }
}
