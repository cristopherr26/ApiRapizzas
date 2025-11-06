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
			
			employee.setName("Juan");
			employee.setLastName("Montoya");
			employee.setIdentificationNumber("1040873589");
			employee.setEmployeePassword("holaMundo_01");
			employee.setAdministrator(false);
			employee.setActive(false);
			employee.setIdentificationType(new IdentificationTypeDTO(UUID.fromString("11111111-1111-1111-1111-111111111111")));
			employee.setCellPhoneNumberConfirmed(false);
			employee.setCellPhoneNumber("3052136587");
			
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
