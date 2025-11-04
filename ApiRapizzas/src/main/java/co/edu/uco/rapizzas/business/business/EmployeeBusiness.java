package co.edu.uco.rapizzas.business.business;

import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.business.domain.EmployeeDomain;

public interface EmployeeBusiness {
	
	void registerNewEmployeeInformation(EmployeeDomain employeeDomain);
	
	void updateEmployeeInformation(UUID id, EmployeeDomain employeeDomain);
	
	List<EmployeeDomain> findAllEmployees();
	
	List<EmployeeDomain> findEmployeeByFilter(EmployeeDomain employeeFilters);
	
	void confirmMobileNumber(UUID id, int confirmationCode);
	
	void sendMobileNumberConfirmation(UUID id);
	
	void loginAsEmployee();
	

}
