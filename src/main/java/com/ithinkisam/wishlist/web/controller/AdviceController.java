package com.ithinkisam.wishlist.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ithinkisam.wishlist.config._Constants;
import com.ithinkisam.wishlist.messaging.Message;

@ControllerAdvice
public class AdviceController {

	@ModelAttribute(name = _Constants.MODEL_MESSAGES)
	public List<Message> getMessages() {
		return new ArrayList<Message>();
	}

}
