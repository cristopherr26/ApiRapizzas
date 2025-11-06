package co.edu.uco.rapizzas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/identification-types")
public class IdentificationTypeController {
	
	@GetMapping
	public void findAllIdentificationTypes() {
   // TODO document why this method is empty
 }

}
