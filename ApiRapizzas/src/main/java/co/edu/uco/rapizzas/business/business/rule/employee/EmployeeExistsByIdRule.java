package co.edu.uco.rapizzas.business.business.rule.employee;

import java.util.UUID;

import co.edu.uco.rapizzas.business.business.rule.Rule;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.factory.DAOFactory;

public final class EmployeeExistsByIdRule implements Rule {
	
	private static final Rule instance = new EmployeeExistsByIdRule();
	
	private EmployeeExistsByIdRule() {
		
	}
	
	public static void executeRule(final Object... data) {
		instance.execute(data);
	}

	@Override
	public void execute(final Object... data) {

		if(ObjectHelper.isNull(data)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALUE_IS_NOT_PRESENT_EMPLOYEE.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if(data.length < 2) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WRONG_EMPLOYEE_LENGTH_VALUE.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		var id = (UUID) data[0];
		var daoFactory = (DAOFactory) data [1];
		
		var employee = daoFactory.getEmployeeDAO().findById(id);
		
		if(UUIDHelper.getUUIDHelper().isDefaultUUID(employee.getEmployeeId())) {
			var userMessage = MessagesEnum.USER_ERROR_EMPLOYEE_DOES_NOT_EXITS.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_EMPLOYEE_DOES_NOT_EXITS.getContent() + id;
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
	}

}
