package com.nirengi.kapnews.services;

import java.util.List;

public interface EmailService {
    public void sendEmail(String context, List<String> receiverEmails);
}
