package com.hooman.incident.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hooman.incident.entity.Incident;
import com.hooman.incident.entity.IncidentAssignedTeamEntity;
import com.hooman.incident.entity.IncidentIdentity;
import com.hooman.incident.service.api.IIncidentService;
import com.hooman.incident.service.repository.IncidentRepository;
import com.hooman.incident.service.repository.IncidentResponseRepository;
import com.hooman.incident.utils.IncidentUtils;

@Service
public class IncidentServiceImpl implements IIncidentService {

	@Autowired
	IncidentRepository incidentRepository;

	@Autowired
	IncidentResponseRepository incidentResponseRepository;

	@Override
	public Incident createNewIncident(Integer tenantId, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds) {
		// perform validation
		String incidentId = IncidentUtils.getIncidentId();
		IncidentIdentity identity = new IncidentIdentity(incidentId, tenantId);
		Incident incident = getNewIncident(identity, userId, subject, criteria1, criteria2, criteria3, criteria4,
				criteria5, criteria6, criteria7, criteria8, criteria9, criteria10, assignedTeamIds);
		return incidentRepository.save(incident);
	}

	@Override
	public Incident getIncident(String tenantId, String incidentId) {
		IncidentIdentity identity = new IncidentIdentity(incidentId, Integer.parseInt(tenantId));
		return incidentRepository.getOne(identity);
	}

	@Override
	public void deleteIncident(String tenantId, String incidentId) {
		IncidentIdentity identity = new IncidentIdentity(incidentId, Integer.parseInt(tenantId));
		incidentRepository.deleteById(identity);
	}

	@Override
	public Incident updateIncident(String incidentId, Integer tenantId, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds) {
		IncidentIdentity identity = new IncidentIdentity(incidentId, tenantId);
		Incident incident = incidentRepository.getOne(identity);
		Incident updatedIncident = updateIncident(incident, userId, subject, criteria1, criteria2, criteria3, criteria4,
				criteria5, criteria6, criteria7, criteria8, criteria9, criteria10, assignedTeamIds);
		return incidentRepository.save(incident);
	}

	private Incident updateIncident(Incident incident, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds) {
		incident.setUserId(userId);
		incident.setSubject(subject);
		incident.setCriteria1(criteria1);
		incident.setCriteria2(criteria2);
		incident.setCriteria3(criteria3);
		incident.setCriteria4(criteria4);
		incident.setCriteria5(criteria5);
		incident.setCriteria6(criteria6);
		incident.setCriteria7(criteria7);
		incident.setCriteria8(criteria8);
		incident.setCriteria9(criteria9);
//		incident.getIncidentAssignedTeamEntity().getListOfTeamIds().addAll(assignedTeamIds);
		return incident;
	}

	@Override
	public List<Incident> getAllIncident(String tenantId) {
		return incidentRepository.findByIncidentIdentityTenantId(tenantId);
	}

	@Override
	public List<Incident> getAllIncidentAssignedToTeam(String tenantId, String teamId) {
		return incidentRepository.getAllIncidentsAssignedToTeam(tenantId, teamId);
	}

	private Incident getNewIncident(IncidentIdentity identity, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds) {
		IncidentAssignedTeamEntity assignedTeamEntity = new IncidentAssignedTeamEntity();
		assignedTeamEntity.setIncidentIdentity(identity);
		assignedTeamEntity.setTeamId(new String[] {"1"});
		assignedTeamEntity.setId(identity.getIncidentId());
		Incident incident = new Incident(identity, userId, subject, criteria1, criteria2, criteria3, criteria4,
				criteria5, criteria6, criteria7, criteria8, criteria9, criteria10, assignedTeamEntity);
		return incident;
	}

}
