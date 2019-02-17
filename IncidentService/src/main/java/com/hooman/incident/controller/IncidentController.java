package com.hooman.incident.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hooman.incident.entity.Incident;
import com.hooman.incident.request.IncidentRequest;
import com.hooman.incident.request.ResponseDetails;
import com.hooman.incident.response.IncidentDetails;
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

//	@PreAuthorize("#oauth2.hasScope('bar') and #oauth2.hasScope('read')")
	@ApiOperation(value = "Create new incident", response = Incident.class, tags = "createNewIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/create", method = RequestMethod.POST)

	Incident createNewIncident(@RequestHeader(name = "Authorization") String authenticationToken,
			@RequestBody IncidentRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.info("request received for incident creation");
		Assert.notNull(request.getTenantId(), "tenantId cannot be null");
		return incidentService.createNewIncident(request.getTenantId(), authentication.getName(), request.getSubject(),
				request.getCriteria1(), request.getCriteria2(), request.getCriteria3(), request.getCriteria4(),
				request.getCriteria5(), request.getCriteria6(), request.getCriteria7(), request.getCriteria8(),
				request.getCriteria9(), request.getCriteria10(), request.getAssignedTeamIds());
	}

	@ApiOperation(value = "Get an Incident by incidentid", response = Incident.class, tags = "getIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident", method = RequestMethod.GET)
	Incident getIncident(@RequestHeader(name = "Authorization") String authenticationToken,
			@RequestParam("tenantId") Integer tenantId, @RequestParam("incidentId") String incidentId) {
		// Assert.notNull(tenantId, "tenantId cannot be null");
		Assert.notNull(incidentId, "incidentId cannot be null");
		logger.info("request to get incident received");
		return incidentService.getIncident(tenantId, incidentId);
	}

	@ApiOperation(value = "Delete an incident by id", response = Void.class, tags = "deleteIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/delete", method = RequestMethod.DELETE)
	void deleteIncident(@RequestHeader(name = "Authorization") String authenticationToken,
			@RequestParam("tenantId") String tenantId, @RequestParam("incidentId") String incidentId) {
		logger.info("request for incident deletion received");
		// Assert.notNull(tenantId, "tenantId cannot be null");
		Assert.notNull(incidentId, "incidentId cannot be null");
		incidentService.deleteIncident(tenantId, incidentId);
		return;
	}

	@ApiOperation(value = "Update an incident", response = Incident.class, tags = "updateIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/update", method = RequestMethod.POST)

	Incident updateIncident(@RequestHeader(name = "Authorization") String authenticationToken,
			@RequestParam("incidentId") String incidentId, @RequestParam("request") IncidentRequest request) {
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

	List<Incident> getAllIncident(@RequestHeader(name = "Authorization") String authenticationToken,
			@RequestParam("tenantId") Integer tenantId) {
		logger.info("request for get all incident received");
		// Assert.notNull(tenantId, "tenantId cannot be null");
		return incidentService.getAllIncident(tenantId);
	}

	@ApiOperation(value = "Get list of incident assigned to a team ", response = Iterable.class, tags = "getAllIncidentAssignedToTeam")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/getAllIncidentAssignedToTeam", method = RequestMethod.GET)
	List<IncidentDetails> getAllIncidentAssignedToTeam(
			@RequestHeader(name = "Authorization") String authenticationToken,
			@RequestParam("tenantId") Integer tenantId, @RequestParam("teamId") String teamId) {
		logger.info("request to get all incidents assigned to a team received");
		// Assert.notNull(tenantId, "incidentId cannot be null");
		Assert.notNull(teamId, "incidentId cannot be null");
		return incidentService.getAllIncidentAssignedToTeam(tenantId, teamId);
	}

	@ApiOperation(value = "get all response time for an incident", response = IncidentResponseDetails.class, tags = "getAllResponseTimeForIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/getAllResponseTimeForIncident", method = RequestMethod.GET)
	List<IncidentResponseDetails> getAllResponseTimeForIncident(
			@RequestHeader(name = "Authorization") String authenticationToken,
			@RequestParam("tenantId") Integer tenantId, @RequestParam("incidentId") List<String> incidentIds) {
		logger.info("request to fetch all responses for a team received");
		Assert.notNull(incidentIds, "incidentId cannot be null");
		// Assert.notNull(tenantId, "tenantId cannot be null");
		return incidentResponseService.getAllResponsesForIncidentId(tenantId, incidentIds);
	}

	@ApiOperation(value = "provide response time for an incident", response = Void.class, tags = "provideResponseTimeForIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/provideResponseTime", method = RequestMethod.PUT)
	public void provideResponseTimeForIncident(@RequestHeader(name = "Authorization") String authenticationToken,
			@RequestParam("tenantId") Integer tenantId, @RequestParam("incidentId") String incidentId,
			@RequestBody ResponseDetails responseDetails) {
		Assert.notNull(incidentId, "incidentId cannot be null");
		// Assert.notNull(tenantId, "tenantId cannot be null");
		Assert.notNull(responseDetails.getUserId(), "userId cannot be null");
		Assert.notNull(responseDetails.getTime(), "time cannot be null");
		Assert.notNull(responseDetails.getTeamId(), "teamId cannot be null");
		logger.info("request to provide response for incident received");
		incidentResponseService.provideResponseForIncident(tenantId, incidentId, responseDetails);

	}

	@ApiOperation(value = "provide all responses time for a list of incidents", response = Map.class, tags = "getAllResponsesForIncidentIdByUserIdTeamId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/getAllResponsesForIncidentIdByUserIdTeamId", method = RequestMethod.GET)
	public Map<String, Integer> getAllIncidentResponsesByUser(Integer tenantId, String userId, String teamId,
			List<String> listOfIncidentIds) {
		return incidentResponseService.getAllResponsesForIncidentIdByUserIdTeamId(tenantId, userId, teamId,
				listOfIncidentIds);

	}
}
