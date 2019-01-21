package com.hooman.incident.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hooman.incident.request.IncidentRequest;
import com.hooman.incident.response.Incident;
import com.hooman.incident.service.api.IIncidentService;
import com.hooman.incident.service.repository.IncidentRepository;

@Service
public class IncidentServiceImpl implements IIncidentService {

	@Autowired
	IncidentRepository incidentRepository;

	@Override
	public Incident createNewIncident(String tenantId, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds) {
		// perform validation
		return null;
	}

	@Override
	public Incident getIncident(String tenantId, String incidentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteIncident(String tenantId, String incidentId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Incident updateIncident(String incidentId, String tenantId, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> getAllIncident(String tenantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> getAllIncidentAssignedToTeam(String tenantId, String teamId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Map<String, Long>> getAllResponseTimeForIncident(String incidentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
