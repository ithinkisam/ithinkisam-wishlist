package com.ithinkisam.wishlist.service.exception;

public class UsernameExistsException extends Exception {

	private static final long serialVersionUID = -3715894189517535536L;

	/**
	 * Constructs a new exception with the specified detail message. The cause
	 * is not initialized, and may subsequently be initialized by a call to
	 * {@link #initCause}.
	 *
	 * @param message
	 *            the detail message. The detail message is saved for later
	 *            retrieval by the {@link #getMessage()} method.
	 */
	public UsernameExistsException(String message) {
		super(message);
	}

}
