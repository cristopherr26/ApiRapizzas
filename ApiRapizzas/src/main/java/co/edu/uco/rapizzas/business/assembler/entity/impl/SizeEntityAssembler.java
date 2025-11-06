package co.edu.uco.rapizzas.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.SizeDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.SizeEntity;

public final class SizeEntityAssembler implements EntityAssembler<SizeEntity, SizeDomain>{
	
	private static final EntityAssembler<SizeEntity, SizeDomain> instance = new SizeEntityAssembler();
	
	private SizeEntityAssembler() {
		
	}
	
	public static EntityAssembler<SizeEntity, SizeDomain> getSizeEntityAssembler() {
		return instance;
	}

	@Override
	public SizeEntity toEntity(final SizeDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new SizeDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new SizeEntity(domainTmp.getId(), domainTmp.getNameSize());
	}

	@Override
	public SizeDomain toDomain(final SizeEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new SizeEntity());
		return new SizeDomain(entityTmp.getSizeId(), entityTmp.getNameSize());
	}

	@Override
	public List<SizeDomain> toDomain(List<SizeEntity> entityList) {
		var sizeDomainList = new ArrayList<SizeDomain>();
		for (SizeEntity entity : entityList) {
			sizeDomainList.add(toDomain(entity));
		}
		return sizeDomainList;
	}
	
}
