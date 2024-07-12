package com.app.EmailSenderService;

import com.app.EmailSenderService.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class EmailSenderServiceApplicationTests {

	@Autowired
	private EmailService emailService;

	@Test
	void emailSendTest() {
		System.out.println ("Sending Email....!!!");
		emailService.sendEmail ("bytebstr@gmail.com", "Email from Springboot", "This email is sent using springboot while creating EmailService." );
	}

	@Test
	void sendEmailWithHtmlContext(){
		String html = "" + "<h1 style = 'color:red;border: 0.5px solid red;'> Welcome to sending mail with html" + "";
		emailService.sendEmailWithHtmlContext ("bytebstr@gmail.com", "Email from Springboot", "This email is sent using springboot while creating EmailService." );
	}

	@Test
	void sendEmailWithFile(){
		emailService.sendEmailWithFile ("bytebstr@gmail.com", "Email from Springboot", "This email is sent using springboot while creating EmailService.", new File ("") );

	}

	@Test
	void sendEmailWithInputStream(){
		File file = new File ("");
		try {
			InputStream inputStream = new FileInputStream (file);
			emailService.sendEmailWithInputStream ("bytebstr@gmail.com", "Email from Springboot", "This email is sent using springboot while creating EmailService.", inputStream);
		}catch (FileNotFoundException e){
			throw new RuntimeException (e);
			}
		}
	}

