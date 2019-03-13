package com.hooman.incident.response;

import java.io.Serializable;

/**
 * @author shubham.agarwal
 *
 */
public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer tenantId;

	private String title;

	public Long getId() {
		return id;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * @param id
	 * @param tenantId
	 * @param title
	 */
	public Team(Long id, Integer tenantId, String title) {
		this.id = id;
		this.tenantId = tenantId;
		this.title = title;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 */
	public Team() {
	}

}
