package com.hooman.incident.response;

import java.util.Map;


public class IncidentResponseDetails {
	private Integer tenantId;
	private String incidentId;
	Map<String, Map<String, Integer>> response;
	public IncidentResponseDetails() {
		super();
	}
	public IncidentResponseDetails(Integer tenantId, String incidentId, Map<String, Map<String, Integer>> response) {
		super();
		this.tenantId = tenantId;
		this.incidentId = incidentId;
		this.response = response;
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

	public Map<String, Map<String, Integer>> getResponse() {
		return response;
	}

	public void setResponse(Map<String, Map<String, Integer>> response) {
		this.response = response;
	}
}
