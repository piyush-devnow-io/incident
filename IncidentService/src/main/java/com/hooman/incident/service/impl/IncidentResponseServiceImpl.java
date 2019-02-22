package com.hooman.incident.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		incidentResponseRepository.deleteById(incidentId);
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
			teamIdVSUserIdVsResponseTime.put(teamId, map);
			entity = existingResponse.get();
//			entity.setId(existingResponse.get().getId());
		}
		String convertMapIntoString = convertMapIntoString(teamIdVSUserIdVsResponseTime);
		entity.setIncidentId(incidentId);
		entity.setTenantId(tenantId);
		entity.setTeamIdVsUserIdVsResponseTime(convertMapIntoString);
		incidentResponseRepository.save(entity);
	}

	@Override
	public List<IncidentResponseDetails> getAllResponsesForIncidentId(Integer tenantId, List<String> incidentId) {
		List<IncidentResponseEntity> incidentResponseEntityList = incidentResponseRepository.findAllById(incidentId);
		List<IncidentResponseDetails> list = new ArrayList<>();
		for (IncidentResponseEntity entity : incidentResponseEntityList) {
			IncidentResponseDetails response = new IncidentResponseDetails();
			response.setTenantId(tenantId);
			response.setIncidentId(entity.getIncidentId());
			String teamIdVsUserIdVsResponseTime = entity.getTeamIdVsUserIdVsResponseTime();
			response.setResponse(convertStringToMap(teamIdVsUserIdVsResponseTime));
			list.add(response);
		}
		return list;
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

	@Override
	public Map<String, Integer> getAllResponsesForIncidentIdByUserIdTeamId(Integer tenantId, String userId, String teamId,
			List<String> listOfIncidentIds) {
		List<IncidentResponseDetails> allResponsesDetails = getAllResponsesForIncidentId(tenantId, listOfIncidentIds);
		Map<String, Integer> userIdVsResponseTimeMap = new HashMap<>();
		for (IncidentResponseDetails responseDetails : allResponsesDetails) {
			String incidentId = responseDetails.getIncidentId();
			Map<String, Map<String, Integer>> response = responseDetails.getResponse();
			if (response.get(teamId) != null) {
				if (response.get(teamId).get(userId) != null) {
					userIdVsResponseTimeMap.put(incidentId, response.get(teamId).get(userId));
				}
			}
		}
		return userIdVsResponseTimeMap;
	}

}
