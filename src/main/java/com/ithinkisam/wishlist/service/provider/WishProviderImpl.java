package com.ithinkisam.wishlist.service.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ithinkisam.wishlist.config._Constants;
import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.domain.Wish;
import com.ithinkisam.wishlist.repository.WishRepository;
import com.ithinkisam.wishlist.service.WishProvider;

@Service("wishProvider")
public class WishProviderImpl implements WishProvider {

	@Autowired
	@Qualifier(_Constants.REPOSITORY_TYPE + "WishRepository")
	private WishRepository wishRepository;

	@Override
	public void add(String description, User user) {
		wishRepository.save(description, user.getUsername());
	}

	@Override
	public void update(Wish wish, User user) {
		wishRepository.update(wish.getId(), wish.getDescription());
	}

	@Override
	public void remove(int id, User user) {
		
	}
	
}
