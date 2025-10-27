package co.edu.uco.rapizzas.business.facade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.dto.EmployeeDTO;

public interface EmployeeFacade {
	
	void registerNewUserInformation(EmployeeDTO employeeDto);
	
	void updateUserInformation(UUID id, EmployeeDTO employeeDto);
	
	List<EmployeeDTO> findAllUsers();
	
	List<EmployeeDTO> findUserByFilter(EmployeeDTO employeeFilters);
	
	void confirmMobileNumber(UUID id, int confirmationCode);
	
	void sendMobileNumberConfirmation(UUID id);
	
	void loginAsEmployee();

}
