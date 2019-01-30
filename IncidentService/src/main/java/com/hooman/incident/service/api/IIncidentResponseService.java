package com.hooman.incident.service.api;

import java.util.Map;

import com.hooman.incident.entity.IncidentResponseEntity;
import com.hooman.incident.request.ResponseDetails;

public interface IIncidentResponseService {
	public void provideResponseForIncident(Integer tenantId, String incidentId, ResponseDetails responseDetails);


	Map<String, Map<String, Long>> getAllResponsesForIncidentId(Integer tenantId, String incidentId);



}
