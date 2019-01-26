package com.hooman.incident.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hooman.incident.incidentidentity.IncidentIdentity;
import com.hooman.incident.incidentidentity.IncidentResponseEntity;

public interface IncidentResponseRepository extends JpaRepository<IncidentResponseEntity, IncidentIdentity>{


}
