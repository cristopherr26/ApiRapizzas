package co.edu.uco.rapizzas.business.facade.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.business.assembler.dto.impl.IdentificationTypeDTOAssembler;
import co.edu.uco.rapizzas.business.business.impl.IdentificationTypeBusinessImpl;
import co.edu.uco.rapizzas.business.facade.IdentificationTypeFacade;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.factory.DAOFactory;
import co.edu.uco.rapizzas.dto.IdentificationTypeDTO;

public class IdentificationTypeFacadeImpl implements IdentificationTypeFacade{

	@Override
	public void registerNewIdentificationTypeInformation(IdentificationTypeDTO identificationTypeDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IdentificationTypeDTO> findAllIdentificationTypes() {

		var daoFactory = DAOFactory.getFactory();
		var business = new IdentificationTypeBusinessImpl(daoFactory);
		
		try {
			
			var identificationTypeDomainList = business.findAllIdentificationTypes();
			
			return IdentificationTypeDTOAssembler.getIdentificationTypeDTOAssembler().toDTO(identificationTypeDomainList);
			
			
		} catch (final RapizzasException exception) {
			throw exception;
		} catch (final Exception exception) {
			
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_IDENTIFICATION_TYPE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_IDENTIFICATION_TYPE.getContent();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
			
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public void updateIdentificationTypeInformation(UUID id, IdentificationTypeDTO identificationTypeDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IdentificationTypeDTO findSpecificIdentificationType(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
