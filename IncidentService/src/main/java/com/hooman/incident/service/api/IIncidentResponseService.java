package com.hooman.incident.service.api;

import java.util.List;
import java.util.Map;

import com.hooman.incident.incidentidentity.IncidentResponseEntity;
import com.hooman.incident.request.ResponseDetails;

public interface IIncidentResponseService {
	public void provideResponseForIncident(Integer tenantId, String incidentId, ResponseDetails responseDetails);


	IncidentResponseEntity getAllResponsesForIncidentId(Integer tenantId, String incidentId);



}
