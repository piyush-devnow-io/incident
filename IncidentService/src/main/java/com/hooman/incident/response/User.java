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
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Team> getTeams() {
		return teams;
	}

}
