package com.hooman.incident.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hooman.incident.entity.Incident;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, String> {

	@Query("SELECT p FROM Incident p WHERE p.tenantId = :tenantId")
	List<Incident> findByIncidentTenantId(Integer tenantId);

	
//	@Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
//	String getAllResponseTimeForIncident(@Param("tenantId") String tenantId, @Param("incidentId") String incidentId);

}
