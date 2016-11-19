package com.ithinkisam.wishlist.domain;

import java.util.Set;

public class Party {

	private Event event;
	private Set<User> invitees;

	public Party(Event event, Set<User> invitees) {
		this.event = event;
		this.invitees = invitees;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @return the invitees
	 */
	public Set<User> getInvitees() {
		return invitees;
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
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((invitees == null) ? 0 : invitees.hashCode());
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
		Party other = (Party) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (invitees == null) {
			if (other.invitees != null)
				return false;
		} else if (!invitees.equals(other.invitees))
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
		return "Party [event=" + event + ", invitees=" + invitees + "]";
	}

}
