package com.ithinkisam.wishlist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.domain.Wish;
import com.ithinkisam.wishlist.service.UserProvider;
import com.ithinkisam.wishlist.service.WishProvider;

@Controller
@RequestMapping("/wishes")
public class WishController {

	@Autowired private UserProvider userProvider;
	@Autowired private WishProvider wishProvider;
	
	public List<Wish> getWishes(WebRequest request) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		return wishProvider.getByUser(user);
	}
	
	@PostMapping
	public String addNewWish(WebRequest request,
			@RequestParam("description") String description) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		wishProvider.add(description, user);
		return "redirect:/";
	}
	
}
