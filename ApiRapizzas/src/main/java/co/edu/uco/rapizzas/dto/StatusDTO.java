package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class StatusDTO {
	
	private UUID statusId;
	private String statusName;
	
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
		return statusId;
	}
	
	public void setStatusId(final UUID statusId) {
		this.statusId = ObjectHelper.getDefault(statusId, UUIDHelper.getUUIDHelper().getDefault());
	}
	
	public String getStatusName() {
		return statusName;
	}
	
	public void setStatusName(final String statusName) {
		this.statusName = TextHelper.getDefaultWithTrim(statusName);
	}
	
	

}
