package com.nirengi.kapnews.concurrent;

import java.util.List;
import java.util.regex.Pattern;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nirengi.kapnews.dto.DisclosureDto;
import com.nirengi.kapnews.dto.UserDto;
import com.nirengi.kapnews.services.EmailService;

import static java.lang.Thread.currentThread;

@Component
@Setter
@Scope("prototype")
public class PrepareAndSendMail implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(PrepareAndSendMail.class);
    @Autowired
    private EmailService emailService;
    private List<DisclosureDto> newDisclosures;
    private UserDto userDto;

    @Override
    public void run() {
        // userdto.getPatter
        String pattern = "\\byeni\\b|\\bihale\\b|\\byatırım\\b|\\bsipariş\\b";
        Pattern patternTitle = Pattern.compile(pattern);
        Pattern patternSummary = Pattern.compile("\\byeni\\b|\\bihale\\b|\\bortak\\b|\\bsipariş\\b|\\brapor\\b");
        String context = "";

        for (DisclosureDto newDisclosure : newDisclosures) {
            String disclosureString = "";

            if (patternTitle.matcher(newDisclosure.getTitle().toLowerCase()).find() || patternSummary.matcher(newDisclosure.getSummary().toLowerCase()).find()) {
                disclosureString += newDisclosure.toDisclosureString();

                List<String> stockCodes = List.of(newDisclosure.getStockCode().replaceAll(" ", "").split(","));

                disclosureString = disclosureString
                        + "Disclosure Link : " + "https://www.kap.org.tr/tr/Bildirim/" + newDisclosure.getDisclosureId();

                for (String code : stockCodes) {
                    try {
                        disclosureString = disclosureString
                                + "\n" + code
                                + "\nFintables financial Link : " + "https://fintables.com/sirketler/" + code.toUpperCase() + "/finansal-tablolar/gelir-tablosu"
                                + "\nTradingview  live stock graph Link : " + "https://tr.tradingview.com/chart/?symbol=BIST%3A" + code.toUpperCase()
                                + "\n";
                    } catch (Exception e) {
                        log.info("Error at preparing emails : " + e.getMessage());
                        throw new RuntimeException(e.getMessage());
                    }
                }

                context = context + "\n\n" + disclosureString;
            }

        }

        if (!context.equals("")) {
            emailService.sendEmail(context, userDto.getEmail());
            log.info("Mail is sended by the thread : " + currentThread().getName());
        }
    }
}
