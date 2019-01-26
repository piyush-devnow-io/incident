package com.hooman.incident.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "incident_id_vs_team_id_vs_user_id_vs_response_time_json")
public class IncidentTeamResponseEntity {
	@ApiModelProperty(notes = "incident team response entity id", name = "id", required = false, value = "test id")
	@Column(name = "id")
	private String id;
	@ApiModelProperty(notes = "incident id", name = "incidentId", required = true, value = "test id")
	@Column(name = "incident_id")
	private String incidentId;
	@ApiModelProperty(notes = "team id wise user id wise response time", name = "teamIdVsUserIdVsResponseTime", required = false, value = "test time")
	@Column(name = "team_id_vs_user_id_vs_response_time_json")
	String teamIdVsUserIdVsResponseTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public String getTeamIdVsUserIdVsResponseTime() {
		return teamIdVsUserIdVsResponseTime;
	}

	public void setTeamIdVsUserIdVsResponseTime(String teamIdVsUserIdVsResponseTime) {
		this.teamIdVsUserIdVsResponseTime = teamIdVsUserIdVsResponseTime;
	}

	public IncidentTeamResponseEntity() {
		super();
	}

	public IncidentTeamResponseEntity(String id, String incidentId, String teamIdVsUserIdVsResponseTime) {
		super();
		this.id = id;
		this.incidentId = incidentId;
		this.teamIdVsUserIdVsResponseTime = teamIdVsUserIdVsResponseTime;
	}
}
