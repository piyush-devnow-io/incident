package com.hooman.incident.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="incident_assigned_team_list")
public class IncidentAssignedTeamEntity {
	@Column(name="id")
	private String id;
	@Column(name="incident_id")
	private String incidentId;
	@Column(name="list_of_team_ids")
	private List<String> listOfTeamIds;
}
