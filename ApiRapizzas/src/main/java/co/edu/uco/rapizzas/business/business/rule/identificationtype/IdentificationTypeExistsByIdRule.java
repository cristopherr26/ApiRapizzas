package co.edu.uco.rapizzas.business.business.rule.identificationtype;

import java.util.UUID;

import co.edu.uco.rapizzas.business.business.rule.Rule;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.factory.DAOFactory;

public final class IdentificationTypeExistsByIdRule implements Rule {
	
	private static final Rule instance = new IdentificationTypeExistsByIdRule();
	
	private IdentificationTypeExistsByIdRule() {
		
	}
	
	public static void executeRule(final Object... data) {
		instance.execute(data);
	}

	@Override
	public void execute(final Object... data) {
		

		if(ObjectHelper.isNull(data)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_ID_TYPE_EXISTS_BY_ID.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if(data.length < 2) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_EXECUTING_ID_TYPE_EXISTS_BY_ID_WITH_PARAMETERS.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		var id = (UUID) data[0];
		var daoFactory = (DAOFactory) data [1];
		
		var idType = daoFactory.getIdentificationTypeDAO().findById(id);
		
		if(UUIDHelper.getUUIDHelper().isDefaultUUID(idType.getIdentificationTypeId())) {
			var userMessage = MessagesEnum.USER_ERROR_IDENTIFICATION_TYPE_DOES_NOT_EXITS.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_IDENTIFICATION_TYPE_DOES_NOT_EXITS.getContent() + id;
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
	}

}
