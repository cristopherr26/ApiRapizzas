package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class CustomerDomain extends Domain {
	
	private String name;
	private String lastName;
	private boolean isActive;
	private boolean isActiveDefaultValue;
	private IdentificationTypeDomain identificationType;
	private String identificationNumber;
	
	public CustomerDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(false);
		setActiveDefaultValue(true);
		setIdentificationType(IdentificationTypeDomain.getDefaultValue());;
		setIdentificationNumber(TextHelper.getDefault());
	}
	
	public CustomerDomain(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(false);
		setActiveDefaultValue(true);
		setIdentificationType(IdentificationTypeDomain.getDefaultValue());;
		setIdentificationNumber(TextHelper.getDefault());
	}
	
	
	public CustomerDomain(final UUID id, final String name, final String lastName, 
			final boolean isActive, final IdentificationTypeDomain identificationType, 
			final String identificationNumber) {
		super(id);
		setName(name);
		setLastName(lastName);
		setActive(isActive);
		setIdentificationType(identificationType);;
		setIdentificationNumber(identificationNumber);
	}
	
	static CustomerDomain getDefaultValue() {
		return new CustomerDomain();
	}
	
	static CustomerDomain getDefaultValue(final CustomerDomain customer) {
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
		this.isActive = isActive;
		setActiveDefaultValue(false);
	}

	
	public boolean isActiveDefaultValue() {
		return isActiveDefaultValue;
	}

	private void setActiveDefaultValue(final boolean isActiveDefaultValue) {
		this.isActiveDefaultValue = isActiveDefaultValue;
	}

	public IdentificationTypeDomain getIdentificationType() {
		return identificationType;
	}


	public void setIdentificationType(final IdentificationTypeDomain identificationType) {
		this.identificationType = IdentificationTypeDomain.getDefaultValue(identificationType);
	}


	public String getIdentificationNumber() {
		return identificationNumber;
	}


	public void setIdentificationNumber(final String identificationNumber) {
		this.identificationNumber = TextHelper.getDefaultWithTrim(identificationNumber);
	}
	
}
