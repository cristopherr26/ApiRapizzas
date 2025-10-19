package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.OrderDomain;
import co.edu.uco.rapizzas.entity.OrderEntity;

public final class OrderEntityAssembler implements EntityAssembler<OrderEntity, OrderDomain>{
	
private static final EntityAssembler<OrderEntity, OrderDomain> instance = new OrderEntityAssembler();
	
	private OrderEntityAssembler() {
		
	}
	
	public static EntityAssembler<OrderEntity, OrderDomain> getOrderEntityAssembler() {
		return instance;
	}
	
	@Override
	public OrderEntity toDomain(final OrderDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDomain toEntity(final OrderEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
