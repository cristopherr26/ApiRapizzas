package co.edu.uco.rapizzas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.rapizzas.controller.dto.Response;
import co.edu.uco.rapizzas.business.facade.impl.EmployeeFacadeImpl;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.dto.EmployeeDTO;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	
	@PostMapping
	public ResponseEntity<Response<EmployeeDTO>> registerNewEmployeeInformation(@RequestBody EmployeeDTO employee) {
		
		Response<EmployeeDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;
		
		try {
			var facade = new EmployeeFacadeImpl();
			facade.registerNewEmployeeInformation(employee);
			responseObjectData.addMessage("Employee registered succesfully!!");
		} catch (final RapizzasException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessage(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch(Exception exception) {
			var userMessage = "Unexpected error";
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessage(userMessage);
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}
		return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}

}
