package com.ithinkisam.wishlist.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ithinkisam.wishlist.config._Constants;
import com.ithinkisam.wishlist.domain.NewUser;
import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.messaging.Message;
import com.ithinkisam.wishlist.messaging.Severity;
import com.ithinkisam.wishlist.security.VerificationToken;
import com.ithinkisam.wishlist.service.UserProvider;
import com.ithinkisam.wishlist.service.exception.EmailExistsException;
import com.ithinkisam.wishlist.service.exception.UsernameExistsException;
import com.ithinkisam.wishlist.service.validation.NewUserValidator;
import com.ithinkisam.wishlist.web.event.OnRegistrationCompleteEvent;

@Controller
public class AuthController {

	/**
	 * Providers
	 */
	@Autowired
	private UserProvider userProvider;
	
	/**
	 * Validators
	 */
	private NewUserValidator newUserValidator = new NewUserValidator();
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@RequestMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(HttpServletRequest request,
			@ModelAttribute NewUser newUser) {
		if (request.isUserInRole("USER")) {
			return "redirect:/";
		}
		return "registration";
	}
	
	@PostMapping("register")
	public String registerNewUser(
			@ModelAttribute(name = _Constants.MODEL_MESSAGES) List<Message> messages,
			@ModelAttribute NewUser newUser,
			BindingResult bindingResult,
			HttpServletRequest request) {
		
		newUserValidator.validate(newUser, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		
		User user = createUserAccount(newUser, bindingResult);
		if (user == null) {
			bindingResult.reject("auth.registrationError");
			return "registration";
		}
		
		try {
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), System.getenv("APP_ROOT")));
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("auth.registrationError");
			return "registration";
		}
		
		messages.add(new Message(Severity.SUCCESS, "registration.verify"));
		return "redirect:/login?message=verifyRegistration&messageType=info";
	}
	
	@GetMapping("/registrationConfirm")
	public String confirmRegistration(
			@ModelAttribute(name = _Constants.MODEL_MESSAGES) List<Message> messages,
			@RequestParam("token") String token) {
		
		VerificationToken verificationToken = userProvider.getVerificationToken(token);
		if (verificationToken == null) {
			return "redirect:/login?message=tokenNotFound&messageType=danger";
		}
		if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			return "redirect:/login?message=tokenExpired&messageType=danger";
		}
		
		userProvider.enableNewUser(verificationToken.getUser().getUsername());
		messages.add(new Message(Severity.SUCCESS, "registration.confirmed"));
		return "redirect:/login?message=registrationVerified&messageType=success";
	}
	
	private User createUserAccount(NewUser newUser, BindingResult bindingResult) {
		User user = null;
		try {
			user = userProvider.registerNewUserAccount(newUser);
		} catch (EmailExistsException e) {
			LOGGER.error("Unable to register new user; Email already exists: " + newUser.getEmail());
			bindingResult.reject("email", "validation.newuser.email.exists");
		} catch (UsernameExistsException e) {
			LOGGER.error("Unable to register new user; Username already exists: " + newUser.getUsername());
			bindingResult.reject("username", "validation.newuser.username.exists");
		}
		return user;
	}
	
}
