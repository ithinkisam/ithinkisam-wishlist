package com.ithinkisam.wishlist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ithinkisam.wishlist.service.provider.TestProvider;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private TestProvider testProvider;
	
	@GetMapping
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/test")
	public String testDatabase(Model model) {
		model.addAttribute("users", testProvider.getUsers());
		return "test";
	}

}
