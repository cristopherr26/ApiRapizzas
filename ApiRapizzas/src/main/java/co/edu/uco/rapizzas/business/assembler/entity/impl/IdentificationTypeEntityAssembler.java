package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.IdentificationTypeDomain;
import co.edu.uco.rapizzas.entity.IdentificationTypeEntity;

public final class IdentificationTypeEntityAssembler implements EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain>{
	
private static final EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> instance = new IdentificationTypeEntityAssembler();
	
	private IdentificationTypeEntityAssembler() {
		
	}
	
	public static EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> getIdentificationTypeEntityAssembler() {
		return instance;
	}
	
	@Override
	public IdentificationTypeEntity toDomain(final IdentificationTypeDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdentificationTypeDomain toEntity(final IdentificationTypeEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
