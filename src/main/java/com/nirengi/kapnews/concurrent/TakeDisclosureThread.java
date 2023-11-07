package com.nirengi.kapnews.concurrent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.nirengi.kapnews.constant.KapNewsConstants;
import com.nirengi.kapnews.dto.DisclosureDto;
import com.nirengi.kapnews.services.DisclosureService;
import com.nirengi.kapnews.services.EmailService;
import com.nirengi.kapnews.services.UserService;
import com.nirengi.kapnews.dto.UserDto;
import com.nirengi.kapnews.exception.ThreadException;

@Component
public class TakeDisclosureThread extends Thread {

    private static final Logger log = LoggerFactory.getLogger(TakeDisclosureThread.class);

    public boolean isExecute() {
        return execute;
    }

    private boolean execute = true;
    @Autowired
    KapNewsConstants kapNewsConstants;
    @Autowired
    DisclosureService disclosureService;
    @Autowired
    EmailService emailService;
    @Autowired
    UserService userService;
    @Autowired
    AutowireCapableBeanFactory beanFactory;

    public void run() {
        try {
            log.info("TakeDisclosureThread is started !");
            while (true) {
                while (execute) {
                    List<DisclosureDto> newDisclosures = new ArrayList<>();
                    HttpClient client = HttpClient.newHttpClient();

                    try {

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
                                    summary(object.has("summary") ? object.getString("summary") : "").
                                    publishDate(new Date().toString()).
                                    build();
                            ResponseEntity responseEntity = disclosureService.saveDisclosure(disclosureDto);
                            if (responseEntity.getBody() != null) {
                                DisclosureDto newDisclosureDto = (DisclosureDto) responseEntity.getBody();
                                if (!newDisclosureDto.getStockCode().equals(""))
                                    newDisclosures.add(newDisclosureDto);
                            }
                        }
                    } catch (Exception e) {
                        log.info("Error at take disclosures : " + e.getMessage());
                        throw new RuntimeException(e.getMessage());
                    }

                    //Sending email
                    if (newDisclosures.size() > 0) {
                        ExecutorService chachedThreadPool = Executors.newCachedThreadPool();
                        List<UserDto> userDtoList = userService.getAllUsers();

                        for (UserDto userDto : userDtoList) { // throw it cache  at every day
                            PrepareAndSendMail prepareAndSendMail = beanFactory.createBean(PrepareAndSendMail.class);
                            prepareAndSendMail.setNewDisclosures(newDisclosures);
                            prepareAndSendMail.setUserDto(userDto);
                            chachedThreadPool.execute(prepareAndSendMail);
                        }
                        chachedThreadPool.shutdown();
                    }
                    log.info("Take Disclosure is done !");


                    try {
                        Thread.sleep(kapNewsConstants.getScheduledTakeDisclosuresFixedRate());
                    } catch (InterruptedException e) {
                        log.info("Error at take disclosures sleep : " + e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (Exception e) {
            log.info("Error at Disclosure Thread : " + e.getMessage());
            emailService.sendErrorEmail(e.getMessage(), "talmer2001@gmail.com");
            throw new ThreadException(e.getMessage());
        }
    }

    public void stopThread() {
        this.execute = false;
        log.info("TakeDisclosureThread stopped !");
    }

    public void resumeThread() {
        this.execute = true;
    }
}
