package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class CustomerDTO {
	
	private UUID customerId;
	private String name;
	private String lastName;
	private boolean isActive;
	private boolean isActiveDefaultValue;
	private IdentificationTypeDTO identificationType;
	private String identificationNumber;
	
	public CustomerDTO() {
		setCustomerId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(false);
		setActiveDefaultValue(true);
		setIdentificationType(IdentificationTypeDTO.getDefaultValue());
		setIdentificationNumber(TextHelper.getDefault());
	}
	
	public CustomerDTO(final UUID customerId) {
		setCustomerId(customerId);
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(false);
		setActiveDefaultValue(true);
		setIdentificationType(IdentificationTypeDTO.getDefaultValue());
		setIdentificationNumber(TextHelper.getDefault());
	}
	
	public CustomerDTO(final UUID customerId, final String name, final String lastName, 
			final boolean isActive, final IdentificationTypeDTO identificationType, 
			final String identificationNumber) {
		setCustomerId(customerId);
		setName(name);
		setLastName(lastName);
		setActive(isActive);
		setIdentificationType(identificationType);
		setIdentificationNumber(identificationNumber);
	}
	
	static CustomerDTO getDefaultValue() {
		return new CustomerDTO();
	}
	
	static CustomerDTO getDefaultValue(final CustomerDTO customer) {
		return ObjectHelper.getDefault(customer, getDefaultValue());
	}
	
	public UUID getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(final UUID customerId) {
		this.customerId = UUIDHelper.getUUIDHelper().getDefault(customerId);
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
		this.isActive = isActive;
		setActiveDefaultValue(false);
	}
	
	public IdentificationTypeDTO getIdentificationType() {
		return identificationType;
	}
	
	public void setIdentificationType(final IdentificationTypeDTO identificationType) {
		this.identificationType = IdentificationTypeDTO.getDefaultValue(identificationType);
	}
	
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	
	public void setIdentificationNumber(final String identificationNumber) {
		this.identificationNumber = TextHelper.getDefaultWithTrim(identificationNumber);
	}

	public boolean isActiveDefaultValue() {
		return isActiveDefaultValue;
	}

	private void setActiveDefaultValue(final boolean isActiveDefaultValue) {
		this.isActiveDefaultValue = isActiveDefaultValue;
	}
	
	

}
