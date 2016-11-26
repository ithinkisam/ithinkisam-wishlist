package com.ithinkisam.wishlist.domain;

import java.net.URL;
import java.util.List;

public class Wish {

	private int id;
	private String username;
	private String description;
	private boolean purchased;
	private List<URL> tags;

	public Wish() {
		/* empty */
	}

	public Wish(int id, String username, String description, boolean purchased) {
		this.id = id;
		this.username = username;
		this.description = description;
		this.purchased = purchased;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the purchased
	 */
	public boolean isPurchased() {
		return purchased;
	}

	/**
	 * @param purchased
	 *            the purchased to set
	 */
	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}

	/**
	 * @return the tags
	 */
	public List<URL> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(List<URL> tags) {
		this.tags = tags;
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
		return "Wish [id=" + id + ", username=" + username + ", description=" + description + ", tags=" + tags + "]";
	}

}
