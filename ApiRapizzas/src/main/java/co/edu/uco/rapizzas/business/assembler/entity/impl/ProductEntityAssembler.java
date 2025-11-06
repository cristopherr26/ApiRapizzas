package co.edu.uco.rapizzas.business.assembler.entity.impl;

import static co.edu.uco.rapizzas.business.assembler.entity.impl.SizeEntityAssembler.getSizeEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.rapizzas.business.assembler.entity.impl.CategoryEntityAssembler.getCategoryEntityAssembler;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.ProductDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.ProductEntity;

public final class ProductEntityAssembler implements EntityAssembler<ProductEntity, ProductDomain>{
	
	private static final EntityAssembler<ProductEntity, ProductDomain> instance = new ProductEntityAssembler();
	
	private ProductEntityAssembler() {
		
	}
	
	public static EntityAssembler<ProductEntity, ProductDomain> getProductEntityAssembler() {
		return instance;
	}

	@Override
	public ProductEntity toEntity(final ProductDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new ProductDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var sizeTmp = getSizeEntityAssembler().toEntity(domainTmp.getSize());
		var categoryTmp = getCategoryEntityAssembler().toEntity(domainTmp.getCategory());
		return new ProductEntity(domainTmp.getId(), domainTmp.getProductName(), 
				domainTmp.getPrice(), categoryTmp, sizeTmp);
	}

	@Override
	public ProductDomain toDomain(final ProductEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new ProductEntity());
		var sizeDomainTmp = getSizeEntityAssembler().toDomain(entityTmp.getSize());
		var categoryDomainTmp = getCategoryEntityAssembler().toDomain(entityTmp.getCategory());
		return new ProductDomain(entityTmp.getProductId(), entityTmp.getProductName(), 
				entityTmp.getPrice(), categoryDomainTmp, sizeDomainTmp);
	}

	@Override
	public List<ProductDomain> toDomain(List<ProductEntity> entityList) {
		var productDomainList = new ArrayList<ProductDomain>();
		for (ProductEntity entity : entityList) {
			productDomainList.add(toDomain(entity));
		}
		return productDomainList;
	}

}
