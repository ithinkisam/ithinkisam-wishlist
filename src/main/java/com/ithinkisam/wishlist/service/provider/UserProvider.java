package com.ithinkisam.wishlist.service.provider;

import com.ithinkisam.wishlist.domain.User;

public interface UserProvider {

	void addNewUser(User user);

	User getUserByUsername(String username);

	User getUserByEmail(String email);

}
