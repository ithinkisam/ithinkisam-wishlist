package com.ithinkisam.wishlist.service.provider;

import java.util.List;

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
	public Wish getById(int id) {
		return wishRepository.findById(id);
	}

	@Override
	public List<Wish> getByUser(User user) {
		return wishRepository.findByUser(user.getUsername());
	}

	@Override
	public List<Wish> getByFulfiller(User user) {
		return wishRepository.findByFulfiller(user.getUsername());
	}

	@Override
	public Wish add(String description, User user) {
		return wishRepository.save(description, user.getUsername());
	}

	@Override
	public void update(Wish wish, User user) {
		Wish exists = wishRepository.findById(wish.getId());
		if (exists == null) {
			// TODO throw exception
		}
		if (!exists.getUsername().equals(user.getUsername())) {
			// TODO throw exception
		}
		wishRepository.update(wish.getId(), wish.getDescription());
	}

	@Override
	public void fulfill(Wish wish, User user) {
		Wish existing = wishRepository.findById(wish.getId());
		if (existing == null) {
			// TODO throw exception
		}
		if (existing.getUsername().equals(user.getUsername())) {
			// TODO throw exception
			// cannot fulfill own wish
		}
		wishRepository.fulfill(wish.getId(), user.getUsername());
	}

	@Override
	public void unfulfill(Wish wish, User user) {
		Wish existing = wishRepository.findById(wish.getId());
		if (existing == null) {
			// TODO throw exception
		}
		if (existing.getUsername().equals(user.getUsername())) {
			// TODO throw exception
			// cannot fulfill own wish
		}
		wishRepository.unfulfill(wish.getId());
	}

}
