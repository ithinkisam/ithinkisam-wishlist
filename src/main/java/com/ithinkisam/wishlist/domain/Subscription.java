package com.ithinkisam.wishlist.domain;

public class Subscription {

	private Event event;
	private User subscriber;
	private User subscription;
	private Status status;

	public Subscription(Event event, User subscriber, User subscription, Status status) {
		this.event = event;
		this.subscriber = subscriber;
		this.subscription = subscription;
		this.status = status;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @return the subscriber
	 */
	public User getSubscriber() {
		return subscriber;
	}

	/**
	 * @return the subscription
	 */
	public User getSubscription() {
		return subscription;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
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
		result = prime * result + ((subscriber == null) ? 0 : subscriber.hashCode());
		result = prime * result + ((subscription == null) ? 0 : subscription.hashCode());
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
		Subscription other = (Subscription) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (subscriber == null) {
			if (other.subscriber != null)
				return false;
		} else if (!subscriber.equals(other.subscriber))
			return false;
		if (subscription == null) {
			if (other.subscription != null)
				return false;
		} else if (!subscription.equals(other.subscription))
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
		return "Subscription [event=" + event + ", subscriber=" + subscriber + ", subscription=" + subscription
				+ ", status=" + status + "]";
	}

}
