package com.ithinkisam.wishlist.web.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.ithinkisam.wishlist.domain.User;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private static final long serialVersionUID = -4657694434323139245L;

	private final String appUrl;
	private final Locale locale;
	private final User user;

	public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
		super(user);

		this.user = user;
		this.locale = locale;
		this.appUrl = appUrl;
	}

	/**
	 * @return the appUrl
	 */
	public String getAppUrl() {
		return appUrl;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

}
