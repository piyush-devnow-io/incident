package com.hooman.incident.incidentidentity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "incident_response_details")
public class IncidentResponseEntity {

	@Embedded
	IncidentIdentity incidentIdentity;

	@Column(name = "team_id_vs_user_id_vs_response_time")
	String teamIdVsUserIdVsResponseTime;

	public IncidentResponseEntity() {
		super();
	}

	public IncidentResponseEntity(IncidentIdentity incidentIdentity, String teamIdVsUserIdVsResponseTime) {
		super();
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

}
