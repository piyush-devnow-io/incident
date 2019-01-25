package com.hooman.incident.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hooman.incident.incidentidentity.IncidentIdentity;

@Entity
@Table(name = "incident")
public class Incident {

	@EmbeddedId
	IncidentIdentity incidentIdentity;

	@Column(name = "user_id")
	String userId;
	@Column(name = "subject")
	String subject;
	@Column(name = "criteria1")
	String criteria1;
	@Column(name = "criteria2")
	String criteria2;
	@Column(name = "criteria3")
	String criteria3;
	@Column(name = "criteria4")
	String criteria4;
	@Column(name = "criteria5")
	String criteria5;
	@Column(name = "criteria6")
	String criteria6;
	@Column(name = "criteria7")
	String criteria7;
	@Column(name = "criteria8")
	String criteria8;
	@Column(name = "criteria9")
	String criteria9;
	@Column(name = "criteria10")
	String criteria10;

	@Column(name = "assigned_team_entity_id")
	String assignedTeamEntityId;

	@Column(name = "incident_response_entity_id")
	String incidentResponseEntityId;

	public Incident() {
		super();
	}

	public Incident(IncidentIdentity incidentIdentity, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, String assignedTeamEntityId,
			String incidentResponseEntityId) {
		super();
		this.incidentIdentity = incidentIdentity;
		this.userId = userId;
		this.subject = subject;
		this.criteria1 = criteria1;
		this.criteria2 = criteria2;
		this.criteria3 = criteria3;
		this.criteria4 = criteria4;
		this.criteria5 = criteria5;
		this.criteria6 = criteria6;
		this.criteria7 = criteria7;
		this.criteria8 = criteria8;
		this.criteria9 = criteria9;
		this.criteria10 = criteria10;
		this.assignedTeamEntityId = assignedTeamEntityId;
		this.incidentResponseEntityId = incidentResponseEntityId;
	}

	public String getIncidentResponseEntityId() {
		return incidentResponseEntityId;
	}

	public void setIncidentResponseEntityId(String incidentResponseEntityId) {
		this.incidentResponseEntityId = incidentResponseEntityId;
	}

	public IncidentIdentity getIncidentIdentity() {
		return incidentIdentity;
	}

	public void setIncidentIdentity(IncidentIdentity incidentIdentity) {
		this.incidentIdentity = incidentIdentity;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCriteria1() {
		return criteria1;
	}

	public void setCriteria1(String criteria1) {
		this.criteria1 = criteria1;
	}

	public String getCriteria2() {
		return criteria2;
	}

	public void setCriteria2(String criteria2) {
		this.criteria2 = criteria2;
	}

	public String getCriteria3() {
		return criteria3;
	}

	public void setCriteria3(String criteria3) {
		this.criteria3 = criteria3;
	}

	public String getCriteria4() {
		return criteria4;
	}

	public void setCriteria4(String criteria4) {
		this.criteria4 = criteria4;
	}

	public String getCriteria5() {
		return criteria5;
	}

	public void setCriteria5(String criteria5) {
		this.criteria5 = criteria5;
	}

	public String getCriteria6() {
		return criteria6;
	}

	public void setCriteria6(String criteria6) {
		this.criteria6 = criteria6;
	}

	public String getCriteria7() {
		return criteria7;
	}

	public void setCriteria7(String criteria7) {
		this.criteria7 = criteria7;
	}

	public String getCriteria8() {
		return criteria8;
	}

	public void setCriteria8(String criteria8) {
		this.criteria8 = criteria8;
	}

	public String getCriteria9() {
		return criteria9;
	}

	public void setCriteria9(String criteria9) {
		this.criteria9 = criteria9;
	}

	public String getCriteria10() {
		return criteria10;
	}

	public void setCriteria10(String criteria10) {
		this.criteria10 = criteria10;
	}

	public String getAssignedTeamEntityId() {
		return assignedTeamEntityId;
	}

	public void setAssignedTeamEntityId(String assignedTeamEntityId) {
		this.assignedTeamEntityId = assignedTeamEntityId;
	}


}
