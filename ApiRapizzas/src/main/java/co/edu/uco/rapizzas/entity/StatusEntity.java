package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class StatusEntity{
	
	private UUID statusId;
	private String statusName;
	
	public StatusEntity() {
		setStatusId(UUIDHelper.getUUIDHelper().getDefault());
		setStatusName(TextHelper.getDefault());
	}

	public StatusEntity(final UUID id) {
		setStatusId(id);
		setStatusName(TextHelper.getDefault());
	}

	public StatusEntity(final UUID id, final String statusName) {
		setStatusId(id);
		setStatusName(statusName);
	}
	
	static StatusEntity getDefaultValue() {
		return new StatusEntity();
	}
	
	static StatusEntity getDefaultValue(final StatusEntity status) {
		return ObjectHelper.getDefault(status, getDefaultValue());
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(final String statusName) {
		this.statusName = TextHelper.getDefaultWithTrim(statusName);
	}

	public UUID getStatusId() {
		return statusId;
	}

	public void setStatusId(final UUID statusId) {
		this.statusId = UUIDHelper.getUUIDHelper().getDefault(statusId);
	}
	
	

}
