package com.hooman.incident.response;

import java.util.Map;

public class IncidentResponseData {
	private Integer tenantId;
	private String incidentId;
	private String userId;
	private Integer responseTime;

	public IncidentResponseData() {
		super();
	}

	public IncidentResponseData(Integer tenantId, String incidentId, String userId, Integer responseTime) {
		super();
		this.tenantId = tenantId;
		this.incidentId = incidentId;
		this.userId = userId;
		this.responseTime = responseTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Integer responseTime) {
		this.responseTime = responseTime;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

}
