package com.nirengi.kapnews.schedule;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nirengi.kapnews.dto.DisclosureDto;
import com.nirengi.kapnews.services.DisclosureService;
import com.nirengi.kapnews.services.DisclosureServiceImpl;
import com.nirengi.kapnews.services.EmailService;
import com.nirengi.kapnews.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private  boolean firstTask = true;
    @Autowired
    DisclosureService disclosureService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Scheduled(initialDelay = 900000, fixedRate = 900000)
    public void takeDisclosures( ) {
        List<DisclosureDto>  newDisclosures = new ArrayList<>();
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://www.kap.org.tr/tr/api/disclosures"))
                    .version(HttpClient.Version.HTTP_2)
                    .GET()
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONArray array = new JSONArray(response.body());
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                object = new JSONObject(object.getString("basic"));

                DisclosureDto disclosureDto = DisclosureDto.
                        builder().
                        disclosureId(object.getString("disclosureId")).
                        stockCode(object.getString("stockCodes")).
                        title(object.getString("title")).
                        summary(object.has("summary") ?  object.getString("summary") : "").
                        publishDate(new Date()).
                        build();

                ResponseEntity responseEntity = disclosureService.saveDisclosure(disclosureDto);
                if(responseEntity.getBody() != null && !firstTask){
                    newDisclosures.add((DisclosureDto) responseEntity.getBody());
                }
            }

        }
        catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

        if(newDisclosures.size() > 0 ){
            List<String> userEmailList = userService.getAllUsersEmails();
            String context = newDisclosures.toString();
            emailService.sendEmail(context,userEmailList);
            log.info("Emails sended !");
        }
        log.info("Take Disclosure is done !");

    }


    @Scheduled(fixedRate = 26400000)
    public void flushDailyDisclosures(){
        DisclosureServiceImpl.dailyDisclosures.clear();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getFirstDisclosures() {
        takeDisclosures();
        firstTask = false;
    }
}