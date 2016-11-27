package com.ithinkisam.wishlist.web.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.service.UserProvider;
import com.ithinkisam.wishlist.web.event.OnRegistrationCompleteEvent;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
    @Autowired
    private UserProvider userProvider;
    
    @Autowired
    private MessageSource messages;
    
    @Autowired
    private MailSender mailSender;
 
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }
 
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userProvider.createVerificationToken(user, token);
         
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String message = messages.getMessage("message.registration.success", null, event.getLocale());
         
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setBcc("ithinkisam@gmail.com");
        email.setSubject(subject);
		email.setText("<p>" + message + "</p><p><a href=\"" + confirmationUrl
				+ "\">Verify my email address</a></p><p>Copy and paste this into your browser if you are having issues with the link above</p><p>"
				+ confirmationUrl + "</p>");
        mailSender.send(email);
    }
}
