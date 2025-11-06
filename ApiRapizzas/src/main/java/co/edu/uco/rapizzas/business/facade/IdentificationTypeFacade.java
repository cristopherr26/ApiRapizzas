package co.edu.uco.rapizzas.business.facade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.dto.IdentificationTypeDTO;

public interface IdentificationTypeFacade {
	
	void registerNewIdentificationTypeInformation(
			IdentificationTypeDTO identificationTypeDto);
	
	List<IdentificationTypeDTO> findAllIdentificationTypes();
	
	void updateIdentificationTypeInformation(UUID id, 
			IdentificationTypeDTO identificationTypeDto);
	
	IdentificationTypeDTO findSpecificIdentificationType(UUID id);

}
