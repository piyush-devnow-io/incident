package com.hooman.incident.response;

/**
 * @author shubham.agarwal
 *
 */
public class Team {

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

}
