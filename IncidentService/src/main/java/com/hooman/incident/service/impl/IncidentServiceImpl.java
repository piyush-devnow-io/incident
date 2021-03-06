package com.hooman.incident.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hooman.incident.entity.Incident;
import com.hooman.incident.entity.IncidentAssignedTeamEntity;
import com.hooman.incident.push.notification.HeaderRequestInterceptor;
import com.hooman.incident.push.notification.NotificationSender;
import com.hooman.incident.response.IncidentDetails;
import com.hooman.incident.service.api.IIncidentService;
import com.hooman.incident.service.repository.IncidentAssignedTeamRepository;
import com.hooman.incident.service.repository.IncidentRepository;
import com.hooman.incident.service.repository.IncidentResponseRepository;
import com.hooman.incident.utils.IncidentUtils;

@Service
public class IncidentServiceImpl implements IIncidentService {

	@Autowired
	IncidentRepository incidentRepository;

	@Autowired
	NotificationSender notificationSender;

	@Autowired
	IncidentResponseRepository incidentResponseRepository;

	@Autowired
	IncidentAssignedTeamRepository incidentAssignedTeamRepository;

	private static final Logger logger = LoggerFactory.getLogger(IncidentServiceImpl.class);

	@Override
	public Incident createNewIncident(Integer tenantId, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds) {
		// perform validation
		String incidentId = IncidentUtils.getIncidentId();
		Incident incident = getNewIncident(tenantId, incidentId, userId, subject, criteria1, criteria2, criteria3,
				criteria4, criteria5, criteria6, criteria7, criteria8, criteria9, criteria10, assignedTeamIds);
		Incident savedIncident = incidentRepository.save(incident);
		for (String teamId : assignedTeamIds) {
			IncidentAssignedTeamEntity entity = new IncidentAssignedTeamEntity();
			entity.setIncidentId(incidentId);
			entity.setTenantId(tenantId);
			entity.setTeamId(teamId);
			incidentAssignedTeamRepository.save(entity);
			try {
				sendAssignmentNotification(incidentId, tenantId, teamId, criteria1);
			} catch (Exception e) {
				logger.error("error while sending notification " + e.getMessage());
				e.printStackTrace();
			}
		}

		return savedIncident;
	}

	@Override
	public Incident getIncident(Integer tenantId, String incidentId) {
		return incidentRepository.getOne(incidentId);
	}

	@Override
	public void deleteIncident(String tenantId, String incidentId) {
		incidentRepository.deleteById(incidentId);
		incidentAssignedTeamRepository.deleteById(incidentId);
		return;
	}

	@Override
	public Incident updateIncident(String incidentId, Integer tenantId, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds) {
		Incident incident = incidentRepository.getOne(incidentId);
		Incident updatedIncident = updateIncident(incident, userId, subject, criteria1, criteria2, criteria3, criteria4,
				criteria5, criteria6, criteria7, criteria8, criteria9, criteria10, assignedTeamIds);
		return incidentRepository.save(incident);
	}

	private Incident updateIncident(Incident incident, String userId, String subject, String criteria1,
			String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7,
			String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds) {
		incident.setUserId(userId);
		incident.setSubject(subject);
		incident.setCriteria1(criteria1);
		incident.setCriteria2(criteria2);
		incident.setCriteria3(criteria3);
		incident.setCriteria4(criteria4);
		incident.setCriteria5(criteria5);
		incident.setCriteria6(criteria6);
		incident.setCriteria7(criteria7);
		incident.setCriteria8(criteria8);
		incident.setCriteria9(criteria9);
//		incident.getIncidentAssignedTeamEntity().getListOfTeamIds().addAll(assignedTeamIds);
		return incident;
	}

	@Override
	public List<Incident> getAllIncident(Integer tenantId) {
		return incidentRepository.findByIncidentTenantId(tenantId);
	}

