package co.edu.uco.rapizzas.business.business.validator.employee;

import co.edu.uco.rapizzas.business.business.rule.employee.EmployeeExistsByIdRule;
import co.edu.uco.rapizzas.business.business.validator.Validator;

public final class ValidateEmployeeExistsById implements Validator {
	
	private static final Validator instance = new ValidateEmployeeExistsById();
	
	private ValidateEmployeeExistsById() {
		
	}
	
	public static void executeValidation(final Object... data) {
		instance.validate(data);
	}

	@Override
	public void validate(final Object... data) {
		
		EmployeeExistsByIdRule.executeRule(data);
	}

}
