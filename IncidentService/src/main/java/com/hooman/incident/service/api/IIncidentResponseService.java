package com.hooman.incident.service.api;

import com.hooman.incident.request.ResponseDetails;
import com.hooman.incident.response.IncidentResponseDetails;

public interface IIncidentResponseService {
	public void provideResponseForIncident(Integer tenantId, String incidentId, ResponseDetails responseDetails);


	IncidentResponseDetails getAllResponsesForIncidentId(Integer tenantId, String incidentId);



}
