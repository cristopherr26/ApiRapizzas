package co.edu.uco.rapizzas.business.business.impl;

import java.util.List;
import java.util.UUID;

import static co.edu.uco.rapizzas.business.assembler.entity.impl.EmployeeEntityAssembler.getEmployeeEntityAssembler;

import co.edu.uco.rapizzas.business.business.EmployeeBusiness;
import co.edu.uco.rapizzas.business.business.validator.employee.ValidateDataEmployeeConsistencyForRegisterNewInformation;
import co.edu.uco.rapizzas.business.business.validator.employee.ValidateEmployeeDoesNotExistsWithSameIdTypeAndNumber;
import co.edu.uco.rapizzas.business.business.validator.employee.ValidateEmployeeDoesNotExistsWithSamePhoneNumber;
import co.edu.uco.rapizzas.business.business.validator.identificationtype.ValidateIdTypeExistsById;
import co.edu.uco.rapizzas.business.domain.EmployeeDomain;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.data.dao.factory.DAOFactory;


public final class EmployeeBusinessImpl implements EmployeeBusiness{
	
	private DAOFactory daoFactory;
	
	
	public EmployeeBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void registerNewEmployeeInformation(final EmployeeDomain employeeDomain) {
		
		ValidateDataEmployeeConsistencyForRegisterNewInformation.executeValidation(employeeDomain);

		ValidateIdTypeExistsById.executeValidation(employeeDomain.getIdentificationType().getId(), daoFactory);
		
		ValidateEmployeeDoesNotExistsWithSameIdTypeAndNumber.executeValidation(employeeDomain.getIdentificationType(), employeeDomain.getIdentificationNumber(), daoFactory);
		
		ValidateEmployeeDoesNotExistsWithSamePhoneNumber.executeValidation(employeeDomain.getCellPhoneNumber(), daoFactory);
		
		var employeeEntity = getEmployeeEntityAssembler().toEntity(employeeDomain);
		
		employeeEntity.setEmployeeId(generateId());
		
		employeeEntity.setActive(true);
		
		daoFactory.getEmployeeDAO().create(employeeEntity);
		
	}
	
	private UUID generateId() {
		var id = UUIDHelper.getUUIDHelper().generateNewUUID();
		var userEntity = daoFactory.getEmployeeDAO().findById(id);
		while (!UUIDHelper.getUUIDHelper().isDefaultUUID(userEntity.getEmployeeId())) {
			id = UUIDHelper.getUUIDHelper().generateNewUUID();
			userEntity = daoFactory.getEmployeeDAO().findById(id);
		}
		
		return id;
	}
	
	@Override
	public void updateEmployeeInformation(final UUID id, final EmployeeDomain employeeDomain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmployeeDomain> findAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDomain> findEmployeeByFilter(final EmployeeDomain employeeFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmMobileNumber(final UUID id, final int confirmationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMobileNumberConfirmation(final UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginAsEmployee() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmployeeDomain findSpecificEmployee(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
