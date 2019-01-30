package com.hooman.incident.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hooman.incident.entity.Incident;
import com.hooman.incident.entity.IncidentAssignedTeamEntity;

public interface IncidentAssignedTeamRepository extends JpaRepository<IncidentAssignedTeamEntity, String> {
	@Query("SELECT incidentId FROM IncidentAssignedTeamEntity p WHERE p.tenantId = :tenantId and p.teamId = :teamId")
	List<String> getAllIncidentsAssignedToTeam(@Param("tenantId") Integer tenantId, @Param("teamId") String teamId);
}
