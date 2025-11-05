package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class IdentificationTypeDomain extends Domain {
	
	private String name;
	
	IdentificationTypeDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setIdentificationTypeName(TextHelper.getDefault());
	}
	
	public IdentificationTypeDomain(final UUID id) {
		super(id);
		setIdentificationTypeName(TextHelper.getDefault());
	}
	

	public IdentificationTypeDomain(final UUID id, final String identificationTypeName) {
		super(id);
		setIdentificationTypeName(identificationTypeName);
	}
	
	protected static IdentificationTypeDomain getDefaultValue() {
		return new IdentificationTypeDomain();
	}
	
	static IdentificationTypeDomain getDefaultValue(final IdentificationTypeDomain 
			identifiactionType) {
		return ObjectHelper.getDefault(identifiactionType, getDefaultValue());
	}

	public String getIdentificationTypeName() {
		return name;
	}

	public void setIdentificationTypeName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}
	
}
