package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class IdentificationTypeEntity{
	
	private UUID id;
	private String name;
	
	public IdentificationTypeEntity() {
		setIdentificationTypeId(UUIDHelper.getUUIDHelper().getDefault());
		setIdentificationTypeName(TextHelper.getDefault());
	}
	
	public IdentificationTypeEntity(final UUID id) {
		setIdentificationTypeId(id);
		setIdentificationTypeName(TextHelper.getDefault());
	}
	

	public IdentificationTypeEntity(final UUID id, final String identificationTypeName) {
		setIdentificationTypeId(id);
		setIdentificationTypeName(identificationTypeName);
	}
	
	static IdentificationTypeEntity getDefaultValue() {
		return new IdentificationTypeEntity();
	}
	
	static IdentificationTypeEntity getDefaultValue(final IdentificationTypeEntity 
			identifiactionType) {
		return ObjectHelper.getDefault(identifiactionType, getDefaultValue());
	}

	public String getName() {
		return name;
	}

	public void setIdentificationTypeName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}

	public UUID getIdentificationTypeId() {
		return id;
	}

	public void setIdentificationTypeId(final UUID identificationTypeId) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(identificationTypeId);
	}

}
