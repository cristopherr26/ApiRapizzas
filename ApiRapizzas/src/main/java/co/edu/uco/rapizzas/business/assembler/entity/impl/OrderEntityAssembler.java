package co.edu.uco.rapizzas.business.assembler.entity.impl;

import static co.edu.uco.rapizzas.business.assembler.entity.impl.EmployeeEntityAssembler.getEmployeeEntityAssembler;
import static co.edu.uco.rapizzas.business.assembler.entity.impl.CustomerTableEntityAssembler.getCustomerTableEntityAssembler;
import static co.edu.uco.rapizzas.business.assembler.entity.impl.StatusEntityAssembler.getStatusEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.OrderDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.OrderEntity;

public final class OrderEntityAssembler implements EntityAssembler<OrderEntity, OrderDomain>{
	
private static final EntityAssembler<OrderEntity, OrderDomain> instance = new OrderEntityAssembler();
	
	private OrderEntityAssembler() {
		
	}
	
	public static EntityAssembler<OrderEntity, OrderDomain> getOrderEntityAssembler() {
		return instance;
	}

	@Override
	public OrderEntity toEntity(final OrderDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new OrderDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var customerTableTmp = getCustomerTableEntityAssembler().toEntity(domainTmp.getCustomerTable());
		var employeeTmp = getEmployeeEntityAssembler().toEntity(domainTmp.getEmployee());
		var statusTmp = getStatusEntityAssembler().toEntity(domainTmp.getStatus());
		return new OrderEntity(domainTmp.getId(), domainTmp.getTotal(), domainTmp.getComment(), 
				employeeTmp, statusTmp, customerTableTmp);
	}

	@Override
	public OrderDomain toDomain(final OrderEntity entity) {
		var entityTmp =ObjectHelper.getDefault(entity, new OrderEntity());
		var customerTableDomainTmp = getCustomerTableEntityAssembler().toDomain(entityTmp.getCustomerTable());
		var statusDomainTmp = getStatusEntityAssembler().toDomain(entityTmp.getStatus());
		var employeeDomainTmp = getEmployeeEntityAssembler().toDomain(entityTmp.getEmployee());
		return new OrderDomain(entityTmp.getOrderId(), entityTmp.getTotal(), entityTmp.getComment(), 
				employeeDomainTmp, statusDomainTmp, customerTableDomainTmp);
	}

	@Override
	public List<OrderDomain> toDomain(List<OrderEntity> entityList) {
		var orderDomainList = new ArrayList<OrderDomain>();
		for(var entity : entityList) {
			orderDomainList.add(toDomain(entity));
		}
		return orderDomainList;
	}
	
}
