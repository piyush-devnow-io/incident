package com.hooman.incident.incidentidentity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class IncidentIdentity {

	@NotNull
	@Column(name = "incident_id")
	String incidentId;

	@NotNull
	@Column(name = "tenant_id")
	Integer tenantId;

	public IncidentIdentity() {
		super();
	}

	public IncidentIdentity(@NotNull String incidentId, @NotNull Integer tenantId) {
		super();
		this.incidentId = incidentId;
		this.tenantId = tenantId;
	}

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		IncidentIdentity that = (IncidentIdentity) o;

		if (!incidentId.equals(that.incidentId))
			return false;
		return tenantId.equals(that.tenantId);
	}

	@Override
	public int hashCode() {
		int result = incidentId.hashCode();
		result = 31 * result + tenantId.hashCode();
		return result;
	}

}
