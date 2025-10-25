package co.edu.uco.rapizzas.business.facade;

import java.util.UUID;

import co.edu.uco.rapizzas.dto.EmployeeDTO;

public interface EmployeeFacade {
	
	void registerNewEmployeeInformation(EmployeeDTO employeeDTO);
	
	void generateAndSendPhoneVerificationCode(UUID id);
	
	void confirmPhoneNumber(UUID id);
	
	void logInAsEmployee();
	
	void consultEmployeeInformation(UUID id);
	
	void updateUserInformation(UUID id, EmployeeDTO employeeDTO);

}
