package com.hooman.incident.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author shubham.agarwal
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer tenantId;

	private String firstName;

	private String lastName;

	private String role;

	private String username;

	private String photoUrl;

	private List<Team> teams;

	public Integer getTenantId() {
		return tenantId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public String getRole() {
		return role;
	}

	public User() {

	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param photoUrl the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Team> getTeams() {
		return teams;
	}

}
