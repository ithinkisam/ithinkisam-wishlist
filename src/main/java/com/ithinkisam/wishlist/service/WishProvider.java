package com.ithinkisam.wishlist.service;

import java.util.List;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.domain.Wish;

public interface WishProvider {

	Wish getById(int id);
	
	List<Wish> getByUser(User user);
	
	List<Wish> getByFulfiller(User user);
	
	Wish add(String description, User user);

	void update(Wish wish, User user);
	
	void fulfill(Wish wish, User user);
	
	void unfulfill(Wish wish, User user);
	
}
