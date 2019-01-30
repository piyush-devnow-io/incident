package com.hooman.incident.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
		Optional<IncidentResponseEntity> existingResponse = incidentResponseRepository.findById(incidentId);
		IncidentResponseEntity entity = null;
		String userId = responseDetails.getUserId();
		String teamId = responseDetails.getTeamId();
		Integer time = responseDetails.getTime();
		Map<String, Map<String, Long>> teamIdVSUserIdVsResponseTime = null;
		if (!existingResponse.isPresent()) {
			teamIdVSUserIdVsResponseTime = new HashMap<String, Map<String, Long>>();
			Map<String, Long> map = new HashMap<String, Long>();
			teamIdVSUserIdVsResponseTime.put(teamId, map);
			entity = new IncidentResponseEntity();
		} else {
			String data = existingResponse.get().getTeamIdVsUserIdVsResponseTime();
			teamIdVSUserIdVsResponseTime = convertStringToMap(data);
			Map<String, Long> map = teamIdVSUserIdVsResponseTime.get(teamId);
			if (map == null) {
				map = new HashMap<String, Long>();
			}
			map.put(userId, Long.parseLong(String.valueOf(time)));
			entity.setId(existingResponse.get().getId());
		}
		String convertMapIntoString = convertMapIntoString(teamIdVSUserIdVsResponseTime);
		entity.setIncidentId(incidentId);
		entity.setTenantId(tenantId);
		entity.setTeamIdVsUserIdVsResponseTime(convertMapIntoString);
		incidentResponseRepository.save(entity);
	}

	@Override
	public Map<String, Map<String, Long>> getAllResponsesForIncidentId(Integer tenantId, String incidentId) {
		Optional<IncidentResponseEntity> incidentResponseEntity = incidentResponseRepository.findById(incidentId);
		if (incidentResponseEntity.isPresent()) {
			String teamIdVsUserIdVsResponseTime = incidentResponseEntity.get().getTeamIdVsUserIdVsResponseTime();
			return convertStringToMap(teamIdVsUserIdVsResponseTime);
		}
		return null;
	}

	private String convertMapIntoString(Map<String, Map<String, Long>> map) {
		ObjectMapper mapperObj = new ObjectMapper();
		try {
			String jsonResp = mapperObj.writeValueAsString(map);
			return jsonResp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Map<String, Map<String, Long>> convertStringToMap(String details) {
		try {
			Map<String, Map<String, Long>> response = new ObjectMapper().readValue(details, HashMap.class);
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
