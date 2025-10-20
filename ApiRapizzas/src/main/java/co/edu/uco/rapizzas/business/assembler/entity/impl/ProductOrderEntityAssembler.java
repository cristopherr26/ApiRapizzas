package co.edu.uco.rapizzas.business.assembler.entity.impl;

import static co.edu.uco.rapizzas.business.assembler.entity.impl.OrderEntityAssembler.getOrderEntityAssembler;
import static co.edu.uco.rapizzas.business.assembler.entity.impl.ProductEntityAssembler.getProductEntityAssembler;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.ProductOrderDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.ProductOrderEntity;

public final class ProductOrderEntityAssembler implements EntityAssembler<ProductOrderEntity, ProductOrderDomain>{
	
	private static final EntityAssembler<ProductOrderEntity, ProductOrderDomain> instance = new ProductOrderEntityAssembler();
	
	private ProductOrderEntityAssembler() {
		
	}
	
	public static EntityAssembler<ProductOrderEntity, ProductOrderDomain> getProductOrderEntityAssembler() {
		return instance;
	}

	@Override
	public ProductOrderEntity toEntity(final ProductOrderDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new ProductOrderDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var orderTmp = getOrderEntityAssembler().toEntity(domainTmp.getOrder());
		var productTmp = getProductEntityAssembler().toEntity(domainTmp.getProduct());
		return new ProductOrderEntity(domainTmp.getId(), domainTmp.getAmount(), 
				productTmp, orderTmp);
	}

	@Override
	public ProductOrderDomain toDomain(final ProductOrderEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new ProductOrderEntity());
		var orderDomainTmp = getOrderEntityAssembler().toDomain(entityTmp.getOrder());
		var productDomainTmp = getProductEntityAssembler().toDomain(entityTmp.getProduct());
		return new ProductOrderDomain(entityTmp.getProductOrderId(), entityTmp.getAmount(), 
				productDomainTmp, orderDomainTmp);
	}

}
