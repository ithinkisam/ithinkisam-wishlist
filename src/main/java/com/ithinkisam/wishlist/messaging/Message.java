package com.ithinkisam.wishlist.messaging;

import java.util.Arrays;

public class Message {

	private Severity severity;
	private String code;
	private String[] arguments;

	public Message(Severity severity, String code, String... arguments) {
		this.severity = severity;
		this.code = code;
		if (arguments != null) {
			String.join(",", Arrays.asList(arguments));
		}
	}

	/**
	 * @return the severity
	 */
	public Severity getSeverity() {
		return severity;
	}

	/**
	 * @param severity
	 *            the severity to set
	 */
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the arguments
	 */
	public String[] getArguments() {
		return arguments;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 */
	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

}
