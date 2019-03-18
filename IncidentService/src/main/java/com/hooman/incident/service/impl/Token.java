package com.hooman.incident.service.impl;

/**
 * @author shubham
 *
 */
public class Token {

	private String token;
	private String type;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param token
	 * @param type
	 */
	public Token(String token, String type) {
		this.token = token;
		this.type = type;
	}

	/**
	 * 
	 */
	public Token() {
		// TODO Auto-generated constructor stub
	}

}
