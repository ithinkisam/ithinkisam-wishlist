package com.ithinkisam.wishlist.service;

import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.domain.Wish;

public interface NotificationProvider {

	void sendNewUserNotification(User user);
	
	void sendAccountRecoveryNotification(String email);
	
	void sendWishRemovalNotification(Wish wish);
	
}
