package co.edu.uco.rapizzas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.rapizzas.business.facade.impl.IdentificationTypeFacadeImpl;
import co.edu.uco.rapizzas.controller.dto.Response;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.dto.IdentificationTypeDTO;

@RestController
@RequestMapping("/api/v1/identification-types")
public class IdentificationTypeController {
	
	@GetMapping
	public ResponseEntity<Response<IdentificationTypeDTO>> findAllIdentificationTypes() {
		Response<IdentificationTypeDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;

		try {
			var facade = new IdentificationTypeFacadeImpl();
			responseObjectData.setData(facade.findAllIdentificationTypes());
			responseObjectData.addMessage(MessagesEnum.IDENTIFICATION_TYPE_SUCESSFULLY_FOUND.getContent());

		} catch (final RapizzasException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessage(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessage(userMessage);
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<>(responseObjectData, responseStatusCode);
 }

}
