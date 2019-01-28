package com.hooman.incident.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hooman.incident.entity.IncidentIdentity;
import com.hooman.incident.entity.IncidentResponseEntity;
import com.hooman.incident.request.ResponseDetails;
import com.hooman.incident.service.api.IIncidentResponseService;
import com.hooman.incident.service.repository.IncidentResponseRepository;

@Service
public class IncidentResponseServiceImpl implements IIncidentResponseService {

	@Autowired
	IncidentResponseRepository incidentResponseRepository;

	@Override
	public void provideResponseForIncident(Integer tenantId, String incidentId, ResponseDetails responseDetails) {
		// TODO Auto-generated method stub
		IncidentIdentity incidentIdentity = new IncidentIdentity();
		incidentIdentity.setIncidentId(incidentId);
		incidentIdentity.setTenantId(tenantId);
		Optional<IncidentResponseEntity> foundExistingIncident = incidentResponseRepository.findById(incidentIdentity);
		List<Integer> listOfResponseTimes = null;
		if (!foundExistingIncident.isPresent()) {
			listOfResponseTimes = new ArrayList<Integer>();
		}
		listOfResponseTimes.add(responseDetails.getTime());
		IncidentResponseEntity entity = new IncidentResponseEntity();
		entity.setIncidentIdentity(incidentIdentity);
		entity.setTeamIdVsUserIdVsResponseTime(responseDetails.getTime()+"");
		incidentResponseRepository.save(entity);
	}

	@Override
	public IncidentResponseEntity getAllResponsesForIncidentId(Integer tenantId, String incidentId) {
		IncidentIdentity incidentIdentity = new IncidentIdentity();
		incidentIdentity.setIncidentId(incidentId);
		incidentIdentity.setTenantId(tenantId);
		return incidentResponseRepository.findById(incidentIdentity).get();
	}

}
