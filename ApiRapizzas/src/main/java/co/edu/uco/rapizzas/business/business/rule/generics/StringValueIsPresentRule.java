package co.edu.uco.rapizzas.business.business.rule.generics;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.business.business.rule.Rule;
import co.edu.uco.rapizzas.business.business.rule.generics.StringValueIsPresentRule;

public final class StringValueIsPresentRule implements Rule{
	
	private static final Rule instance = new StringValueIsPresentRule();
	
	private StringValueIsPresentRule() {
		
	}
	
	public static void executeRule(final Object... data) {
		instance.execute(data);
	}

	@Override
	public void execute(Object... data) {
		if (ObjectHelper.isNull(data)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if (data.length < 3) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE_WITH_PARAMETERS.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		var stringData = (String) data[0];
		var dataName = (String) data[1];
		boolean mustApplyTrim = (Boolean) data[2];
		
		if ((mustApplyTrim) 
			? TextHelper.isEmptyWithTrim(stringData)
						: TextHelper.isEmpty(stringData)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE_EMPTY_DATA.getContent().concat(dataName);
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE_EMPTY_DATA.getContent().concat(dataName);
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
	}

}
