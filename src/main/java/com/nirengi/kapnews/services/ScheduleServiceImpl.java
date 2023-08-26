package com.nirengi.kapnews.services;

import com.nirengi.kapnews.KapNewsConstants;
import com.nirengi.kapnews.schedule.ScheduledTasks;
import com.nirengi.kapnews.thread.TakeDisclosureThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private static final Logger log = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    @Autowired
    TakeDisclosureThread takeDisclosureThread;
    @Autowired
    KapNewsConstants kapNewsConstants;
    @Override
    public ResponseEntity<String> startDisclosureThread() {
        String response ;
        try{
            if(!takeDisclosureThread.isAlive()) {
                takeDisclosureThread.start();
                log.info("Thread started");
                response = "Thread started";
            }else{
                takeDisclosureThread.resumeThread();
                log.info("Thread resumed");
                response = "Thread resumed";
            }
        }catch (Exception e){
            log.info("Error at ScheduleServiceImpl : " + e.getMessage());
            response = "Error at ScheduleServiceImpl : " + e.getMessage();
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<String> stopDisclosureThread() {
        String response ;
        try{
            if(takeDisclosureThread.isAlive()) {
                takeDisclosureThread.stopThread();
                log.info("Thread stopped" );
                response = "Thread stopped";
            }else{
                log.info("Error at ScheduleServiceImpl : thread is not alive" );
                response = "Thread is not alive";
            }
        }catch (Exception e){
            log.info("Error at ScheduleServiceImpl : " + e.getMessage());
            response = "Error at ScheduleServiceImpl : " + e.getMessage();
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<String> changeThreadWorkingRate(Long millisecond) {
        String response;
        try{
            if(!(millisecond < 10000)) {
                kapNewsConstants.setScheduledTakeDisclosuresFixedRate(millisecond);
                response = "Rate is changed";
            }else{
                response = "Rate is too small! It must be equal or bigger than 10000";
            }
        }catch (Exception e){
            log.info("Error at ScheduleServiceImpl/changeThreadWorkingRate : " + e.getMessage());
            response = "Error at ScheduleServiceImpl/changeThreadWorkingRate : " + e.getMessage();
        }
        return  ResponseEntity.ok(response);
    }
}
