package co.edu.uco.rapizzas.business.business;

import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.business.domain.EmployeeDomain;

public interface EmployeeBusiness {
	
	void registerNewUserInformation(EmployeeDomain employeeDomain);
	
	void updateUserInformation(UUID id, EmployeeDomain employeeDomain);
	
	List<EmployeeDomain> findAllUsers();
	
	List<EmployeeDomain> findUserByFilter(EmployeeDomain employeeFilters);
	
	void confirmMobileNumber(UUID id, int confirmationCode);
	
	void sendMobileNumberConfirmation(UUID id);
	
	void loginAsEmployee();
	

}
