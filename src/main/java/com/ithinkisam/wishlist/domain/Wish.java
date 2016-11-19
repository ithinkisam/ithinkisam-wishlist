package com.ithinkisam.wishlist.domain;

import java.net.URL;
import java.util.Set;

public class Wish {

	private int id;
	private User user;
	private String description;
	private Set<URL> tips;

	public Wish(int id, User user, String description, Set<URL> tips) {
		this.id = id;
		this.user = user;
		this.description = description;
		this.tips = tips;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the tips
	 */
	public Set<URL> getTips() {
		return tips;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wish other = (Wish) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Wish [id=" + id + ", user=" + user + ", description=" + description + ", tips=" + tips + "]";
	}

}
