package co.edu.uco.rapizzas.business.facade.impl;

import java.util.List;
import java.util.UUID;

import static co.edu.uco.rapizzas.business.assembler.dto.impl.EmployeeDTOAssembler.getEmployeeDTOAssembler;

import co.edu.uco.rapizzas.business.business.impl.EmployeeBusinessImpl;
import co.edu.uco.rapizzas.business.facade.EmployeeFacade;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.factory.DAOFactory;
import co.edu.uco.rapizzas.dto.EmployeeDTO;

public final class EmployeeFacadeImpl implements EmployeeFacade{

	@Override
	public void registerNewEmployeeInformation(EmployeeDTO employeeDto) {
		var daoFactory = DAOFactory.getFactory();
		var business = new EmployeeBusinessImpl(daoFactory);
		
		try {
			
			daoFactory.initTransaction();
			
			var domain = getEmployeeDTOAssembler().toDomain(employeeDto);
			
			business.registerNewEmployeeInformation(domain);
			
			daoFactory.commitTransaction();
			
		} catch (final RapizzasException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (final Exception exception){
			daoFactory.rollbackTransaction();
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_REGISTERING_EMPLOYEE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_REGISTERING_EMPLOYEE_WHILE_EXECUTION.getContent();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
			
		} finally {
			daoFactory.closeConnection();
		}
		
	}

	@Override
	public void updateEmployeeInformation(UUID id, EmployeeDTO employeeDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmployeeDTO> findAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDTO> findEmployeeByFilter(EmployeeDTO employeeFilters) {
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

	@Override
	public EmployeeDTO findSpecificEmployee(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
