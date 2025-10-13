package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public class EmployeeDTO {
	
	private UUID employeeId;
	private String name;
	private String lastName;
	private boolean isActive;
	private boolean isActiveDefaultValue;
	private IdentificationTypeDTO identificationType;
	private String identificationNumber;
	private String cellPhoneNumber;
	private boolean cellPhoneNumberConfirmed;
	private boolean cellPhoneNumberConfirmedDefaultValue;
	private boolean isAdministrator;
	private boolean isAdministratorDefaultValue;
	private String employeePassword;
	
	public EmployeeDTO() {
		setEmployeeId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(false);
		setActiveDefaultValue(true);
		setIdentificationType(IdentificationTypeDTO.getDefaultValue());
		setIdentificationNumber(TextHelper.getDefault());
		setCellPhoneNumber(TextHelper.getDefault());
		setCellPhoneNumberConfirmed(false);
		setCellPhoneNumberConfirmedDefaultValue(true);
		setAdministrator(false);
		setAdministratorDefaultValue(true);
		setEmployeePassword(TextHelper.getDefault());
	}
	
	public EmployeeDTO(final UUID employeeId) {
		setEmployeeId(employeeId);
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(false);
		setActiveDefaultValue(true);
		setIdentificationType(IdentificationTypeDTO.getDefaultValue());
		setIdentificationNumber(TextHelper.getDefault());
		setCellPhoneNumber(TextHelper.getDefault());
		setCellPhoneNumberConfirmed(false);
		setCellPhoneNumberConfirmedDefaultValue(true);
		setAdministrator(false);
		setAdministratorDefaultValue(true);
		setEmployeePassword(TextHelper.getDefault());
	}
	
	public EmployeeDTO(final UUID employeeId, final String name, final String lastName, 
			final boolean isActive, final IdentificationTypeDTO identificationType, 
			final String identificationNumber, final String cellPhoneNumber,
			final boolean cellPhoneNumberConfirmed, final boolean isAdministrator, 
			final String employeePassword) {
		setEmployeeId(employeeId);
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
	
	static EmployeeDTO getDefaultValue() {
		return new EmployeeDTO();
	}
	
	static EmployeeDTO getDefaultValue(final EmployeeDTO employee) {
		return ObjectHelper.getDefault(employee, getDefaultValue());
	}
	
	public UUID getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(final UUID employeeId) {
		this.employeeId = ObjectHelper.getDefault(employeeId, UUIDHelper.getUUIDHelper().getDefault());
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
		this.identificationType = ObjectHelper.getDefault(identificationType, IdentificationTypeDTO.getDefaultValue());
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
