package com.ithinkisam.wishlist.service.provider;

import java.net.URL;
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
		return populateTags(wishRepository.findById(id));
	}

	@Override
	public List<Wish> getByUser(User user) {
		return populateTags(wishRepository.findByUser(user.getUsername()));
	}

	@Override
	public List<Wish> getByFulfiller(User user) {
		return populateTags(wishRepository.findByFulfiller(user.getUsername()));
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

	@Override
	public void remove(int id, User user) {
		Wish existing = wishRepository.findById(id);
		if (existing == null) {
			// TODO throw exception
		}
		if (!existing.getUsername().equals(user.getUsername())) {
			// TODO throw exeception
			// cannot delete other's wish
		}
		wishRepository.remove(id);
	}

	@Override
	public void addTag(URL url, int wishId, User user) {
		Wish existing = wishRepository.findById(wishId);
		if (existing == null) {
			// TODO throw exception
		}
		if (!existing.getUsername().equals(user.getUsername())) {
			// TODO throw exception
			// cannot add tag to other's wish
		}
		wishRepository.addTag(wishId, url);
	}

	@Override
	public void removeTag(URL url, int wishId, User user) {
		Wish existing = wishRepository.findById(wishId);
		if (existing == null) {
			// TODO throw exception
		}
		if (!existing.getUsername().equals(user.getUsername())) {
			// TODO throw exception
			// cannot add tag to other's wish
		}
		wishRepository.removeTag(wishId, url);
	}
	
	private List<Wish> populateTags(List<Wish> wishes) {
		if (wishes == null) { return null; }
		for (Wish wish : wishes) {
			populateTags(wish);
		}
		return wishes;
	}
	
	private Wish populateTags(Wish wish) {
		if (wish == null) { return null; }
		wish.setTags(wishRepository.findTagsByWish(wish.getId()));
		return wish;
	}

}
