package co.edu.uco.rapizzas.business.business.validator.employee;

import co.edu.uco.rapizzas.business.business.rule.employee.EmployeeDoesNotExistsWithSamePhoneNumberRule;
import co.edu.uco.rapizzas.business.business.validator.Validator;

public final class ValidateEmployeeDoesNotExistsWithSamePhoneNumber implements Validator {
	
	private static final Validator instance = new ValidateEmployeeDoesNotExistsWithSamePhoneNumber();
	
	private ValidateEmployeeDoesNotExistsWithSamePhoneNumber() {
		
	}
	
	public static void executeValidation(final Object... data) {
		instance.validate(data);
	}

	@Override
	public void validate(final Object... data) {
		
		EmployeeDoesNotExistsWithSamePhoneNumberRule.executeRule(data);
		
		
	}

}
