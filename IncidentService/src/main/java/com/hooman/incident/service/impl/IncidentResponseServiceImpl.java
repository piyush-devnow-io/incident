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
import com.hooman.incident.response.IncidentResponseDetails;
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
		Map<String, Map<String, Integer>> teamIdVSUserIdVsResponseTime = null;
		if (!existingResponse.isPresent()) {
			teamIdVSUserIdVsResponseTime = new HashMap<String, Map<String, Integer>>();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put(userId, time);
			teamIdVSUserIdVsResponseTime.put(teamId, map);
			entity = new IncidentResponseEntity();
		} else {
			String data = existingResponse.get().getTeamIdVsUserIdVsResponseTime();
			teamIdVSUserIdVsResponseTime = convertStringToMap(data);
			Map<String, Integer> map = teamIdVSUserIdVsResponseTime.get(teamId);
			if (map == null) {
				map = new HashMap<String, Integer>();
			}
			map.put(userId, time);
//			entity.setId(existingResponse.get().getId());
		}
		String convertMapIntoString = convertMapIntoString(teamIdVSUserIdVsResponseTime);
		entity.setIncidentId(incidentId);
		entity.setTenantId(tenantId);
		entity.setTeamIdVsUserIdVsResponseTime(convertMapIntoString);
		incidentResponseRepository.save(entity);
	}

	@Override
	public IncidentResponseDetails getAllResponsesForIncidentId(Integer tenantId, String incidentId) {
		Optional<IncidentResponseEntity> incidentResponseEntity = incidentResponseRepository.findById(incidentId);
		if (incidentResponseEntity.isPresent()) {
			IncidentResponseDetails response = new IncidentResponseDetails();
			response.setTenantId(tenantId);
			response.setIncidentId(incidentId);
			String teamIdVsUserIdVsResponseTime = incidentResponseEntity.get().getTeamIdVsUserIdVsResponseTime();
			response.setResponse(convertStringToMap(teamIdVsUserIdVsResponseTime));
			return response;
		}
		return null;
	}

	private String convertMapIntoString(Map<String, Map<String, Integer>> map) {
		ObjectMapper mapperObj = new ObjectMapper();
		try {
			String jsonResp = mapperObj.writeValueAsString(map);
			return jsonResp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Map<String, Map<String, Integer>> convertStringToMap(String details) {
		try {
			Map<String, Map<String, Integer>> response = new ObjectMapper().readValue(details, HashMap.class);
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
