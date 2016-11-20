package com.ithinkisam.wishlist.repository;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.security.VerificationToken;

public interface UserRepository {

	User findByEmail(String email);
	
	User findByUsername(String username);
	
	void save(User user);
	
	void enable(String username);
	
	void disable(String username);
	
	void updateInformation(User user);
	
	void updatePassword(String username, String existingPassword, String newPassword);
	
	void persistVerificationToken(VerificationToken token);
	
	VerificationToken getVerificationToken(String token);
	
}
