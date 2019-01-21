package com.hooman.incident.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hooman.incident.request.IncidentRequest;
import com.hooman.incident.response.Incident;
import com.hooman.incident.service.api.IIncidentService;

@Controller
@RequestMapping(value = "/incident/")
public class IncidentController {

	@Autowired
	IIncidentService incidentService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	Incident createNewIncident(IncidentRequest request) {
		return incidentService.createNewIncident(request.getTenantId(), request.getUserId(), request.getSubject(),
				request.getCriteria1(), request.getCriteria2(), request.getCriteria3(), request.getCriteria4(),
				request.getCriteria5(), request.getCriteria6(), request.getCriteria7(), request.getCriteria8(),
				request.getCriteria9(), request.getCriteria10(), null);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	Incident getIncident(@RequestParam("tenantId") String tenantId, @RequestParam("incidentId") String incidentId) {
		return incidentService.getIncident(tenantId, incidentId);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	void deleteIncident(@RequestParam("tenantId") String tenantId, @RequestParam("incidentId") String incidentId) {
		incidentService.deleteIncident(tenantId, incidentId);
		return;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	Incident updateIncident(@RequestParam("incidentId") String incidentId,
			@RequestParam("request") IncidentRequest request) {
		return incidentService.updateIncident(incidentId, request.getTenantId(), request.getUserId(),
				request.getSubject(), request.getCriteria1(), request.getCriteria2(), request.getCriteria3(),
				request.getCriteria4(), request.getCriteria5(), request.getCriteria6(), request.getCriteria7(),
				request.getCriteria8(), request.getCriteria9(), request.getCriteria10(), null);
	}

	@RequestMapping(value = "/getAllIncident", method = RequestMethod.GET)
	@ResponseBody
	List<Incident> getAllIncident(@RequestParam("tenantId") String tenantId) {
		return incidentService.getAllIncident(tenantId);
	}

	@RequestMapping(value = "/getAllIncidentAssignedToTeam", method = RequestMethod.GET)
	@ResponseBody
	List<Incident> getAllIncidentAssignedToTeam(@RequestParam("tenantId") String tenantId,
			@RequestParam("teamId") String teamId) {
		return incidentService.getAllIncidentAssignedToTeam(tenantId, teamId);
	}

	@RequestMapping(value = "/getAllResponseTimeForIncident", method = RequestMethod.GET)
	@ResponseBody
	Map<String, Map<String, Long>> getAllResponseTimeForIncident(@RequestParam("tenantId") String tenantId,
			@RequestParam("incidentId") String incidentId) {
		return incidentService.getAllResponseTimeForIncident(incidentId);
	}

}
