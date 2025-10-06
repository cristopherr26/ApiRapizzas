package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.BooleanHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class EmployeeEntity{
	
	private UUID employeeId;
	private String name;
	private String lastName;
	private boolean isActive;
	private IdentificationTypeEntity identificationType;
	private String identificationNumber;
	private String cellPhoneNumber;
	private boolean cellPhoneNumberConfirmed;
	private boolean isAdministrator;
	private String employeePassword;
	
	public EmployeeEntity() {
		setEmployeeId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(BooleanHelper.getDefault());
		setIdentificationType(IdentificationTypeEntity.getDefaultValue());;
		setIdentificationNumber(TextHelper.getDefault());
		setAdministrator(BooleanHelper.getDefault());
		setCellPhoneNumber(TextHelper.getDefault());
		setCellPhoneNumberConfirmed(BooleanHelper.getDefault());
		setEmployeePassword(TextHelper.getDefault());
	}
	
	public EmployeeEntity(final UUID id) {
		setEmployeeId(id);
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(BooleanHelper.getDefault());
		setIdentificationType(IdentificationTypeEntity.getDefaultValue());;
		setIdentificationNumber(TextHelper.getDefault());
		setAdministrator(BooleanHelper.getDefault());
		setCellPhoneNumber(TextHelper.getDefault());
		setCellPhoneNumberConfirmed(BooleanHelper.getDefault());
		setEmployeePassword(TextHelper.getDefault());
	}
	
	
	public EmployeeEntity(final UUID id, final String name, final String lastName, 
			final boolean isActive, final IdentificationTypeEntity identificationType, 
			final String identificationNumber, final String cellPhoneNumber, 
			final boolean cellPhoneNumberConfirmed, final boolean isAdministrator, 
			final String employeePassword) {
		setEmployeeId(id);
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

	static EmployeeEntity getDefaultValue() {
		return new EmployeeEntity();
	}
	
	static EmployeeEntity getDefaultValue(final EmployeeEntity employee) {
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
		this.isActive = BooleanHelper.getDeafult(isActive);
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
		this.cellPhoneNumberConfirmed = BooleanHelper.getDeafult(cellPhoneNumberConfirmed);
	}

	public boolean isAdministrator() {
		return isAdministrator;
	}

	public void setAdministrator(final boolean isAdministrator) {
		this.isAdministrator = BooleanHelper.getDeafult(isAdministrator);
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(final String employeePassword) {
		this.employeePassword = TextHelper.getDefault(employeePassword);
	}

	public UUID getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(final UUID employeeId) {
		this.employeeId = UUIDHelper.getUUIDHelper().getDefault(employeeId);
	}

	public IdentificationTypeEntity getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(final IdentificationTypeEntity identificationType) {
		this.identificationType = IdentificationTypeEntity.getDefaultValue(identificationType);
	}

}
