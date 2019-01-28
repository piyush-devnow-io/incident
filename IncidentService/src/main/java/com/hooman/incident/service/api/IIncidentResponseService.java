package com.hooman.incident.service.api;

import com.hooman.incident.entity.IncidentResponseEntity;
import com.hooman.incident.request.ResponseDetails;

public interface IIncidentResponseService {
	public void provideResponseForIncident(Integer tenantId, String incidentId, ResponseDetails responseDetails);


	IncidentResponseEntity getAllResponsesForIncidentId(Integer tenantId, String incidentId);



}
