package com.hooman.incident.request;

public class ResponseDetails {

	String userId;
	String teamId;
	Integer time;

	public ResponseDetails() {
		super();
	}

	public ResponseDetails(String userId,String teamId, Integer time) {
		super();
		this.userId = userId;
		this.time = time;
		this.teamId  = teamId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
}
