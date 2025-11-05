package co.edu.uco.rapizzas.test;

import java.util.UUID;

import co.edu.uco.rapizzas.business.facade.impl.EmployeeFacadeImpl;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.dto.EmployeeDTO;
import co.edu.uco.rapizzas.dto.IdentificationTypeDTO;


public class TestEmployeeRegistration {
	
	public static void main(String[] args) {
		
		try {
			var employee = new EmployeeDTO();
			
			employee.setName("Cristopher");
			employee.setLastName("Jose");
			employee.setIdentificationNumber("10362540589");
			employee.setEmployeePassword("11Ospina*");
			employee.setAdministrator(false);
			employee.setActive(false);
			employee.setIdentificationType(new IdentificationTypeDTO(UUID.fromString("2928425a-9571-4eb3-a079-01addb001798")));
			employee.setCellPhoneNumberConfirmed(false);
			employee.setCellPhoneNumber("12345678902");
			
			var facade = new EmployeeFacadeImpl();
			facade.registerNewEmployeeInformation(employee);
			
			System.out.println("Ganamos el semestre");
		}catch(RapizzasException e) {
			System.out.println(e.getUserMessage());
			System.out.println(e.getTechnicalMessage());
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		}


}
