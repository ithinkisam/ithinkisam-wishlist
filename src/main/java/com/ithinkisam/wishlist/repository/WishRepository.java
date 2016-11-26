package com.ithinkisam.wishlist.repository;

import java.net.URL;
import java.util.List;

import com.ithinkisam.wishlist.domain.Wish;

public interface WishRepository {

	Wish findById(int id);
	
	List<Wish> findByUser(String username);
	
	List<Wish> findByFulfiller(String username);
	
	Wish save(String description, String username);
	
	void update(int id, String description);
	
	void fulfill(int id, String username);
	
	void unfulfill(int id);
	
	void remove(int id);
	
	void addTag(int wishId, URL tag);
	
	void removeTag(int wishId, URL tag);
	
	List<URL> findTagsByWish(int wishId);
	
}
