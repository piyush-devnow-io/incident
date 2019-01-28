package com.hooman.incident.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "incident_assignment_details")
public class IncidentAssignedTeamEntity {
	 @ApiModelProperty(notes = "incident assigned team entity id",name="id",required=false,value="test id")
	@Column(name = "id")
	 @Id
	private String id;
	 @ApiModelProperty(notes = "incident id",name="incidentId",required=false,value="test id")
	@Column(name = "incident_id")
	private String incidentId;
	 @ApiModelProperty(notes = "tenant id",name="tenantId",required=false,value="test id")
	@Column(name = "tenant_id")
	private Integer tenantId;
	
	public IncidentAssignedTeamEntity() {
		super();
	}
	public IncidentAssignedTeamEntity(String id, String incidentId, Integer tenantId, List<String> listOfTeamIds) {
		super();
		this.id = id;
		this.incidentId = incidentId;
		this.tenantId = tenantId;
		this.listOfTeamIds = listOfTeamIds;
	}
	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	@OneToOne
	@Column(name = "list_of_team_ids")
	private List<String> listOfTeamIds;
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
	public List<String> getListOfTeamIds() {
		return listOfTeamIds;
	}
	public void setListOfTeamIds(List<String> listOfTeamIds) {
		this.listOfTeamIds = listOfTeamIds;
	}
}
