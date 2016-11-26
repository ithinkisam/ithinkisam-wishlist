package com.ithinkisam.wishlist.service;

import java.net.URL;
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
	
	void remove(int id, User user);
	
	void addTag(URL url, int wishId, User user);
	
	void removeTag(URL url, int wishId, User user);
	
}
