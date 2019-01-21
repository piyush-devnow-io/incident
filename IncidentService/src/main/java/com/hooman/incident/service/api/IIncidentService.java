package com.hooman.incident.service.api;

import java.util.List;
import java.util.Map;

import com.hooman.incident.request.IncidentRequest;
import com.hooman.incident.response.Incident;

public interface IIncidentService {
	Incident createNewIncident(String tenantId, String userId, String subject, String criteria1, String criteria2,
			String criteria3, String criteria4, String criteria5, String criteria6, String criteria7, String criteria8,
			String criteria9, String criteria10, List<String> assignedTeamIds);

	Incident getIncident(String tenantId, String incidentId);

	void deleteIncident(String tenantId, String incidentId);

	Incident updateIncident(String incidentId, String tenantId, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds);

	List<Incident> getAllIncident(String tenantId);

	List<Incident> getAllIncidentAssignedToTeam(String tenantId, String teamId);

	Map<String, Map<String, Long>> getAllResponseTimeForIncident(String incidentId);
}
