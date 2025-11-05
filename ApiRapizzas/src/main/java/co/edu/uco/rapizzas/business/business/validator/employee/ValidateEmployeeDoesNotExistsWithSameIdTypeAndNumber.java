package co.edu.uco.rapizzas.business.business.validator.employee;

import co.edu.uco.rapizzas.business.business.rule.employee.EmployeeDoesNotExistsWithSameIdTypeAndNumberRule;
import co.edu.uco.rapizzas.business.business.validator.Validator;

public final class ValidateEmployeeDoesNotExistsWithSameIdTypeAndNumber implements Validator {
	
	private static final Validator instance = new ValidateEmployeeDoesNotExistsWithSameIdTypeAndNumber();
	
	private ValidateEmployeeDoesNotExistsWithSameIdTypeAndNumber() {
		
	}
	
	public static void executeValidation(final Object... data) {
		instance.validate(data);
	}

	@Override
	public void validate(final Object... data) {
		
		EmployeeDoesNotExistsWithSameIdTypeAndNumberRule.executeRule(data);
		
	}

}
