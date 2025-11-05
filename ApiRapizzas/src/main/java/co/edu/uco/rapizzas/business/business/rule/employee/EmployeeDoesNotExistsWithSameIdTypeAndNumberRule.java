package co.edu.uco.rapizzas.business.business.rule.employee;

import co.edu.uco.rapizzas.business.assembler.entity.impl.IdentificationTypeEntityAssembler;
import co.edu.uco.rapizzas.business.business.rule.Rule;
import co.edu.uco.rapizzas.business.domain.IdentificationTypeDomain;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.factory.DAOFactory;
import co.edu.uco.rapizzas.entity.EmployeeEntity;

public final class EmployeeDoesNotExistsWithSameIdTypeAndNumberRule implements Rule {
	
	private static final Rule instance = new EmployeeDoesNotExistsWithSameIdTypeAndNumberRule();
	
	private EmployeeDoesNotExistsWithSameIdTypeAndNumberRule() {
		
	}
	
	public static void executeRule(final Object... data) {
		instance.execute(data);
	}

	@Override
	public void execute(final Object... data) {
		
		if(ObjectHelper.isNull(data)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALUE_EMPLOYEE_DOES_NOT_EXISTS_BY_IDENTIFICATION.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if(data.length < 3) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_EXECUTING_STRING_VALUE_IS_PRESENT_RULE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WRONG_EMPLOYEE_LENGTH_VALUE_EMPLOYEE_DOES_NOT_EXISTS_BY_IDENTIFICATION.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
        var idTypeDomain = (IdentificationTypeDomain) data[0];
        var identificationNumber = (String) data[1];
        var daoFactory = (DAOFactory) data[2];

        var idTypeEntity = IdentificationTypeEntityAssembler.getIdentificationTypeEntityAssembler().toEntity(idTypeDomain);
        
        var filter = new EmployeeEntity();
        filter.setIdentificationType(idTypeEntity);
        filter.setIdentificationNumber(identificationNumber);
        
	    var duplicateIdentification = daoFactory.getEmployeeDAO().findByFilter(filter);
	    
	    if(!duplicateIdentification.isEmpty()) {
	    	var userMessage = MessagesEnum.USER_ERROR_EMPLOYEE_DOES_EXISTS_BY_IDENTIFICATION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_EMPLOYEE_DOES_EXISTS_BY_IDENTIFICATION.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
	    }
		
		
	}

}
