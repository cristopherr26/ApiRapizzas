package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.BooleanHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public class EmployeeDomain extends Domain {
	
	private String name;
	private String lastName;
	private boolean isActive;
	private IdentificationTypeDomain identificationType;
	private String identificationNumber;
	private String cellPhoneNumber;
	private boolean cellPhoneNumberConfirmed;
	private boolean isAdministrator;
	private String employeePassword;
	
	public EmployeeDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(BooleanHelper.getDefault());
		setIdentificationType(IdentificationTypeDomain.getDefaultValue());;
		setIdentificationNumber(TextHelper.getDefault());
		setAdministrator(BooleanHelper.getDefault());
		setCellPhoneNumber(TextHelper.getDefault());
		setCellPhoneNumberConfirmed(BooleanHelper.getDefault());
		setEmployeePassword(TextHelper.getDefault());
	}
	
	public EmployeeDomain(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setActive(BooleanHelper.getDefault());
		setIdentificationType(IdentificationTypeDomain.getDefaultValue());;
		setIdentificationNumber(TextHelper.getDefault());
		setAdministrator(BooleanHelper.getDefault());
		setCellPhoneNumber(TextHelper.getDefault());
		setCellPhoneNumberConfirmed(BooleanHelper.getDefault());
		setEmployeePassword(TextHelper.getDefault());
	}
	
	
	public EmployeeDomain(final UUID id, final String name, final String lastName, final boolean isActive,
			final IdentificationTypeDomain identificationType, final String identificationNumber, final String cellPhoneNumber,
			final boolean cellPhoneNumberConfirmed, final boolean isAdministrator, final String employeePassword) {
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
		this.isActive = BooleanHelper.getDeafult(isActive);
	}


	public IdentificationTypeDomain getIdentificationType() {
		return identificationType;
	}


	public void setIdentificationType(final IdentificationTypeDomain identificationType) {
		this.identificationType = ObjectHelper.getDefault(identificationType, IdentificationTypeDomain.getDefaultValue());
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
	
	

}
