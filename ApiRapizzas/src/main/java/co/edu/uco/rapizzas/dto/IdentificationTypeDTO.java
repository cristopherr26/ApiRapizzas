package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class IdentificationTypeDTO {
	
	private UUID id;
	private String name;
	
	public IdentificationTypeDTO() {
		setIdentificationTypeId(UUIDHelper.getUUIDHelper().getDefault());
		setIdentificationTypeName(TextHelper.getDefault());
	}
	
	public IdentificationTypeDTO(final UUID identificationTypeId) {
		setIdentificationTypeId(identificationTypeId);
		setIdentificationTypeName(TextHelper.getDefault());
	}
	
	public IdentificationTypeDTO(final UUID identificationTypeId, final String identificationTypeName) {
		setIdentificationTypeId(identificationTypeId);
		setIdentificationTypeName(identificationTypeName);
	}
	
	static IdentificationTypeDTO getDefaultValue() {
		return new IdentificationTypeDTO();
	}
	
	static IdentificationTypeDTO getDefaultValue(final IdentificationTypeDTO identificationType) {
		return ObjectHelper.getDefault(identificationType, getDefaultValue());
	}
	
	public UUID getIdentificationTypeId() {
		return id;
	}
	
	public void setIdentificationTypeId(final UUID identificationTypeId) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(identificationTypeId);
	}
	
	public String getIdentificationTypeName() {
		return name;
	}
	
	public void setIdentificationTypeName(final String identificationTypeName) {
		this.name = TextHelper.getDefaultWithTrim(identificationTypeName);
	}
	
}
