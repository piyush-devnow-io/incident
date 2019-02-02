package com.hooman.incident.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class IncidentDetails {

	String incidentId;

	Integer tenantId;

	String userId;

	String subject;

	String criteria1;

	String criteria2;

	String criteria3;

	String criteria4;

	String criteria5;

	String criteria6;

	String criteria7;

	String criteria8;

	String criteria9;

	String criteria10;

	Integer responseTimeForUser;

	public IncidentDetails() {
		super();
	}

	public IncidentDetails(Integer tenantId, String incidentId, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, Integer responseTimeForUser) {
		super();
		this.tenantId = tenantId;
		this.incidentId = incidentId;
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
		this.responseTimeForUser = responseTimeForUser;
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

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getResponseTimeForUser() {
		return responseTimeForUser;
	}

	public void setResponseTimeForUser(Integer responseTimeForUser) {
		this.responseTimeForUser = responseTimeForUser;
	}

}
