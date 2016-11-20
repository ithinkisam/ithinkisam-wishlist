package com.ithinkisam.wishlist.web.event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.service.UserProvider;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
    @Autowired
    private UserProvider userProvider;
    
//    @Autowired
//    private MessageSource messages;
    
//    @Autowired
//    private MailSender mailSender;
 
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }
 
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userProvider.createVerificationToken(user, token);
         
//        String recipientAddress = user.getEmail();
//        String subject = "Registration Confirmation";
//        String confirmationUrl = event.getAppUrl() + "/regitrationConfirm?token=" + token;
//        String message = messages.getMessage("message.regSucc", null, event.getLocale());
         
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + " rn" + event.getAppUrl() + confirmationUrl);
//        mailSender.send(email);
    }
}
