package co.edu.uco.rapizzas.business.business.rule.generics;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.business.business.rule.Rule;
import co.edu.uco.rapizzas.business.business.rule.generics.StringLengthValueIsValidRule;

public final class StringLengthValueIsValidRule implements Rule{
	
	private static final Rule instance = new StringLengthValueIsValidRule();
	
	private StringLengthValueIsValidRule() {
		
	}
	
	public static void executeRule(final Object... data) {
		instance.execute(data);
	}

	@Override
	public void execute(Object... data) {
		if (ObjectHelper.isNull(data)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_STRING_LENGTH_VALUE_IS_VALID_RULE.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if (data.length < 5) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_STRING_LENGTH_VALUE_IS_VALID_RULE_WITH_PARAMETERS.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		var stringData = (String) data[0];
		var dataName = (String) data[1];
		int minLength = (Integer) data[2];
		int maxLength = (Integer) data[3];
		boolean mustApplyTrim = (Boolean) data[4];
		
		if (!TextHelper.lengthIsValid(stringData, minLength, maxLength, mustApplyTrim)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_LENGTH_VALUE_IS_VALID_RULE.getContent().concat(dataName).concat(" - ").concat(String.valueOf(minLength)).concat(" y ").concat(String.valueOf(maxLength));
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_STRING_LENGTH_VALUE_IS_VALID_RULE_WITH_LENGTH.getContent().concat(dataName).concat(" - ")
					.concat(String.valueOf(minLength)).concat(" y ").concat(String.valueOf(maxLength));
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
	}

}
