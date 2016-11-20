package com.ithinkisam.wishlist.security;

import java.time.LocalDateTime;

import com.ithinkisam.wishlist.domain.User;

public class VerificationToken {

	private static final int EXPIRATION = 60 * 48;

	private Long id;
	private String token;
	private User user;
	private LocalDateTime expiryDate;

	public VerificationToken() {
		super();
	}

	public VerificationToken(String token, User user) {
		this.token = token;
		this.user = user;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

	public VerificationToken(String token, LocalDateTime expiryDate, User user) {
		this.token = token;
		this.user = user;
		this.expiryDate = expiryDate;
	}

	private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {
		return LocalDateTime.now().plusMinutes(expiryTimeInMinutes);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the expiryDate
	 */
	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VerificationToken [id=" + id + ", token=" + token + ", user=" + user + ", expiryDate=" + expiryDate
				+ "]";
	}

}