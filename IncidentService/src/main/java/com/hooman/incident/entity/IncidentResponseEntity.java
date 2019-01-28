package com.hooman.incident.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "incident_response_details")
public class IncidentResponseEntity {

	@Id
	String id;
	@Embedded
	IncidentIdentity incidentIdentity;

	@Column(name = "team_id_vs_user_id_vs_response_time")
	String teamIdVsUserIdVsResponseTime;

	public IncidentResponseEntity() {
		super();
	}

	public IncidentResponseEntity(String id,IncidentIdentity incidentIdentity, String teamIdVsUserIdVsResponseTime) {
		super();
		this.id = id;
		this.incidentIdentity = incidentIdentity;
		this.teamIdVsUserIdVsResponseTime = teamIdVsUserIdVsResponseTime;
	}

	public IncidentIdentity getIncidentIdentity() {
		return incidentIdentity;
	}

	public void setIncidentIdentity(IncidentIdentity incidentIdentity) {
		this.incidentIdentity = incidentIdentity;
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

}
