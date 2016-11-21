package com.ithinkisam.wishlist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.service.UserProvider;
import com.ithinkisam.wishlist.service.WishProvider;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired private UserProvider userProvider;
	@Autowired private WishProvider wishProvider;
	
	@GetMapping
	public String showHome(Model model, WebRequest request) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		model.addAttribute("wishes", wishProvider.getByUser(user));
		return "home";
	}

}
