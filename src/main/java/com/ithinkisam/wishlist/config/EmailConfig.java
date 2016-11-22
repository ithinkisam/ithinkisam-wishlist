package com.ithinkisam.wishlist.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

@Configuration
public class EmailConfig {

	private static final String FROM = "registration@ithinkisam.com";
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailConfig.class);
	
	@Bean
	public MailSender mailSender() {
		return new MailSender() {

			SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
			
			@Override
			public void send(SimpleMailMessage message) throws MailException {
				LOGGER.debug("Sending email using API key: {}", System.getenv("SENDGRID_API_KEY"));
				LOGGER.debug("To: {}", consolidateRecipientList(message.getTo()));
				LOGGER.debug("From: {}", FROM);
				LOGGER.debug("Subject: {}", message.getSubject());
				LOGGER.debug("Content: {}", message.getText());
				Mail mail = new Mail(new Email(FROM), message.getSubject(),
						new Email(consolidateRecipientList(message.getTo())),
						new Content("text/html", message.getText()));
				Request request = new Request();
				try {
					request.method = Method.POST;
					request.endpoint = "mail/send";
					request.body = mail.build();
					sg.api(request);
				} catch (IOException e) {
					LOGGER.error("IOException occurred while constructing and sending email", e);
					throw new MailSendException("Error sending mail", e);
				}
			}

			@Override
			public void send(SimpleMailMessage... message) throws MailException {
				LOGGER.debug("((((((( WE SHOULDN'T BE HERE... )))))))");
			}
			
		};
	}
	
	private String consolidateRecipientList(String[] to) {
		if (to == null) {
			throw new MailParseException("Invalid recipient list: no addresses provided");
		}
		return String.join(",", to);
	}
	
}
