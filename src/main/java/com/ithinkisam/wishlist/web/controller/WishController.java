package com.ithinkisam.wishlist.web.controller;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping
	public String showWishes(Model model, WebRequest request) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		return view(model, user);
	}
	
	@PostMapping
	public String addNewWish(Model model, WebRequest request,
			@RequestParam("description") String description) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		wishProvider.add(description, user);
		return view(model, user);
	}
	
	@PostMapping("/delete")
	public String deleteWish(Model model, WebRequest request,
			@RequestParam("id") Integer wishId) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		wishProvider.remove(wishId, user);
		return view(model, user);
	}
	
	@PostMapping("/update")
	@ResponseBody
	public String updateWish(WebRequest request,
			@RequestParam("id") Integer wishId,
			@RequestParam("description") String description) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		Wish existing = wishProvider.getById(wishId);
		existing.setDescription(description);
		wishProvider.update(existing, user);
		return "OK";
	}
	
	@PostMapping("/tag/add")
	public String addTag(Model model, WebRequest request,
			@RequestParam("wishId") Integer wishId,
			@RequestParam("url") URL url) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		wishProvider.addTag(url, wishId, user);
		return "redirect:/wishes";
	}
	
	@PostMapping("/tag/remove")
	public String removeTag(Model model, WebRequest request,
			@RequestParam("wishId") Integer wishId,
			@RequestParam("url") URL url) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		wishProvider.removeTag(url, wishId, user);
		return "redirect:/wishes";
	}
	
	private String view(Model model, User user) {
		model.addAttribute("wishes", wishProvider.getByUser(user));
		return "wishes";
	}
}