	@Override
	public List<IncidentDetails> getAllIncidentAssignedToTeam(Integer tenantId, String teamId) {
		List<String> allIncidentsAssignedToTeam = incidentAssignedTeamRepository.getAllIncidentsAssignedToTeam(tenantId,
				teamId);
		List<Incident> findAllById = incidentRepository.findAllById(allIncidentsAssignedToTeam);
//		List<IncidentResponseDetails> allIncidentResponsesByUser = getAllIncidentResponsesByUser(tenantId,
//				allIncidentsAssignedToTeam, userId);
		List<IncidentDetails> incidentDetails = convertListOfIncidentToIncidentDetails(findAllById, teamId);
		Collections.sort(incidentDetails);
//		Collections.sort(incidentDetails, new Comparator<IncidentDetails>() {
//
//			@Override
//			public int compare(IncidentDetails o1, IncidentDetails o2) {
//				if (o1.getDateCreated() > o2.getDateCreated()) {
//					return 1;
//				} else {
//					return 0;
//				}
//			}
//		});
		return incidentDetails;
	}

	private List<IncidentDetails> convertListOfIncidentToIncidentDetails(List<Incident> findAllById, String teamId) {
		List<IncidentDetails> list = new ArrayList<>();
		for (Incident incident : findAllById) {
			list.add(new IncidentDetails(incident.getIncidentId(), incident.getTenantId(), incident.getUserId(),
					incident.getSubject(), incident.getCriteria1(), incident.getCriteria2(), incident.getCriteria3(),
					incident.getCriteria4(), incident.getCriteria5(), incident.getCriteria6(), incident.getCriteria7(),
					incident.getCriteria8(), incident.getCriteria9(), incident.getCriteria10(), teamId,
					incident.getDateAdded().getTime(), incident.getDateUpdated().getTime()));
		}
		return list;
	}

//	@Override
//	public List<IncidentResponseDetails> getAllIncidentResponsesByUser(Integer tenantId, List<String> listOfIncidentIds,
//			String userId) {
//		List<IncidentResponseEntity> findAllById = incidentResponseRepository.findAllById(listOfIncidentIds);
//		List<IncidentResponseDetails> list = new ArrayList<>();
//		for (IncidentResponseEntity entity : findAllById) {
//			list.add(incidentResponseRepository.fi)
//		}
//		return null;
//	}

	private Incident getNewIncident(Integer tenantId, String incidentId, String userId, String subject,
			String criteria1, String criteria2, String criteria3, String criteria4, String criteria5, String criteria6,
			String criteria7, String criteria8, String criteria9, String criteria10, List<String> assignedTeamIds) {
		Set<IncidentAssignedTeamEntity> set = new HashSet<>();
		IncidentAssignedTeamEntity assignedTeamEntity = new IncidentAssignedTeamEntity();
//		assignedTeamEntity.setIncidentId(incidentId);
//		assignedTeamEntity.setTenantId(tenantId);
		assignedTeamEntity.setTeamId("1");
		IncidentAssignedTeamEntity assignedTeamEntity2 = new IncidentAssignedTeamEntity();
//		assignedTeamEntity2.setIncidentId(incidentId);
//		assignedTeamEntity2.setTenantId(tenantId);
		assignedTeamEntity2.setTeamId("1");
		set.add(assignedTeamEntity);
		set.add(assignedTeamEntity2);

//		assignedTeamEntity.setId(identity.getIncidentId());
		Incident incident = new Incident(incidentId, tenantId, userId, subject, criteria1, criteria2, criteria3,
				criteria4, criteria5, criteria6, criteria7, criteria8, criteria9, criteria10, new Date(), new Date());
//		assignedTeamEntity.setIncident(incident);
//		assignedTeamEntity2.setIncident(incident);
		return incident;
	}

