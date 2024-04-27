package com.alenazaromskih.ParserMoneyEmailSender;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ParserMoneyEmailSenderApplication {
	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(ParserMoneyEmailSenderApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
public void triggerEmail() throws MessagingException {
		emailSenderService.sendMailWithAttachment("ussalena25@gmail.com",
				"Добрый день! Во вложении данные о валютах на сегодняшний день.",
				"Данные о валютах.",
				"C:/ParserMoneyEmailSender/src/main/java/com/alenazaromskih/ParserMoneyEmailSender/ParserMoney.txt");

}
}
