package co.edu.uco.rapizzas.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.IdentificationTypeDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.IdentificationTypeEntity;

public final class IdentificationTypeEntityAssembler implements EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain>{
	
private static final EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> instance = new IdentificationTypeEntityAssembler();
	
	private IdentificationTypeEntityAssembler() {
		
	}
	
	public static EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> getIdentificationTypeEntityAssembler() {
		return instance;
	}

	@Override
	public IdentificationTypeEntity toEntity(final IdentificationTypeDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new IdentificationTypeDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new IdentificationTypeEntity(domainTmp.getId(), domainTmp.getIdentificationTypeName());
	}

	@Override
	public IdentificationTypeDomain toDomain(final IdentificationTypeEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new IdentificationTypeEntity());
		return new IdentificationTypeDomain(entityTmp.getIdentificationTypeId(), entityTmp.getName());
	}

	@Override
	public List<IdentificationTypeDomain> toDomain(List<IdentificationTypeEntity> entityList) {
		var identificationTypeDomainList = new ArrayList<IdentificationTypeDomain>();
		for (var identificationTypeEntity : entityList) {
			identificationTypeDomainList.add(toDomain(identificationTypeEntity));
		}
		return identificationTypeDomainList;
	}

}
