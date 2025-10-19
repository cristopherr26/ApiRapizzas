package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.SizeDomain;
import co.edu.uco.rapizzas.entity.SizeEntity;

public final class SizeEntityAssembler implements EntityAssembler<SizeEntity, SizeDomain>{
	
	private static final EntityAssembler<SizeEntity, SizeDomain> instance = new SizeEntityAssembler();
	
	private SizeEntityAssembler() {
		
	}
	
	public static EntityAssembler<SizeEntity, SizeDomain> getSizeEntityAssembler() {
		return instance;
	}
	
	@Override
	public SizeEntity toDomain(final SizeDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SizeDomain toEntity(final SizeEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
