package com.hooman.incident.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamIncidentResponseData {
	private Map<String, List<IncidentDetails>> teamIdVsListOfIncidentDetails;

	public Map<String, List<IncidentDetails>> getTeamIdVsListOfIncidentDetails() {
		if(teamIdVsListOfIncidentDetails == null)
		{
			teamIdVsListOfIncidentDetails = new HashMap<String, List<IncidentDetails>>();
		}
		return teamIdVsListOfIncidentDetails;
	}

	public void setTeamIdVsListOfIncidentDetails(Map<String, List<IncidentDetails>> teamIdVsListOfIncidentDetails) {
		this.teamIdVsListOfIncidentDetails = teamIdVsListOfIncidentDetails;
	}

	public TeamIncidentResponseData(Map<String, List<IncidentDetails>> teamIdVsListOfIncidentDetails) {
		super();
		this.teamIdVsListOfIncidentDetails = teamIdVsListOfIncidentDetails;
	}

	public TeamIncidentResponseData() {
		super();
	}

}
