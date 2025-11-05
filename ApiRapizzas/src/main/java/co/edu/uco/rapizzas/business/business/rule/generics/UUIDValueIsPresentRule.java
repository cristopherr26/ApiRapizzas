package co.edu.uco.rapizzas.business.business.rule.generics;

import java.util.UUID;

import co.edu.uco.rapizzas.business.business.rule.Rule;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;

public final class UUIDValueIsPresentRule implements Rule {
	
	private static final Rule instance = new UUIDValueIsPresentRule();
	
	private UUIDValueIsPresentRule() {
		
	}
	
	public static void executeRule(final Object... data) {
		instance.execute(data);
	}

	@Override
	public void execute(final Object... data) {
		if(ObjectHelper.isNull(data)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_UUID_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_UUID_VALUE_IS_PRESENT_RULE.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if(data.length < 2) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_UUID_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_UUID_VALUE_IS_PRESENT_RULE_WITH_PARAMETERS.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		var uuidData = (UUID) data[0];
		var dataName = (String) data[1];
		
		if(UUIDHelper.getUUIDHelper().isDefaultUUID(uuidData)) {
			var userMessage= MessagesEnum.USER_ERROR_WHILE_EXECUTING_UUID_VALUE_IS_PRESENT_RULE_EMPTY_DATA.getContent();
			var technicalMessage= MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_UUID_VALUE_IS_PRESENT_RULE_EMPTY_DATA.getContent() + dataName;

			throw RapizzasException.create(userMessage, technicalMessage);
	}
		
	}
}