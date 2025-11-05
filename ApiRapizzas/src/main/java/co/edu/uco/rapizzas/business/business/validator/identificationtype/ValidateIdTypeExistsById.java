package co.edu.uco.rapizzas.business.business.validator.identificationtype;

import co.edu.uco.rapizzas.business.business.rule.identificationtype.IdentificationTypeExistsByIdRule;
import co.edu.uco.rapizzas.business.business.validator.Validator;

public final class ValidateIdTypeExistsById implements Validator {
	
	private static final Validator instance = new ValidateIdTypeExistsById();
	
	private ValidateIdTypeExistsById() {
		
	}
	
	public static void executeValidation(final Object... data) {
		instance.validate(data);
	}

	@Override
	public void validate(final Object... data) {
		
		IdentificationTypeExistsByIdRule.executeRule(data);
		
	}

}
