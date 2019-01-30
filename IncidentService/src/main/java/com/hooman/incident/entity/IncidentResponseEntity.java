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

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	String id;

	@Column(name = "tenant_id")
	Integer tenantId;

	@Column(name = "incident_id")
	String incidentId;

	@Column(name = "team_id_vs_user_id_vs_response_time")
	String teamIdVsUserIdVsResponseTime;

	public IncidentResponseEntity() {
		super();
	}

	public IncidentResponseEntity(String id, Integer tenantId, String incidentId, String teamIdVsUserIdVsResponseTime) {
		super();
		this.id = id;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
