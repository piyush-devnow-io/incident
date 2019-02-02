package com.hooman.incident.service.api;

import java.util.List;
import java.util.Map;

import com.hooman.incident.request.ResponseDetails;
import com.hooman.incident.response.IncidentResponseDetails;

public interface IIncidentResponseService {
	public void provideResponseForIncident(Integer tenantId, String incidentId, ResponseDetails responseDetails);




	List<IncidentResponseDetails> getAllResponsesForIncidentId(Integer tenantId, List<String> incidentId);




	public Map<String, Integer> getAllResponsesForIncidentIdByUserIdTeamId(Integer tenantId, String userId,
			String teamId, List<String> listOfIncidentIds);



}
