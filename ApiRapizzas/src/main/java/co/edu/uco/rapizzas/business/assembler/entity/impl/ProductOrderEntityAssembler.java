package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.ProductOrderDomain;
import co.edu.uco.rapizzas.entity.ProductOrderEntity;

public final class ProductOrderEntityAssembler implements EntityAssembler<ProductOrderEntity, ProductOrderDomain>{
	
	private static final EntityAssembler<ProductOrderEntity, ProductOrderDomain> instance = new ProductOrderEntityAssembler();
	
	private ProductOrderEntityAssembler() {
		
	}
	
	public static EntityAssembler<ProductOrderEntity, ProductOrderDomain> getProductOrderEntityAssembler() {
		return instance;
	}
	
	@Override
	public ProductOrderEntity toDomain(final ProductOrderDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductOrderDomain toEntity(final ProductOrderEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
