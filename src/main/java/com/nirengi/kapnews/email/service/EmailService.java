package com.nirengi.kapnews.email.service;

import java.util.List;

public interface EmailService {
    void sendEmail(String context, List<String> receiverEmails);

    void sendEmail(String context, String receiverEmail);

    void sendErrorEmail(String context, String receiverEmail);
}
