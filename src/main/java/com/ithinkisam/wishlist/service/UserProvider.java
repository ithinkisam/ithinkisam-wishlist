package com.ithinkisam.wishlist.service;

import com.ithinkisam.wishlist.domain.NewUser;
import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.security.VerificationToken;
import com.ithinkisam.wishlist.service.exception.EmailExistsException;
import com.ithinkisam.wishlist.service.exception.UsernameExistsException;

public interface UserProvider {

	User registerNewUserAccount(NewUser newUser) throws EmailExistsException, UsernameExistsException;
	
	void createVerificationToken(User user, String token);
	
	VerificationToken getVerificationToken(String token);
	
	void enableNewUser(String username);
	
	User getByUsername(String username);

}
