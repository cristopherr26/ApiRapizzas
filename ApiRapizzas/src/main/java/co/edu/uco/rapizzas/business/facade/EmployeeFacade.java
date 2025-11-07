package co.edu.uco.rapizzas.business.facade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.dto.EmployeeDTO;

public interface EmployeeFacade {
	
	void registerNewEmployeeInformation(EmployeeDTO employeeDto);
	
	void updateEmployeeInformation(UUID id, EmployeeDTO employeeDto);
	
	List<EmployeeDTO> findAllEmployees();
	
	List<EmployeeDTO> findEmployeeByFilter(EmployeeDTO employeeFilters);
	
	EmployeeDTO findSpecificEmployee(UUID id);
	
	void confirmMobileNumber(UUID id, int confirmationCode);
	
	void sendMobileNumberConfirmation(UUID id);
	
	void loginAsEmployee();

}
