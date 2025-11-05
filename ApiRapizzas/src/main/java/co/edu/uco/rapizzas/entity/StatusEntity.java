package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class StatusEntity{
	
	private UUID id;
	private String name;
	
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
		return name;
	}

	public void setStatusName(final String statusName) {
		this.name = TextHelper.getDefaultWithTrim(statusName);
	}

	public UUID getStatusId() {
		return id;
	}

	public void setStatusId(final UUID statusId) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(statusId);
	}
	
}
