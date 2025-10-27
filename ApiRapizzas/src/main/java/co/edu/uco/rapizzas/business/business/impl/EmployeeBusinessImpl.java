package co.edu.uco.rapizzas.business.business.impl;

import java.util.List;
import java.util.UUID;

import static co.edu.uco.rapizzas.business.assembler.entity.impl.EmployeeEntityAssembler.getEmployeeEntityAssembler;

import co.edu.uco.rapizzas.business.business.EmployeeBusiness;
import co.edu.uco.rapizzas.business.domain.EmployeeDomain;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.entity.EmployeeDAO;
import co.edu.uco.rapizzas.data.dao.factory.DAOFactory;
import co.edu.uco.rapizzas.entity.EmployeeEntity;


public final class EmployeeBusinessImpl implements EmployeeBusiness{
	
	private DAOFactory daoFactory;
	
	
	public EmployeeBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void registerNewUserInformation(final EmployeeDomain employeeDomain) {
		
		//1. Validar que la informacion sea consistente a nivel de tipo de dato, longitud, oblatoriedad
		//2. Validar que no exista otro empleado con el mismo tipo y número de documento
		//3. Validar que no exista previamente un empleado con el mismo número de teléfono celular
		//4. Generar un identificador para el nuevo empleado, asegurando de que no exista previamente
		
		validateEmployeeData(employeeDomain);
		validateDuplicatedEmployee(employeeDomain);
		
		var id = generateUniqueEmployeeId(daoFactory.getEmployeeDAO());
		
		var employeeEntity = getEmployeeEntityAssembler().toEntity(employeeDomain);
		
		employeeEntity.setEmployeeId(id);
		
		daoFactory.getEmployeeDAO().create(employeeEntity);
		
	}
	
	private void validateEmployeeData (final EmployeeDomain employeeDomain) {
		if(ObjectHelper.isNull(employeeDomain)) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_REGISTERING_EMPLOYEE_NULL_EMPLOYEE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_REGISTERING_EMPLOYEE_NULL_EMPLOYEE.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if(ObjectHelper.isNull(employeeDomain.getIdentificationType())) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_REGISTERING_EMPLOYEE_NULL_IDENTIFICATION_TYPE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_REGISTERING_EMPLOYEE_NULL_IDENTIFICATION_TYPE.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		
		if (TextHelper.isEmptyWithTrim(employeeDomain.getIdentificationNumber()) || 
				!TextHelper.isValidIdentificationNumber(employeeDomain.getIdentificationNumber())) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_REGISTERING_EMPLOYEE_INVALID_IDENTIFICATION_NUMBER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_REGISTERING_EMPLOYEE_INVALID_IDENTIFICATION_NUMBER.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if (TextHelper.isEmptyWithTrim(employeeDomain.getName()) || 
				!TextHelper.isValidName(employeeDomain.getName())) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_REGISTERING_EMPLOYEE_INVALID_FIRST_NAME.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_REGISTERING_EMPLOYEE_INVALID_FIRST_NAME.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		
		if (TextHelper.isEmptyWithTrim(employeeDomain.getLastName()) || 
				!TextHelper.isValidLastName(employeeDomain.getLastName())) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_REGISTERING_EMPLOYEE_INVALID_LAST_NAME.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_REGISTERING_EMPLOYEE_INVALID_LAST_NAME.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		
		if (TextHelper.isEmpty(employeeDomain.getEmployeePassword()) || 
				!TextHelper.isValidPassword(employeeDomain.getEmployeePassword())) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_REGISTERING_EMPLOYEE_INVALID_PASSWORD.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_REGISTERING_EMPLOYEE_INVALID_PASSWORD.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if (!TextHelper.isValidPhoneNumber(employeeDomain.getCellPhoneNumber())) {
			var userMessage = MessagesEnum.USER_ERROR_WHILE_REGISTERING_EMPLOYEE_INVALID_CELL_PHONE_NUMBER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_REGISTERING_EMPLOYEE_INVALID_CELL_PHONE_NUMBER.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		
	}
	
	private void validateDuplicatedEmployee(EmployeeDomain employeeDomain) {

	    var employeeEntityFilter = getEmployeeEntityAssembler().toEntity(employeeDomain);


	    var employeeDAO = daoFactory.getEmployeeDAO();

	    var filterByIdentification = new EmployeeEntity();
	    filterByIdentification.setIdentificationType(employeeEntityFilter.getIdentificationType());
	    filterByIdentification.setIdentificationNumber(employeeEntityFilter.getIdentificationNumber());

	    var existingById = employeeDAO.findByFilter(filterByIdentification);
	    if (!existingById.isEmpty()) {
	    	var userMessage = MessagesEnum.USER_ERROR_WHILE_REGISTERING_EMPLOYEE_DUPLICATED_IDENTIFICATION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_REGISTERING_EMPLOYEE_DUPLICATED_IDENTIFICATION.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
	    }


	    var filterByPhone = new EmployeeEntity();
	    filterByPhone.setCellPhoneNumber(employeeEntityFilter.getCellPhoneNumber());

	    var existingByPhone = employeeDAO.findByFilter(filterByPhone);
	    if (!existingByPhone.isEmpty()) {
	    	var userMessage = MessagesEnum.USER_ERROR_WHILE_REGISTERING_EMPLOYEE_DUPLICATED_CELL_PHONE_NUMBER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_WHILE_REGISTERING_EMPLOYEE_DUPLICATED_CELL_PHONE_NUMBER.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
	    }
	}
	
	private UUID generateUniqueEmployeeId(EmployeeDAO employeeDAO) {
	    var id = UUIDHelper.getUUIDHelper().generateNewUUID();

	    var filterById = new EmployeeEntity();
	    filterById.setEmployeeId(id);

	    var existingEmployee = employeeDAO.findByFilter(filterById);

	    while (!existingEmployee.isEmpty()) {
	        id = UUIDHelper.getUUIDHelper().generateNewUUID();
	        filterById = new EmployeeEntity();
	        filterById.setEmployeeId(id);
	        existingEmployee = employeeDAO.findByFilter(filterById);
	    }

	    return id;
	}


	@Override
	public void updateUserInformation(UUID id, EmployeeDomain employeeDomain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmployeeDomain> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDomain> findUserByFilter(EmployeeDomain employeeFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmMobileNumber(UUID id, int confirmationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMobileNumberConfirmation(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginAsEmployee() {
		// TODO Auto-generated method stub
		
	}

	

}
