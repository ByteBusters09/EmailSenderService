package com.app.EmailSenderService.Service;

import ui.EmailRequest;

import java.io.File;
import java.io.InputStream;

public interface EmailService {

    // send email to single person
    void sendEmail(String to, String subject, String message);

    // send email to multiple person
    void sendEmail(String []to, String subject, String message);

    // send email with html context
    void sendEmailWithHtmlContext(String to, String subject, String message);

    // send email with the help of a file
    void sendEmailWithFile(String to, String subject, String message,File file);

    //send email via input stream
    void sendEmailWithInputStream(String to, String subject, String message, InputStream is);
}
