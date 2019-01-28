package com.hooman.incident.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "incident_assignment_details")
public class IncidentAssignedTeamEntity {

	@Id
	String id;

	@Embedded
	IncidentIdentity incidentIdentity;

	public IncidentAssignedTeamEntity() {
		super();
	}

	public IncidentAssignedTeamEntity(String id, IncidentIdentity incidentIdentity, String[] teamId) {
		super();
		this.id = id;
		this.incidentIdentity = incidentIdentity;
		this.teamId = teamId;
	}

	@OneToOne(mappedBy = "incidentAssignedTeamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private Incident incident;

	@Column(name = "list_of_team_ids")
	private String[] teamId;

	public String[] getTeamId() {
		return teamId;
	}

	public void setTeamId(String[] teamId) {
		this.teamId = teamId;
	}

	public IncidentIdentity getIncidentIdentity() {
		return incidentIdentity;
	}

	public void setIncidentIdentity(IncidentIdentity incidentIdentity) {
		this.incidentIdentity = incidentIdentity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
