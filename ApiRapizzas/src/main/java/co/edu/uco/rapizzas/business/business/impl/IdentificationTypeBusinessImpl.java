package co.edu.uco.rapizzas.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.data.dao.factory.DAOFactory;
import co.edu.uco.rapizzas.business.assembler.entity.impl.IdentificationTypeEntityAssembler;
import co.edu.uco.rapizzas.business.business.IdentificationTypeBusiness;
import co.edu.uco.rapizzas.business.domain.IdentificationTypeDomain;

public class IdentificationTypeBusinessImpl implements IdentificationTypeBusiness{
	
	private DAOFactory daoFactory;

	public IdentificationTypeBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void registerNewIdentificationTypeInformation(IdentificationTypeDomain identificationTypeDomain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IdentificationTypeDomain> findAllIdentificationTypes() {
		
		var identificationTypeEntities = daoFactory.getIdentificationTypeDAO().findAll();
		
		return IdentificationTypeEntityAssembler.getIdentificationTypeEntityAssembler().toDomain(identificationTypeEntities);
	}

	@Override
	public void updateIdentificationTypeInformation(UUID id, IdentificationTypeDomain identificationTypeDomain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IdentificationTypeDomain findSpecificIdentificationType(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
