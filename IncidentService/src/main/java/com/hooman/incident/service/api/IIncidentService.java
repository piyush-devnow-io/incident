package com.hooman.incident.service.api;

import java.util.List;
import java.util.Map;

import com.hooman.incident.entity.Incident;
import com.hooman.incident.entity.IncidentResponseEntity;
import com.hooman.incident.response.IncidentDetails;
import com.hooman.incident.response.IncidentResponseDetails;

public interface IIncidentService {
	Incident createNewIncident(Integer tenantId, String userId, String subject, String criteria1, String criteria2,
			String criteria3, String criteria4, String criteria5, String criteria6, String criteria7, String criteria8,
			String criteria9, String criteria10, List<String> assignedTeamIds);

	Incident getIncident(Integer tenantId, String incidentId);

	void deleteIncident(String tenantId, String incidentId);

	Incident updateIncident(String incidentId, Integer tenantId, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds);

	List<Incident> getAllIncident(Integer tenantId);

	List<IncidentDetails> getAllIncidentAssignedToTeam(Integer tenantId, String teamId);

	Map<String, List<String>> getIncidentIdVsAssignedTeamIdList();

}