	@Override
	public Map<String, List<String>> getIncidentIdVsAssignedTeamIdList() {
		Map<String, List<String>> map = new HashMap<>();
		List<IncidentAssignedTeamEntity> findAll = incidentAssignedTeamRepository.findAll();
		for (IncidentAssignedTeamEntity entity : findAll) {
			String incidentId = entity.getIncidentId();
			String teamId = entity.getTeamId();
			List<String> list = map.get(incidentId);
			if (list == null) {
				list = new ArrayList<>();
			}
			list.add(teamId);
			map.put(incidentId, list);
		}
		return map;
	}

	private void sendAssignmentNotification(String incidentId, Integer tenantId, String teamId, String criteria1)
			throws Exception {
		logger.info("sending assignment notification to teamId" + teamId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("incidentId", incidentId);
		map.put("teamId", teamId);
		map.put("message", criteria1);
		logger.info("going to get all users for team" + teamId);
		@SuppressWarnings("unchecked")
		List users = getAllUsersOfTeam(teamId, tenantId);
		logger.info("going to iterate users");
		for (Object user : users) {
			if (user != null && !"".equalsIgnoreCase((String) user)) {
				List<Token> tokens = getAppTokens((String) user, tenantId);
				for (Token token : tokens) {
					logger.info("sending notification for token " + ((Token) token).getToken());
					notificationSender.send("IncidentAssigned", incidentId, map, ((Token) token).getToken(),
							((Token) token).getType());
				}
			}
		}
	}

	/**
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Token> getAppTokens(String userId, Integer tenantId) {
		logger.info("going to get apptokens for userId " + userId + " and tenand Id " + tenantId);
		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODI3NDI4NTksInVzZXJfbmFtZSI6InNodWJoYW1AZGV2bm93LmlvIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJlM2Q3ZDdhNi03ZDcxLTQwNzMtOWY5YS1iNzJiODE0MWJiOGIiLCJjbGllbnRfaWQiOiJ3ZWJDbGllbnRJZFBhc3N3b3JkIiwic2NvcGUiOlsiaW5jaWRlbnQiLCJyZWFkIiwidGVhbSIsInVzZXIiLCJ3cml0ZSJdfQ.uLWaMJT7k4fuxVp3hJyXCB5OWiInHxCT7N_glWMuQN4"));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		interceptors.add(new HeaderRequestInterceptor("Tenant-Id", tenantId + ""));
		restTemplate.setInterceptors(interceptors);

		Map<String, Object> params = new HashMap<>();
		params.put("username", userId);
		System.out.println("going for username " + userId);
		ResponseEntity<List<Token>> userTokens = restTemplate.exchange(
				"http://3.90.159.103:8081/UserManagement/user/{username}/getAppTokens", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Token>>() {
				}, params);
		logger.info("list of tokens is " + userTokens.getBody());
		return userTokens.getBody();
	}

	private List getAllUsersOfTeam(String teamId, Integer tenantId) {
		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODI3NDI4NTksInVzZXJfbmFtZSI6InNodWJoYW1AZGV2bm93LmlvIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJlM2Q3ZDdhNi03ZDcxLTQwNzMtOWY5YS1iNzJiODE0MWJiOGIiLCJjbGllbnRfaWQiOiJ3ZWJDbGllbnRJZFBhc3N3b3JkIiwic2NvcGUiOlsiaW5jaWRlbnQiLCJyZWFkIiwidGVhbSIsInVzZXIiLCJ3cml0ZSJdfQ.uLWaMJT7k4fuxVp3hJyXCB5OWiInHxCT7N_glWMuQN4"));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		interceptors.add(new HeaderRequestInterceptor("Tenant-Id", tenantId + ""));
		restTemplate.setInterceptors(interceptors);

		Map<String, Object> params = new HashMap<>();
		params.put("id", teamId);
		ResponseEntity<List> usernames = restTemplate
				.getForEntity("http://localhost:8081/UserManagement/team/{id}/getAllUsernames", List.class, params);
		logger.info("all users are  " + usernames.getBody());
		return usernames.getBody();
	}

}
