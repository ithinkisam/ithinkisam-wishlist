package com.ithinkisam.wishlist.repository;

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
	
}