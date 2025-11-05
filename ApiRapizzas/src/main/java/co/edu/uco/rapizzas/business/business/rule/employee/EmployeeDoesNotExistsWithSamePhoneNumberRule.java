package co.edu.uco.rapizzas.business.business.rule.employee;

import co.edu.uco.rapizzas.business.business.rule.Rule;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.factory.DAOFactory;
import co.edu.uco.rapizzas.entity.EmployeeEntity;

public final class EmployeeDoesNotExistsWithSamePhoneNumberRule implements Rule{
	
	private static final Rule instance = new EmployeeDoesNotExistsWithSamePhoneNumberRule();
	
	private EmployeeDoesNotExistsWithSamePhoneNumberRule() {
		
	}
	
	public static void executeRule(final Object... data) {
		instance.execute(data);
	}

	@Override
	public void execute(final Object... data) {
		if(ObjectHelper.isNull(data)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALUE_EMPLOYEEE_DOES_NOT_EXISTS_BY_MOBILE_NUMBER.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if(data.length < 2) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WRONG_EMPLOYEE_LENGTH_VALUE_EMPLOYEE_DOES_NOT_EXISTS_BY_MOBILE_NUMBER.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		var mobilePhone = (String) data [0];
		var daoFactory = (DAOFactory) data [1];
		
		var filter = new EmployeeEntity();
		filter.setCellPhoneNumber(mobilePhone);
	   
	    var duplicateMobilePhone = daoFactory.getEmployeeDAO().findByFilter(filter);
	    
	    if(!duplicateMobilePhone.isEmpty()) {
	    	var userMessage = MessagesEnum.USER_ERROR_EMPLOYEE_DOES_EXISTS_BY_MOBILE_NUMBER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_EMPLOYEE_DOES_EXISTS_BY_MOBILE_NUMBER.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
	    }
		
	}

}
