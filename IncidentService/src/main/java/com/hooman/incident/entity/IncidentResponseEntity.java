package com.hooman.incident.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "incident_response_details")
public class IncidentResponseEntity {

	@Column(name = "tenant_id")
	Integer tenantId;

	@Id
	@Column(name = "incident_id")
	String incidentId;

	@Column(name = "team_id_vs_user_id_vs_response_time")
	String teamIdVsUserIdVsResponseTime;

	public IncidentResponseEntity() {
		super();
	}

	public IncidentResponseEntity(Integer tenantId, String incidentId, String teamIdVsUserIdVsResponseTime) {
		super();
		this.tenantId = tenantId;
		this.incidentId = incidentId;
		this.teamIdVsUserIdVsResponseTime = teamIdVsUserIdVsResponseTime;
	}

	public String getTeamIdVsUserIdVsResponseTime() {
		return teamIdVsUserIdVsResponseTime;
	}

	public void setTeamIdVsUserIdVsResponseTime(String teamIdVsUserIdVsResponseTime) {
		this.teamIdVsUserIdVsResponseTime = teamIdVsUserIdVsResponseTime;
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
