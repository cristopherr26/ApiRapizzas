package co.edu.uco.rapizzas.business.business;

import java.util.UUID;

import co.edu.uco.rapizzas.business.domain.EmployeeDomain;

public interface EmployeeBusiness {
	
	void registerNewEmployeeInformation(EmployeeDomain employeeDomain);
	
	void generateAndSendPhoneVerificationCode(UUID id);
	
	void confirmPhoneNumber(UUID id);
	
	void logInAsEmployee();
	
	void consultEmployeeInformation(UUID id);
	
	void updateUserInformation(UUID id, EmployeeDomain employeeDomain);

}
