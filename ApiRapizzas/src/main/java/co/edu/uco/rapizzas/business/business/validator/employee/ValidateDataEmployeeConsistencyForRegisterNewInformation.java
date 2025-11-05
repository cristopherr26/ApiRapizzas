package co.edu.uco.rapizzas.business.business.validator.employee;

import co.edu.uco.rapizzas.business.business.rule.generics.StringLengthValueIsValidRule;
import co.edu.uco.rapizzas.business.business.rule.generics.StringFormatValueIsValidRule;
import co.edu.uco.rapizzas.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.rapizzas.business.business.rule.generics.UUIDValueIsPresentRule;
import co.edu.uco.rapizzas.business.business.validator.Validator;
import co.edu.uco.rapizzas.business.domain.EmployeeDomain;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;

public final class ValidateDataEmployeeConsistencyForRegisterNewInformation implements Validator{
	
	private static final Validator instance = new ValidateDataEmployeeConsistencyForRegisterNewInformation();
	
	private ValidateDataEmployeeConsistencyForRegisterNewInformation(){
		
	}
	
	public static void executeValidation(final Object... data) {
		instance.validate(data);
	}

	@Override
	public void validate(final Object... data) {
		
		if(ObjectHelper.isNull(data)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALUE_EMPLOYEE_IS_NOT_PRESENT.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if(data.length < 1) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WRONG_EMPLOYEE_LENGTH_VALUE_IS_NOT_PRESENT.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		var employeeDomainData = (EmployeeDomain) data [0];
		
		validateEmptyData(employeeDomainData);
		
		validateDataLength(employeeDomainData);
		
		validateDataFormat(employeeDomainData);
		
	}
	
	private void validateEmptyData(final EmployeeDomain data) {
		 
		UUIDValueIsPresentRule.executeRule(data.getIdentificationType().getId(), "Tipo de identificación");
		StringValueIsPresentRule.executeRule(data.getIdentificationNumber(), "Número de identificación ", true);
		StringValueIsPresentRule.executeRule(data.getName(), "Nombre", true);
		StringValueIsPresentRule.executeRule(data.getLastName(), "Apellido", true);
		StringValueIsPresentRule.executeRule(data.getCellPhoneNumber(), " Número de celular", true);
		StringValueIsPresentRule.executeRule(data.getEmployeePassword(), " Contraseña", false);
	
	}
	
	private void validateDataLength(final EmployeeDomain data) {
		
		StringLengthValueIsValidRule.executeRule(data.getIdentificationNumber(), "Número de identificación", 8, 12, true);
		StringLengthValueIsValidRule.executeRule(data.getName(), "Nombre", 3, 25, true);
		StringLengthValueIsValidRule.executeRule(data.getLastName(), "Apellido", 2, 25, true);
		StringLengthValueIsValidRule.executeRule(data.getCellPhoneNumber(), "Número de celular", 10, 15, true);
		StringLengthValueIsValidRule.executeRule(data.getEmployeePassword(), "Contraseña", 8, 15, false);
		

	}
	
	private void validateDataFormat(final EmployeeDomain data) {
		
		StringFormatValueIsValidRule.executeRule(data.getIdentificationNumber(), "Número de identificación", "^[A-Za-z0-9]+$", true );
		StringFormatValueIsValidRule.executeRule(data.getName(), "Primer nombre", "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü]+$", true);
		StringFormatValueIsValidRule.executeRule(data.getLastName(), "Primer apellido", "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü]+$", true);
		StringFormatValueIsValidRule.executeRule(data.getCellPhoneNumber(), "Número de celular", "^\\+?\\d+$", true);
		StringFormatValueIsValidRule.executeRule(data.getEmployeePassword(), "Contraseña", "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*?\\-_])[A-Za-z\\d!@#$%&*?\\-_]{8,15}$", false);
		
	}

}
