package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class StatusDomain extends Domain{
	
	private String statusName;
	
	StatusDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setStatusName(TextHelper.getDefault());
	}

	public StatusDomain(final UUID id) {
		super(id);
		setStatusName(TextHelper.getDefault());
	}

	public StatusDomain(final UUID id, final String statusName) {
		super(id);
		setStatusName(statusName);
	}
	
	protected static StatusDomain getDefaultValue() {
		return new StatusDomain();
	}
	
	static StatusDomain getDefaultValue(final StatusDomain status) {
		return ObjectHelper.getDefault(status, getDefaultValue());
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(final String statusName) {
		this.statusName = TextHelper.getDefaultWithTrim(statusName);
	}

}
