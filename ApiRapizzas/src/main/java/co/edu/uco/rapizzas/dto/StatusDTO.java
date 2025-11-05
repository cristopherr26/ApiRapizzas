package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class StatusDTO {
	
	private UUID id;
	private String name;
	
	public StatusDTO() {
		setStatusId(UUIDHelper.getUUIDHelper().getDefault());
		setStatusName(TextHelper.getDefault());
	}
	
	public StatusDTO(final UUID statusId) {
		setStatusId(statusId);
		setStatusName(TextHelper.getDefault());
	}
	
	public StatusDTO(final UUID statusId, final String statusName) {
		setStatusId(statusId);
		setStatusName(statusName);
	}
	
	static StatusDTO getDefaultValue() {
		return new StatusDTO();
	}
	
	static StatusDTO getDefaultValue(final StatusDTO status) {
		return ObjectHelper.getDefault(status, getDefaultValue());
	}
	
	public UUID getStatusId() {
		return id;
	}
	
	public void setStatusId(final UUID statusId) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(statusId);
	}
	
	public String getStatusName() {
		return name;
	}
	
	public void setStatusName(final String statusName) {
		this.name = TextHelper.getDefaultWithTrim(statusName);
	}
	
}
