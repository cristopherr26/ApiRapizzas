package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.BooleanHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class CustomerEntity {
	
	private UUID customerId;
	private String name;
	private String lastName;
	private boolean isActive;
	private IdentificationTypeEntity identificationType;
	private String identificationNumber;
	
	public CustomerEntity() {
		setCustomerId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(BooleanHelper.getDefault());
		setIdentificationType(IdentificationTypeEntity.getDefaultValue());;
		setIdentificationNumber(TextHelper.getDefault());
	}
	
	public CustomerEntity(final UUID id) {
		setCustomerId(id);
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(BooleanHelper.getDefault());
		setIdentificationType(IdentificationTypeEntity.getDefaultValue());;
		setIdentificationNumber(TextHelper.getDefault());
	}
	
	
	public CustomerEntity(final UUID id, final String name, final String lastName, final boolean isActive,
			final IdentificationTypeEntity identificationType, final String identificationNumber) {
		setCustomerId(id);
		setName(name);
		setLastName(lastName);
		setActive(isActive);
		setIdentificationType(identificationType);;
		setIdentificationNumber(identificationNumber);
	}
	
	static CustomerEntity getDefaultValue() {
		return new CustomerEntity();
	}
	
	static CustomerEntity getDefaultValue(final CustomerEntity customer) {
		return ObjectHelper.getDefault(customer, getDefaultValue());
	}


	public String getName() {
		return name;
	}


	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(final String lastName) {
		this.lastName = TextHelper.getDefaultWithTrim(lastName);
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(final boolean isActive) {
		this.isActive = BooleanHelper.getDeafult(isActive);
	}



	public String getIdentificationNumber() {
		return identificationNumber;
	}


	public void setIdentificationNumber(final String identificationNumber) {
		this.identificationNumber = TextHelper.getDefaultWithTrim(identificationNumber);
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final UUID customerId) {
		this.customerId = UUIDHelper.getUUIDHelper().getDefault(customerId);
	}

	public IdentificationTypeEntity getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(final IdentificationTypeEntity identificationType) {
		this.identificationType = IdentificationTypeEntity.getDefaultValue(identificationType);
	}
	
	
	

}
