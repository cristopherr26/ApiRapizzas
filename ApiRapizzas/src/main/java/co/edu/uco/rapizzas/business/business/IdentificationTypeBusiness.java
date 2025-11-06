package co.edu.uco.rapizzas.business.business;

import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.business.domain.IdentificationTypeDomain;

public interface IdentificationTypeBusiness {
	
	void registerNewIdentificationTypeInformation(
			IdentificationTypeDomain identificationTypeDomain);
	
	List<IdentificationTypeDomain> findAllIdentificationTypes();
	
	void updateIdentificationTypeInformation(UUID id, 
			IdentificationTypeDomain identificationTypeDomain);
	
	IdentificationTypeDomain findSpecificIdentificationType(UUID id);

}
