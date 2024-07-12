package com.app.EmailSenderService.Service.Impl;
import com.app.EmailSenderService.Service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage ();
        simpleMailMessage.setTo (to);
        simpleMailMessage.setSubject (subject);
        simpleMailMessage.setText (message);
        simpleMailMessage.setFrom ("sharmaaanjali0049@gmail.com");
        mailSender.send (simpleMailMessage);
        logger.info("Sending email to {}"); // Info level log
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage ();
        simpleMailMessage.setTo (to);
        simpleMailMessage.setSubject (subject);
        simpleMailMessage.setText (message);
        simpleMailMessage.setFrom ("bytebstr@gmail.com");
        mailSender.send (simpleMailMessage);
        logger.info("Sending email to {}"); // Info level log
    }

    @Override
    public void sendEmailWithHtmlContext(String to, String subject, String htmlContent) {

        MimeMessage mimeMailMessage = mailSender.createMimeMessage ();
        try {
            MimeMessageHelper helper =  new MimeMessageHelper(mimeMailMessage, true, "UTF-8");
            helper.setTo (to);
            helper.setSubject (subject);
            helper.setText (htmlContent);
            helper.setFrom ("bytebstr@gmail.com");
            mailSender.send (mimeMailMessage);
            logger.info("Sending email to {}"); // Info level log
        } catch (MessagingException e) {
            throw new RuntimeException (e);
        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {
       MimeMessage mimeMessage = mailSender.createMimeMessage ();
        try {
            MimeMessageHelper helper  = new MimeMessageHelper (mimeMessage, true, "UTF-8");
            helper.setTo (to);
            helper.setSubject (subject);
            helper.setText (message);
            helper.setFrom ("bytebstr@gmail.com");
            FileSystemResource fileSystemResource = new FileSystemResource (file);
            helper.addAttachment (Objects.requireNonNull (fileSystemResource.getFilename ()), file);
            mailSender.send (mimeMessage);
            logger.info("Email send success"); // Info level log
        } catch (MessagingException e) {
            throw new RuntimeException (e);
        }
    }

    @Override
    public void sendEmailWithInputStream(String to, String subject, String message, InputStream is) {
        MimeMessage mimeMessage = mailSender.createMimeMessage ();
        try {
            MimeMessageHelper helper  = new MimeMessageHelper (mimeMessage, true);
            helper.setTo (to);
            helper.setSubject (subject);
            helper.setText (message);
            helper.setFrom ("bytebstr@gmail.com");
            File file = new File ("test.png");
            FileSystemResource fileSystemResource = new FileSystemResource (file);
            helper.addAttachment (Objects.requireNonNull (fileSystemResource.getFilename ()), file);
            mailSender.send (mimeMessage);
            logger.info ("Email send successfully");
        } catch (MessagingException e) {
            throw new RuntimeException (e);
        }
    }
}
