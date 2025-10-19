package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.ProductDomain;
import co.edu.uco.rapizzas.entity.ProductEntity;

public final class ProductEntityAssembler implements EntityAssembler<ProductEntity, ProductDomain>{
	
	private static final EntityAssembler<ProductEntity, ProductDomain> instance = new ProductEntityAssembler();
	
	private ProductEntityAssembler() {
		
	}
	
	public static EntityAssembler<ProductEntity, ProductDomain> getProductEntityAssembler() {
		return instance;
	}
	
	@Override
	public ProductEntity toDomain(final ProductDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDomain toEntity(final ProductEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
