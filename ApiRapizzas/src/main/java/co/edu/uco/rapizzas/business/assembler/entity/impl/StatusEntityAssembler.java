package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.StatusDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.StatusEntity;

public final class StatusEntityAssembler implements EntityAssembler<StatusEntity, StatusDomain>{
	
	private static final EntityAssembler<StatusEntity, StatusDomain> instance = new StatusEntityAssembler();
	
	private StatusEntityAssembler() {
		
	}
	
	public static EntityAssembler<StatusEntity, StatusDomain> getStatusEntityAssembler() {
		return instance;
	}

	@Override
	public StatusEntity toEntity(final StatusDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new StatusDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new StatusEntity(domainTmp.getId(), domainTmp.getStatusName());
	}

	@Override
	public StatusDomain toDomain(final StatusEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new StatusEntity());
		return new StatusDomain(entityTmp.getStatusId(), entityTmp.getStatusName());
	}
	
	

}
