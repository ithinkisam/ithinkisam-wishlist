package com.ithinkisam.wishlist.service;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.domain.Wish;

public interface WishProvider {

	void add(String description, User user);

	void update(Wish wish, User user);
	
	void remove(int id, User user);
	
}
