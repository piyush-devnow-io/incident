package com.hooman.incident.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hooman.incident.entity.Incident;
import com.hooman.incident.push.notification.HeaderRequestInterceptor;
import com.hooman.incident.request.IncidentRequest;
import com.hooman.incident.request.ResponseDetails;
import com.hooman.incident.response.IncidentDetails;
import com.hooman.incident.response.IncidentResponseData;
import com.hooman.incident.response.IncidentResponseDetails;
import com.hooman.incident.response.Team;
import com.hooman.incident.response.TeamIncidentResponseData;
import com.hooman.incident.response.User;
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

	List<IncidentDetails> getAllIncident(@RequestHeader String authenticationToken,
			@RequestParam("tenantId") Integer tenantId) {
		logger.info("request for get all incident received");
		// Assert.notNull(tenantId, "tenantId cannot be null");
		List<Incident> allIncident = incidentService.getAllIncident(tenantId);
		List<IncidentDetails> allResponse = new ArrayList<>();
		Map<String, List<String>> incidentIdVsAssignedTeamIdList = incidentService.getIncidentIdVsAssignedTeamIdList();
		for (Incident in : allIncident) {
			IncidentDetails detail = convertIncidentToIncidentDetail(in);
			List<String> list = incidentIdVsAssignedTeamIdList.get(detail.getIncidentId());
			if (list != null) {
				detail.setAssignedTeamId(list.get(0));
			}
			allResponse.add(detail);
		}
		return allResponse;
	}

	private IncidentDetails convertIncidentToIncidentDetail(Incident in) {
		IncidentDetails inc = new IncidentDetails(in.getIncidentId(), in.getTenantId(), in.getUserId(), in.getSubject(),
				in.getCriteria1(), in.getCriteria2(), in.getCriteria3(), in.getCriteria4(), in.getCriteria5(),
				in.getCriteria6(), in.getCriteria7(), in.getCriteria8(), in.getCriteria9(), in.getCriteria10(), null,
				in.getDateAdded().getTime(), in.getDateUpdated().getTime());
		// TODO Auto-generated method stub
		return inc;
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

	@ApiOperation(value = "get all incidents assigned to list of teamIds", response = Iterable.class, tags = "getAllIncidentAssignedToTeams")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/getAllIncidentAssignedToTeams", method = RequestMethod.GET)
	TeamIncidentResponseData getAllIncidentAssignedToTeams(
			@RequestHeader(name = "Authorization") String authenticationToken,
			@RequestParam("tenantId") Integer tenantId) {
		logger.info("request to get all incidents assigned to a team received");
		// Assert.notNull(tenantId, "incidentId cannot be null");
//		Assert.notNull(teamIds, "list cannot be null");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<String> teamIds = getAllTeamsOfUser(authentication.getName(), tenantId);

		TeamIncidentResponseData data = new TeamIncidentResponseData();
		if (data.getTeamIdVsListOfIncidentDetails() == null) {
			data.setTeamIdVsListOfIncidentDetails(new HashMap<String, List<IncidentDetails>>());
		}
		for (String teamId : teamIds) {
			List<IncidentDetails> allIncidentAssignedToTeam = incidentService.getAllIncidentAssignedToTeam(tenantId,
					teamId);
			data.getTeamIdVsListOfIncidentDetails().put(teamId, allIncidentAssignedToTeam);
		}
		return data;
	}

	@ApiOperation(value = "get all response time for an incident", response = IncidentResponseData.class, tags = "getAllResponseTimeForIncident")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/incident/getAllResponseTimeForIncident", method = RequestMethod.GET)
	List<IncidentResponseData> getAllResponseTimeForIncident(@RequestHeader String authenticationToken,
			@RequestParam("tenantId") Integer tenantId, @RequestParam("incidentId") List<String> incidentIds) {
		logger.info("request to fetch all responses for a team received");
		Assert.notNull(incidentIds, "incidentId cannot be null");
		// Assert.notNull(tenantId, "tenantId cannot be null");
		List<IncidentResponseDetails> allResponsesForIncidentId = incidentResponseService
				.getAllResponsesForIncidentId(tenantId, incidentIds);
		/**
		 * this logic is not good, just for the jugaad of teammates
		 */
		List<IncidentResponseData> data = new ArrayList<>();
		for (IncidentResponseDetails response : allResponsesForIncidentId) {
			IncidentResponseData bean = new IncidentResponseData();
			bean.setIncidentId(response.getIncidentId());
			bean.setTenantId(response.getTenantId());
			Map<String, Map<String, Integer>> responseDetail = response.getResponse();
			if (responseDetail != null) {
				for (Map.Entry<String, Map<String, Integer>> entrySet : responseDetail.entrySet()) {
					String teamId = entrySet.getKey();
					Map<String, Integer> userIdVSResponseTime = entrySet.getValue();
					for (Map.Entry<String, Integer> entry : userIdVSResponseTime.entrySet()) {
						String userId = entry.getKey();
						Integer responseTime = entry.getValue();
						bean.setUserId(userId);
						bean.setResponseTime(responseTime);
					}
				}
			}
			data.add(bean);
		}
		return data;
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

	private List<String> getAllTeamsOfUser(String userId, Integer tenantId) {
		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODI3NDI4NTksInVzZXJfbmFtZSI6InNodWJoYW1AZGV2bm93LmlvIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJlM2Q3ZDdhNi03ZDcxLTQwNzMtOWY5YS1iNzJiODE0MWJiOGIiLCJjbGllbnRfaWQiOiJ3ZWJDbGllbnRJZFBhc3N3b3JkIiwic2NvcGUiOlsiaW5jaWRlbnQiLCJyZWFkIiwidGVhbSIsInVzZXIiLCJ3cml0ZSJdfQ.uLWaMJT7k4fuxVp3hJyXCB5OWiInHxCT7N_glWMuQN4"));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		interceptors.add(new HeaderRequestInterceptor("Tenant-Id", tenantId + ""));
		restTemplate.setInterceptors(interceptors);

		Map<String, Object> params = new HashMap<>();
		params.put("id", userId);
		ResponseEntity<User> user = restTemplate.getForEntity("http://3.90.159.103:8081/UserManagement/user/{id}",
				User.class, params);

		List<String> teams = new ArrayList<>();
		for (Team team : user.getBody().getTeams()) {
			teams.add(team.getId().toString());
		}

		return teams;
	}
}
