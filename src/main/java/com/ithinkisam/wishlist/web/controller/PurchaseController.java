package com.ithinkisam.wishlist.web.controller;

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
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired private UserProvider userProvider;
	@Autowired private WishProvider wishProvider;
	
	@PostMapping("/fulfill")
	public String purchaseWish(WebRequest request,
			@RequestParam("eventId") Integer eventId,
			@RequestParam("wishId") Integer wishId) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		Wish wish = wishProvider.getById(wishId);
		if (!wish.isPurchased()) {
			wishProvider.fulfill(wish, user);
		}
		return "redirect:/events/" + eventId;
	}
	
	@PostMapping("/unfulfill")
	public String returnWish(WebRequest request,
			@RequestParam("eventId") Integer eventId,
			@RequestParam("wishId") Integer wishId) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		Wish wish = wishProvider.getById(wishId);
		if (wish.isPurchased() && wish.getPurchaser().equals(user.getUsername())) {
			wishProvider.unfulfill(wish, user);
		}
		return "redirect:/events/" + eventId;
	}
	
}
