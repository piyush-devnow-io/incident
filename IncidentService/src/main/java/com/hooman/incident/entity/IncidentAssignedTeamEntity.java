package com.hooman.incident.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "incident_assignment_details")
public class IncidentAssignedTeamEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;

	@Column(name = "tenant_id")
	Integer tenantId;

	@Column(name = "incident_id")
	String incidentId;

	public IncidentAssignedTeamEntity() {
		super();
	}

	public IncidentAssignedTeamEntity(Long id, Integer tenantId, String incidentId, String teamId) {
		super();
		this.id = id;
		this.tenantId = tenantId;
		this.incidentId = incidentId;
//		this.id = id;
		this.teamId = teamId;
	}

	@Column(name = "list_of_team_ids")
	private String teamId;

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
