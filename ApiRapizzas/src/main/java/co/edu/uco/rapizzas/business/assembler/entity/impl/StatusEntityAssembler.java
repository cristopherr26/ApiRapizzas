package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.StatusDomain;
import co.edu.uco.rapizzas.entity.StatusEntity;

public final class StatusEntityAssembler implements EntityAssembler<StatusEntity, StatusDomain>{
	
	private static final EntityAssembler<StatusEntity, StatusDomain> instance = new StatusEntityAssembler();
	
	private StatusEntityAssembler() {
		
	}
	
	public static EntityAssembler<StatusEntity, StatusDomain> getStatusEntityAssembler() {
		return instance;
	}
	
	@Override
	public StatusEntity toDomain(final StatusDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatusDomain toEntity(final StatusEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
