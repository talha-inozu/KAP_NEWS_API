package com.nirengi.kapnews.schedule;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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
    private  boolean firstTask = false;
    @Autowired
    DisclosureService disclosureService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Scheduled(initialDelay = 120000, fixedRate = 120000)
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
                        disclosureId(object.getString("disclosureIndex")).
                        stockCode(object.getString("stockCodes")).
                        title(object.getString("title")).
                        summary(object.has("summary") ?  object.getString("summary") : "").
                        publishDate(new Date().toString()).
                        build();
                ResponseEntity responseEntity = disclosureService.saveDisclosure(disclosureDto);
                if(responseEntity.getBody() != null && !firstTask ){
                    DisclosureDto newDisclosureDto = (DisclosureDto) responseEntity.getBody();
                    if(!newDisclosureDto.getStockCode().equals(""))
                        newDisclosures.add(newDisclosureDto);
                }
            }

        }
        catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

        if(newDisclosures.size() > 0 ){
            boolean properDisclosureExist = false;
            Pattern patternTitle = Pattern.compile("\\byeni\\b|\\bihale\\b|\\bortak\\b|\\byatırım\\b|\\sipariş\\b");
            Pattern patternSummary = Pattern.compile("\\byeni\\b|\\bihale\\b|\\bortak\\b|\\sipariş\\b");
            Field[] fields = DisclosureDto.class.getDeclaredFields();
            List<String> userEmailList = userService.getAllUsersEmails();
            String context = "";
            for(DisclosureDto newDisclosure: newDisclosures){
                String disclosureString = "";

                if(patternTitle.matcher(newDisclosure.getTitle().toLowerCase()).find() || patternSummary.matcher(newDisclosure.getSummary().toLowerCase()).find()){
                    properDisclosureExist = true;
                    for (int i = 0; i < fields.length; i++) {
                        Field field = fields[i];
                        field.setAccessible(true); // Allow access to private fields
                        if (!field.getName().equals("id")) {
                            try {
                                disclosureString = disclosureString + field.getName() + " : " + (String)field.get(newDisclosure) + "\n";
                            } catch (Exception e) {
                            }
                        }
                    }
                    disclosureString = disclosureString +"Disclosure Link : "+ "https://www.kap.org.tr/tr/Bildirim/" + newDisclosure.getDisclosureId();
                    context = context  + "\n\n"  + disclosureString;
                }

            }

            if(properDisclosureExist){
                emailService.sendEmail(context, userEmailList);
                log.info("Emails sended !");
            }
        }
        log.info("Take Disclosure is done !");

    }


    @Scheduled(fixedRate = 86400000)
    public void flushDailyDisclosures(){
        DisclosureServiceImpl.dailyDisclosures.clear();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getFirstDisclosures() {
        takeDisclosures();
        firstTask = false;
    }
}