package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class EmployeeDomain extends Domain {
	
	private String name;
	private String lastName;
	private boolean isActive;
	private boolean isActiveDefaultValue;
	private IdentificationTypeDomain identificationType;
	private String identificationNumber;
	private String cellPhoneNumber;
	private boolean cellPhoneNumberConfirmed;
	private boolean cellPhoneNumberConfirmedDefaultValue;
	private boolean isAdministrator;
	private boolean isAdministratorDefaultValue;
	private String employeePassword;
	
	EmployeeDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(false);
		setActiveDefaultValue(true);
		setIdentificationType(IdentificationTypeDomain.getDefaultValue());;
		setIdentificationNumber(TextHelper.getDefault());
		setAdministrator(false);
		setAdministratorDefaultValue(true);
		setCellPhoneNumber(TextHelper.getDefault());
		setCellPhoneNumberConfirmed(false);
		setCellPhoneNumberConfirmedDefaultValue(true);
		setEmployeePassword(TextHelper.getDefault());
	}
	
	public EmployeeDomain(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(false);
		setActiveDefaultValue(true);
		setIdentificationType(IdentificationTypeDomain.getDefaultValue());;
		setIdentificationNumber(TextHelper.getDefault());
		setAdministrator(false);
		setAdministratorDefaultValue(true);
		setCellPhoneNumber(TextHelper.getDefault());
		setCellPhoneNumberConfirmed(false);
		setCellPhoneNumberConfirmedDefaultValue(true);
		setEmployeePassword(TextHelper.getDefault());
	}
	
	
	public EmployeeDomain(final UUID id, final String name, final String lastName, 
			final boolean isActive, final IdentificationTypeDomain identificationType, 
			final String identificationNumber, final String cellPhoneNumber,
			final boolean cellPhoneNumberConfirmed, final boolean isAdministrator, 
			final String employeePassword) {
		super(id);
		setName(name);
		setLastName(lastName);
		setActive(isActive);
		setIdentificationType(identificationType);
		setIdentificationNumber(identificationNumber);
		setCellPhoneNumber(cellPhoneNumber);
		setCellPhoneNumberConfirmed(cellPhoneNumberConfirmed);
		setAdministrator(isAdministrator);
		setEmployeePassword(employeePassword);
	}

	static EmployeeDomain getDefaultValue() {
		return new EmployeeDomain();
	}
	
	static EmployeeDomain getDefaultValue(final EmployeeDomain employee) {
		return ObjectHelper.getDefault(employee, getDefaultValue());
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

	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	public void setCellPhoneNumber(final String cellPhoneNumber) {
		this.cellPhoneNumber = TextHelper.getDefaultWithTrim(cellPhoneNumber);
	}

	public boolean isCellPhoneNumberConfirmed() {
		return cellPhoneNumberConfirmed;
	}

	public void setCellPhoneNumberConfirmed(final boolean cellPhoneNumberConfirmed) {
		this.cellPhoneNumberConfirmed = cellPhoneNumberConfirmed;
		setCellPhoneNumberConfirmedDefaultValue(false);
	}

	public boolean isAdministrator() {
		return isAdministrator;
	}

	public void setAdministrator(final boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
		setAdministratorDefaultValue(false);
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(final String employeePassword) {
		this.employeePassword = TextHelper.getDefault(employeePassword);
	}

	public boolean isActiveDefaultValue() {
		return isActiveDefaultValue;
	}

	private void setActiveDefaultValue(final boolean isActiveDefaultValue) {
		this.isActiveDefaultValue = isActiveDefaultValue;
	}

	public boolean isCellPhoneNumberConfirmedDefaultValue() {
		return cellPhoneNumberConfirmedDefaultValue;
	}

	private void setCellPhoneNumberConfirmedDefaultValue(final boolean cellPhoneNumberConfirmedDefaultValue) {
		this.cellPhoneNumberConfirmedDefaultValue = cellPhoneNumberConfirmedDefaultValue;
	}

	public boolean isAdministratorDefaultValue() {
		return isAdministratorDefaultValue;
	}

	private void setAdministratorDefaultValue(final boolean isAdministratorDefaultValue) {
		this.isAdministratorDefaultValue = isAdministratorDefaultValue;
	}
	
}
