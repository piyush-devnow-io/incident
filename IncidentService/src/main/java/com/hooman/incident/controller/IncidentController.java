package com.hooman.incident.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hooman.incident.entity.Incident;
import com.hooman.incident.request.IncidentRequest;
import com.hooman.incident.request.ResponseDetails;
import com.hooman.incident.response.IncidentResponseDetails;
import com.hooman.incident.service.api.IIncidentResponseService;
import com.hooman.incident.service.api.IIncidentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "IncidentController", description = "REST APIs related to Incident Entity!!!!", produces = "application/json")
@RestController
public class IncidentController {

	private static final Logger logger = LoggerFactory.getLogger(IncidentController.class);

	@Autowired
	IIncidentService incidentService;

	@Autowired
	IIncidentResponseService incidentResponseService;

	@ApiOperation(value = "Create new incident", response = Incident.class, tags = "createNewIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/create", method = RequestMethod.POST)

	Incident createNewIncident(@RequestHeader String authenticationToken, @RequestBody IncidentRequest request) {
		logger.info("request received for incident creation");
		return incidentService.createNewIncident(request.getTenantId(), request.getUserId(), request.getSubject(),
				request.getCriteria1(), request.getCriteria2(), request.getCriteria3(), request.getCriteria4(),
				request.getCriteria5(), request.getCriteria6(), request.getCriteria7(), request.getCriteria8(),
				request.getCriteria9(), request.getCriteria10(), request.getAssignedTeamIds());
	}

	@ApiOperation(value = "Get an Incident by incidentid", response = Incident.class, tags = "getIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident", method = RequestMethod.GET)
	Incident getIncident(@RequestHeader String authenticationToken, @RequestParam("tenantId") Integer tenantId,
			@RequestParam("incidentId") String incidentId) {

		logger.info("request to get incident received");
		return incidentService.getIncident(tenantId, incidentId);
	}

	@ApiOperation(value = "Delete an incident by id", response = Void.class, tags = "deleteIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/delete", method = RequestMethod.DELETE)
	void deleteIncident(@RequestHeader String authenticationToken, @RequestParam("tenantId") String tenantId,
			@RequestParam("incidentId") String incidentId) {
		logger.info("request for incident deletion received");
		incidentService.deleteIncident(tenantId, incidentId);
		return;
	}

	@ApiOperation(value = "Update an incident", response = Incident.class, tags = "updateIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/update", method = RequestMethod.POST)

	Incident updateIncident(@RequestHeader String authenticationToken, @RequestParam("incidentId") String incidentId,
			@RequestParam("request") IncidentRequest request) {
		logger.info("request for incident updation received");
		return incidentService.updateIncident(incidentId, request.getTenantId(), request.getUserId(),
				request.getSubject(), request.getCriteria1(), request.getCriteria2(), request.getCriteria3(),
				request.getCriteria4(), request.getCriteria5(), request.getCriteria6(), request.getCriteria7(),
				request.getCriteria8(), request.getCriteria9(), request.getCriteria10(), null);
	}

	@ApiOperation(value = "Get list of Incident in the System ", response = Iterable.class, tags = "getAllIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/getAllIncident", method = RequestMethod.GET)

	List<Incident> getAllIncident(@RequestHeader String authenticationToken,
			@RequestParam("tenantId") Integer tenantId) {
		logger.info("request for get all incident received");
		return incidentService.getAllIncident(tenantId);
	}

	@ApiOperation(value = "Get list of incident assigned to a team ", response = Iterable.class, tags = "getAllIncidentAssignedToTeam")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/getAllIncidentAssignedToTeam", method = RequestMethod.GET)

	List<Incident> getAllIncidentAssignedToTeam(@RequestHeader String authenticationToken,
			@RequestParam("tenantId") Integer tenantId, @RequestParam("teamId") String teamId) {
		logger.info("request to get all incidents assigned to a team received");
		return incidentService.getAllIncidentAssignedToTeam(tenantId, teamId);
	}

	@ApiOperation(value = "get all response time for an incident", response = IncidentResponseDetails.class, tags = "getAllResponseTimeForIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/getAllResponseTimeForIncident", method = RequestMethod.GET)

	IncidentResponseDetails getAllResponseTimeForIncident(@RequestHeader String authenticationToken,
			@RequestParam("tenantId") Integer tenantId, @RequestParam("incidentId") String incidentId) {
		logger.info("request to fetch all responses for a team received");
		return incidentResponseService.getAllResponsesForIncidentId(tenantId, incidentId);
	}

	@ApiOperation(value = "provide response time for an incident", response = Void.class, tags = "provideResponseTimeForIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/provideResponseTime", method = RequestMethod.PUT)

	public void provideResponseTimeForIncident(@RequestHeader String authenticationToken,
			@RequestParam("tenantId") Integer tenantId, @RequestParam("incidentId") String incidentId,
			@RequestBody ResponseDetails responseDetails) {
		logger.info("request to provide response for incident received");
		incidentResponseService.provideResponseForIncident(tenantId, incidentId, responseDetails);

	}

}
