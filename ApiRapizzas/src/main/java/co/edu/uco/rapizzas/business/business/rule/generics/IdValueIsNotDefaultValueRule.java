package co.edu.uco.rapizzas.business.business.rule.generics;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.business.business.rule.Rule;
import co.edu.uco.rapizzas.business.business.rule.generics.IdValueIsNotDefaultValueRule;

public final class IdValueIsNotDefaultValueRule implements Rule{
	
	private static final Rule instance = new IdValueIsNotDefaultValueRule();
	
	private IdValueIsNotDefaultValueRule() {
		
	}
	
	public static void executeRule(final Object... data) {
		instance.execute(data);
	}

	@Override
	public void execute(Object... data) {
		if (ObjectHelper.isNull(data)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_ID_VALUE_IS_NOT_DEFAULT_VALUE_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_ID_VALUE_IS_NOT_DEFAULT_VALUE_RULE.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if (data.length < 2) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_ID_VALUE_IS_NOT_DEFAULT_VALUE_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_ID_VALUE_IS_NOT_DEFAULT_VALUE_RULE_WITH_PARAMETERS.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		var id = (UUID) data[0];
		var dataName = (String) data[1];
		
		if (ObjectHelper.isNull(id)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_ID_VALUE_IS_NOT_DEFAULT_VALUE_RULE_EMPTY_DATA.getContent().concat(dataName);
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_ID_VALUE_IS_NOT_DEFAULT_VALUE_RULE_EMPTY_DATA.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if (UUIDHelper.getUUIDHelper().isDefaultUUID(id)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_ID_VALUE_IS_NOT_DEFAULT_VALUE_RULE_DEFAULT_VALUE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_ID_VALUE_IS_NOT_DEFAULT_VALUE_RULE_DEFAULT_VALUE.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		
		}
		
	}

}
